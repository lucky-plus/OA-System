package com.oa.personnel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.oa.personnel.dao.IPostDao;
import com.oa.personnel.entity.Post;
import com.oa.personnel.entity.dto.PostDTO;
import com.oa.staff.dao.IStaffDao;
import com.oa.utils.ExtjsAjaxResult;

@Service
public class PostService implements IPostService {

	@Autowired
	private IPostDao postDao;
	@Autowired
	private IStaffDao staffDao;
	
	@Override
	public void save(PostDTO dto) {
		Post entity = new Post();
		PostDTO.dtoToEntity(dto, entity);
		postDao.save(entity);
	}

//	@Override
//	public void delete(Integer id) {
//		postDao.delete(id);
//	}

	@Override
	public ExtjsAjaxResult delete(Integer[] ids) {
		for(Integer id : ids) {
			List<String> userIdList = staffDao.findUserIdByPostId(id);
			if(userIdList.size()==0) {
				postDao.delete(id);
				return new ExtjsAjaxResult(true,"操作成功！");
			} else {
				return new ExtjsAjaxResult(false,"无法删除！该职位正在使用中！");
			}
		}
		return new ExtjsAjaxResult(false,"操作失败！");
	}

	@Override
	public List<PostDTO> findAll() {
		List<Post> postList = (List<Post>) postDao.findAll();
		List<PostDTO> dtoList = new ArrayList<PostDTO>();
		
		for(Post entity : postList) {
			PostDTO dto = new PostDTO();
			PostDTO.entityToDto(entity, dto);
			dtoList.add(dto);
		}
		
		return dtoList;
	}

	@Override
	public Page<PostDTO> findAll(Pageable pageable) {
		Page<Post> postPage = postDao.findAll(pageable);
		List<PostDTO> dtoList = new ArrayList<PostDTO>();
		
		for(Post entity : postPage.getContent()) {
			PostDTO dto = new PostDTO();
			PostDTO.entityToDto(entity, dto);
			dtoList.add(dto);
		}
		
		Page<PostDTO> dtoPage = new PageImpl<PostDTO>(dtoList, pageable, postPage.getTotalElements());
		return dtoPage;
	}

	@Override
	public Page<PostDTO> findAll(Specification<Post> spec, Pageable pageable) {
		Page<Post> postPage = postDao.findAll(spec, pageable);
		List<PostDTO> dtoList = new ArrayList<PostDTO>();
		
		for(Post entity : postPage.getContent()) {
			PostDTO dto = new PostDTO();
			PostDTO.entityToDto(entity, dto);
			dtoList.add(dto);
		}
		
		Page<PostDTO> dtoPage = new PageImpl<PostDTO>(dtoList, pageable, postPage.getTotalElements());
		return dtoPage;
	}

	@Override
	public List<PostDTO> findPostsByDeptId(Integer deptId) {
		List<Post> postList = postDao.findPostsByDeptId(deptId);
		List<PostDTO> dtoList = new ArrayList<PostDTO>();
		
		for(Post entity : postList) {
			PostDTO dto = new PostDTO();
			PostDTO.entityToDto(entity, dto);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public void updateUserPost(String userId, Integer postId) {
		postDao.updateUserPost(userId, postId);
	}

	@Override
	public Post findPostNameByPostId(Integer postId) {
		return postDao.findPostNameByPostId(postId);
	}

}
