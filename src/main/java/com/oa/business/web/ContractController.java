package com.oa.business.web;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	
	@PostMapping("/deleteone")
	public @ResponseBody ExtjsAjaxResult delete(Integer id)
	{
		try {
			
			File file = new File(contractService.findOne(id).getContractFile());
			file.delete();
			contractService.delete(id);
			 return new ExtjsAjaxResult(true,"操作成功！");
		} catch (Exception e) {
			 e.printStackTrace();
			 return new ExtjsAjaxResult(false,"操作失败！");
		}
	}
	
	@RequestMapping("/upload")
	public @ResponseBody ExtjsAjaxResult springUpload(HttpServletRequest request,ContractDTO contractDTO,Integer contractId) throws IllegalStateException, IOException
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
                	String filePath=request.getSession().getServletContext().getRealPath("/contract");
                    String fileName=file.getOriginalFilename();
                    // System.out.println(fileName);
					int index=fileName.lastIndexOf("."); 
                    String type = fileName.substring(index + 1); 
                    String pictureUrl="contract\\"+fileName;
                    System.out.println(pictureUrl);
                    if(type.equals("jpg")||type.equals("jpeg")||type.equals("png")) {
                    File targetFile = new File(filePath,fileName);
                    //上传
                    file.transferTo(targetFile);
                    contractDTO.setPictureFileName(pictureUrl);
                    contractDTO.setContractFile(targetFile.getAbsolutePath());
                    }else {
                    	return  new ExtjsAjaxResult(false,"格式不正确");
                    }
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
	
	
	@RequestMapping("/downloadone/{pathId}")
    public ResponseEntity<byte[]> download(@PathVariable Integer pathId)throws IOException {
       //下载文件路径
       File file = new File(contractService.findOne(pathId).getContractFile());
       String FileName = new String(file.getName().getBytes("utf-8"),"ISO-8859-1");
       HttpHeaders headers = new HttpHeaders();  
       headers.setContentDispositionFormData("attachment", FileName); 
       //application/octet-stream ： 二进制流数据（最常见的文件下载）。
       headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
       return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);  
    }
	
    
    @RequestMapping("/findByCondition")
   	public @ResponseBody Page<ContractDTO> findByCondition(ContractDTO contractDTO, ExtjsPageable pageable)
   	{
   		return contractService.findAll(ContractDTO.getWhereClause(contractDTO), pageable.getPageable());
   	}
}
