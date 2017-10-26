package com.oa.personnel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.oa.personnel.dao.IDeptDao;
import com.oa.personnel.dao.IPostDao;
import com.oa.personnel.entity.Department;
import com.oa.personnel.entity.Post;
import com.oa.personnel.web.TreeNode;

@Service
public class DeptService implements IDeptService {

	@Autowired
	private IDeptDao deptDao;	
	

	
	@Override
	public void save(Department dept) {
		deptDao.save(dept);
	}

	@Override
	public void delete(Integer nodeId) {
		
		Department node = deptDao.findOne(nodeId);//当前删除的节点
		Department parentNode = node.getParentNode();//当前删除的节点的父节点
		if(parentNode!=null) {
			//有父节点。则把当前节点的子节点挂到父节点下（双向关联）。父节点为null则直接作为根节点。
			//遍历当前删除的节点的子节点
			for(Department c  : node.getChildNodes()) {
				parentNode.getChildNodes().add(c);
				c.setParentNode(parentNode);
			}
			parentNode.getChildNodes().remove(node);
		}else {
			//没有父节点直接设置为null，作为根节点
			for(Department c  : node.getChildNodes()) {
				c.setParentNode(null);
			}
		}
		node.setParentNode(null);
		
		deptDao.save(node);

		node.getChildNodes().clear();
		deptDao.delete(node);

	}

	@Override
	public void delete(Integer[] ids) {
		for(Integer id : ids) {
			deptDao.delete(id);
		}
	}

	@Override
	public List<Department> findAll() {
		List <Department> deptList=(List<Department>) deptDao.findAll();
		for(Department entity: deptList) {
			entity.setParentNode(null);
			entity.setChildNodes(null);
		}
		return deptList;
	}

	@Override
	public Page<Department> findAll(Pageable pageable) {
		return deptDao.findAll(pageable);
	}

	@Override
	public Page<Department> findAll(String deptName, Pageable pageable) {
		return deptDao.findAll(deptName, pageable);
	}


	public List<TreeNode> findNodes(Integer parentId) 
	{
		List<TreeNode> nodeList = new ArrayList<TreeNode>();
		List<Department> lists;
		if(parentId==null) {
			lists =  deptDao.findParentNodes();
		}else {
			lists =  deptDao.findChildNodes(parentId);
		}
		for(Department d : lists) {
			TreeNode node  = new TreeNode();
			node.setId(d.getDeptId());
			node.setText(d.getDeptName());
			if(d.getChildNodes()!=null) {
				if(d.getChildNodes().size()>0) {
					node.setLeaf(false);//设置为父节点
				}else {
					node.setLeaf(true);//设置为子节点
				}
			}
			nodeList.add(node);
		}
		return nodeList;
	}

	@Override
	public Department findOne(Integer id) {
		return deptDao.findOne(id);
	}

	@Override
	public void delete(Department dept) {
		 deptDao.delete(dept);
		
	}


	
}
