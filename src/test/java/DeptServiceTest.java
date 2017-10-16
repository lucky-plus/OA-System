

import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.oa.personnel.entity.Department;
import com.oa.personnel.service.IDeptService;
import com.oa.personnel.web.TreeNode;



@RunWith(SpringJUnit4ClassRunner.class) //① 指定测试用例运行器  
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:applicationContext-jpa.xml"})  
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)  
@Transactional
public class DeptServiceTest 
{
	@Autowired
	private IDeptService deptService;
	
	@BeforeTransaction
	@Rollback(false)
	public  void initData() 
	{
		Department c1 = new Department();
		c1.setDeptName("OA公司");
		
		
		Department c11 = new Department();
		c11.setDeptName("总经理");
		c11.setParentNode(c1);
		
		Department c2 = new Department();
		c2.setDeptName("财务部");
		c2.setParentNode(c11);
		
		Department c3 = new Department();
		c3.setDeptName("人事部");
		c3.setParentNode(c11);

		Department c4 = new Department();
		c4.setDeptName("市场部");
		c4.setParentNode(c11);
		
		
		c1.getChildNodes().add(c11);
		c11.getChildNodes().add(c2);
		c11.getChildNodes().add(c3);
		c11.getChildNodes().add(c4);

		deptService.save(c1);
		
	}
	/**
	 * 根据NodeId 查询根节点和子节点 
	 * 
	 * NodeId为null，返回所有根节点
	 * NodeId不为null，返回所有该id节点的所有子节点
	 */
	
//	@Test
	public  void findNodes() 
	{
		Assert.assertNotNull(deptService);
		
		List<TreeNode> parentNodes = deptService.findNodes(null);
		System.out.println("根节点：------------------------------------");
		for (TreeNode node : parentNodes) {
			System.out.println(node);
		}
	
		List<TreeNode> childNodes = deptService.findNodes(2);
		System.out.println("子节点：------------------------------------");
		for (TreeNode node : childNodes) {
			System.out.println(node);
		}
	}
	/**
	 * 父节点ID  parentNodeId 
	 * parentNodeId不为null,则作为该ID节点的子节点
	 * parentNodeId为null，则作为根节点
	 */
//	@Test
	@Rollback(false)
	public  void addNewNode() 
	{
		Integer parentNodeId = 5;//null
		
		Department newNode = new Department();
		newNode.setDeptName("新的节点");
		
		if(parentNodeId!=null) {
			newNode.setParentNode(deptService.findOne(parentNodeId));
		}

		deptService.save(newNode);
	}
	
	/**
	 * 根据Node Id删除节点 
	 * 场景1：级联删除子节点
	 * 场景2：把子节点更新到父节点上，再删除当前节点
	 * 		支持删除任意节点（根节点、子节点）
	 */
//	@Test
	@Rollback(false)
	public  void deleteNewNode() 
	{
		Integer nodeId  = 1;//2L,5L
		
		Department node = deptService.findOne(nodeId);//当前删除的节点
		
		
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
		
		deptService.save(node);

		node.getChildNodes().clear();
		deptService.delete(node);
	}
	
}
