package com.oa.business.web;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.oa.business.entity.dto.ContractDTO;
import com.oa.business.service.IContractService;
import com.oa.utils.ExtjsAjaxResult;
import com.oa.utils.ExtjsPageable;

@Controller
@RequestMapping("/contract")
public class ContractController {
	@Autowired
	private IContractService contractService;
	public @ResponseBody List<ContractDTO>findALL(){
		return contractService.findAll();
	}
	
	@RequestMapping("/findPage")	
	public @ResponseBody Page<ContractDTO>findALL(ExtjsPageable pageable){
		pageable.setSort("contractId");
		return contractService.findAll(pageable.getPageable());
	}
	
	@PostMapping("/delete")
	public @ResponseBody ExtjsAjaxResult delete(Integer[] ids)
	{
		try {
			for (Integer id : ids) {
			File file = new File(contractService.findOne(id).getContractFile());
			file.delete();
			}
			contractService.delete(ids);
			 return new ExtjsAjaxResult(true,"操作成功！");
		} catch (Exception e) {
			 e.printStackTrace();
			 return new ExtjsAjaxResult(false,"操作失败！");
		}
	}
	
	@RequestMapping("/upload")
	public @ResponseBody ExtjsAjaxResult springUpload(HttpServletRequest request,ContractDTO contractDTO) throws IllegalStateException, IOException
    {
		try {
         //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
           //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();
             
            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                	String filePath=request.getSession().getServletContext().getRealPath("/fileDir");
                    String fileName=file.getOriginalFilename();
                    File targetFile = new File(filePath,fileName);
                    //上传
                    file.transferTo(targetFile); 
                    contractDTO.setContractFile(targetFile.getAbsolutePath());
                }
                 
            }
           
        }
		contractService.save(contractDTO);
		return  new ExtjsAjaxResult(true,"success");
		}
		catch(Exception e){
			e.printStackTrace();
			return new ExtjsAjaxResult(false,"false");
		}
        
    }
	
}
