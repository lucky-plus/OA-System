package com.oa.personnel.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oa.personnel.entity.dto.PostDTO;
import com.oa.personnel.service.IPostService;
import com.oa.utils.ExtjsAjaxResult;
import com.oa.utils.ExtjsPageable;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private IPostService postService;
	
	@RequestMapping("/saveOrUpdate")
	public @ResponseBody ExtjsAjaxResult saveOrUpdate(PostDTO postDTO) {
		try {
			postService.save(postDTO);
			return new ExtjsAjaxResult(true,"操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return new ExtjsAjaxResult(false,"操作失败！");
		}
	}
	
	@RequestMapping("/delete")
	public @ResponseBody ExtjsAjaxResult deleteAuthority(Integer[] postId)
	{
		try {
			return postService.delete(postId);
		} catch (Exception e) {
			 e.printStackTrace();
			 return new ExtjsAjaxResult(false,"操作失败！");
		}
	}
	
	
	@RequestMapping("/findAll")
	public @ResponseBody Page<PostDTO> findAll(ExtjsPageable pageable) {
		return postService.findAll(pageable.getPageable());
	}
	
	@RequestMapping("/findByCondition")
	public @ResponseBody Page<PostDTO> findByCondition(PostDTO postDTO, ExtjsPageable pageable) {
		return postService.findAll(PostDTO.getWhereClause(postDTO), pageable.getPageable());
	}
	
	@RequestMapping("/findPostsByDeptId")
	public @ResponseBody List<PostDTO> findPostsByDeptId(Integer deptId) {
		return postService.findPostsByDeptId(deptId);
	}
	
}
