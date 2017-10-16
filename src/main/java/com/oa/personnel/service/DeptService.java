package com.oa.personnel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.oa.personnel.dao.IDeptDao;
import com.oa.personnel.entity.Department;
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
	public void delete(Integer id) {
		deptDao.delete(id);
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
