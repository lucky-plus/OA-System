package com.oa.utils;

import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FileUploadInterceptor implements HandlerInterceptor {
	
	private Logger LOGGER = LoggerFactory.getLogger(FileUploadInterceptor.class);
	 private long maxSize;

	    @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    	if(request!=null && ServletFileUpload.isMultipartContent(request)) {
	        	ServletRequestContext ctx = new ServletRequestContext(request);
	            long requestSize = ctx.contentLength();
	            if (requestSize > maxSize) {		
	            	PrintWriter pw = null;
	            	try {
	            		ObjectMapper om = new ObjectMapper();
	            		response.setCharacterEncoding("UTF-8");
	            		pw = response.getWriter();
	            		pw.write(om.writeValueAsString(new ExtjsAjaxResult(false,"文件过大")));
	            		
	            	} catch(Exception e) {
	            		
	            	} finally {
	            		if(pw!=null) {
	            			pw.close();
	            		}
	            	}
//	                throw new MaxUploadSizeExceededException(maxSize);
	            }
	        }
	        return true;
	    }


	    @Override
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	    }

	    @Override
	    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	    }

	    public void setMaxSize(long maxSize) {
	        this.maxSize = maxSize;
	    }
}
