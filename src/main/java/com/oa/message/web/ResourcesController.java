package com.oa.message.web;



import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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


import com.oa.message.entity.dto.ResourcesDTO;
import com.oa.message.service.IResourcesService;
import com.oa.utils.ExtjsAjaxResult;
import com.oa.utils.ExtjsPageable;

@Controller
@RequestMapping("/resources")
public class ResourcesController {
	
	@Autowired
	private IResourcesService resourcesService;
	public @ResponseBody List<ResourcesDTO> findAll()
	{
		return resourcesService.findAll();
	}
	
	@RequestMapping("/findPage")
	public @ResponseBody Page<ResourcesDTO> findAll(ExtjsPageable pageable)
	{
		pageable.setSort("resId");
		return resourcesService.findAll(pageable.getPageable());
	}
	
	
	@PostMapping("/saveOrUpdate")
	public @ResponseBody ExtjsAjaxResult saveOrUpdate(ResourcesDTO resourcesDTO)
	{
		try {
			resourcesDTO.setResDate(new Date());
			resourcesService.save(resourcesDTO);
			return new ExtjsAjaxResult(true,"success");
		}catch(Exception e){
			e.printStackTrace();
			return new ExtjsAjaxResult(false,"false");
		}
		
	}
	
	@PostMapping("/delete")
	public @ResponseBody ExtjsAjaxResult delete(Integer[] ids)
	{
		try {
			for (Integer id : ids) {
			File file = new File(resourcesService.findOne(id).getResIdentify());
			file.delete();
			}
			resourcesService.delete(ids);
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
			
			File file = new File(resourcesService.findOne(id).getResIdentify());
			file.delete();
			resourcesService.delete(id);
			 return new ExtjsAjaxResult(true,"操作成功！");
		} catch (Exception e) {
			 e.printStackTrace();
			 return new ExtjsAjaxResult(false,"操作失败！");
		}
	}
	
	
	@RequestMapping("/upload")
	public @ResponseBody ExtjsAjaxResult springUpload(HttpServletRequest request,ResourcesDTO resourcesDTO) throws IllegalStateException, IOException
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
                    resourcesDTO.setResIdentify(targetFile.getAbsolutePath());
                }
                 
            }
           
        }
        resourcesDTO.setResDate(new Date());
       
		resourcesService.save(resourcesDTO);
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
       File file = new File(resourcesService.findOne(pathId).getResIdentify());
       String FileName = new String(file.getName().getBytes("utf-8"),"ISO-8859-1");
       HttpHeaders headers = new HttpHeaders();  
       headers.setContentDispositionFormData("attachment", FileName); 
       //application/octet-stream ： 二进制流数据（最常见的文件下载）。
       headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
       return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);  
    }
	

}
