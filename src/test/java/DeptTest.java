import java.text.ParseException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oa.authority.entity.dto.RoleDTO;
import com.oa.authority.service.IRoleService;
import com.oa.message.entity.Notice;
import com.oa.message.entity.dto.NoticeDTO;
import com.oa.personnel.entity.Department;
import com.oa.personnel.service.IDeptService;
import com.oa.staff.dao.IStaffDao;
import com.oa.staff.entity.UserInfornation;
import com.oa.staff.entity.dto.UserRoleDTO;
import com.oa.staff.service.IStaffService;

@RunWith(MyJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath*:ApplicationContext-jpa.xml",
		"classpath*:ApplicationContext.xml"})
public class DeptTest {

	@Autowired
	private IDeptService deptService;

	@Test
	public void saveTest() {
		Department dept = new Department();
//		dept.setDeptName("人事部");
//		dept.setDeptName("财务部");
//		dept.setDeptName("市场部");
//		dept.setDeptId(4);
//		dept.setDeptName("test4");
		dept.setDeptName("test5");
		deptService.save(dept);
	}
	
	@Test
	public void findAllTest() {
		int page = 0;
		int size = 10;
		Pageable pageable = new PageRequest(page, size);
		Page<Department> pages = deptService.findAll(pageable);
		
		System.out.println("总记录数：" + pages.getTotalElements());
		
		for(Department dept : pages.getContent()) {
			System.out.println(dept.getDeptId());
			System.out.println(dept.getDeptName());
		}
	}
	
	@Test
	public void findByConditionTest() {
		int page = 0;
		int size = 10;
		Pageable pageable = new PageRequest(page, size);
		
		String deptName = "人事";
		Page<Department> pages = deptService.findAll(deptName, pageable);
		
		System.out.println("总记录数：" + pages.getTotalElements());
		
		for(Department dept : pages.getContent()) {
			System.out.println(dept.getDeptId());
			System.out.println(dept.getDeptName());
		}
	}
	
	@Test
	public void deleteTest() {
		Integer[] ids = {4,5};
		deptService.delete(ids);
	}
	
}