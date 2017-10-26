package com.oa.staff.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oa.staff.entity.dto.AssetsDTO;
import com.oa.staff.entity.dto.PostUserDTO;
import com.oa.staff.service.IAssetsService;
import com.oa.utils.ExtjsAjaxResult;
import com.oa.utils.ExtjsPageable;



@Controller
@RequestMapping("/assets")
public class AssetsController {
	
	@Autowired
	private IAssetsService assetsService;

	//返回所有的数据
	@RequestMapping("/findAll")
	public @ResponseBody List<AssetsDTO> findAll()
	{
		return assetsService.findAll();
	}
	
	//查询请求
	@RequestMapping("/findPage")
	public @ResponseBody Page<AssetsDTO> findAll(AssetsDTO assetsDTO, ExtjsPageable pageable)
	{
		pageable.setSort("assetsId");
		return assetsService.findAll(AssetsDTO.getWhereClause(assetsDTO), pageable.getPageable());
	}
	
	//修改或更新数据
	@PostMapping("/saveOrUpdate")
	public @ResponseBody ExtjsAjaxResult saveOrUpdate(AssetsDTO assetsDto)
	{
		try {
			 assetsService.save(assetsDto);
			 return new ExtjsAjaxResult(true,"操作成功！");
		} catch (Exception e) {
			 e.printStackTrace();
			 return new ExtjsAjaxResult(false,"操作失败！");
		}
	}
	
	//删除数据
	@PostMapping("/delete")
	public @ResponseBody ExtjsAjaxResult delete(Integer[] assetsIds)
	{
		try {
			assetsService.delete(assetsIds);
			 return new ExtjsAjaxResult(true,"操作成功！");
		} catch (Exception e) {
			 e.printStackTrace();
			 return new ExtjsAjaxResult(false,"操作失败！");
		}
	}
	
	//根据UserId查询所拥有的资产
	@RequestMapping("/findAssetsByUserId")
	public @ResponseBody List<AssetsDTO> findAssetsByUserId(HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		return assetsService.findAssetsByUserId(userId);
	}

}
