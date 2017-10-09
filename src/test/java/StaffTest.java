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
import com.oa.staff.dao.IStaffDao;
import com.oa.staff.entity.UserInfornation;
import com.oa.staff.entity.dto.UserRoleDTO;
import com.oa.staff.service.IStaffService;

@RunWith(MyJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath*:ApplicationContext-jpa.xml",
		"classpath*:ApplicationContext.xml"})
public class StaffTest {
	
	@Autowired
	private IStaffService staffService;

	@Test
	public void userRoleServiceTest() {
		int page = 0;
		int size = 10;
		Pageable pageable = new PageRequest(page, size);
		Integer roleLevel = 1;
		Page<UserRoleDTO> pages = staffService.findUserRole(roleLevel, pageable);
		
		System.out.println("当前页码：" + pages.getNumber());
		System.out.println("总页数：" + pages.getTotalPages());
		System.out.println("总记录数：" + pages.getTotalElements());
		
		for(UserRoleDTO dto : pages.getContent()) {
			System.out.println(dto.getUserName());
			System.out.println(dto.getRoleName());
			System.out.println(dto.getModulesText());
		}
	}
	
	@Autowired
	private IStaffDao staffDao;
	
	@Test
	public void userRoleDaoTest() {
		int page = 0;
		int size = 10;
		Pageable pageable = new PageRequest(page, size);
		Integer roleLevel = 1;
		Page<UserInfornation> pages = staffDao.findUserRole(roleLevel, pageable);
		
//		System.out.println("当前页码：" + pages.getNumber());
//		System.out.println("总页数：" + pages.getTotalPages());
//		System.out.println("总记录数：" + pages.getTotalElements());
		
		for(UserInfornation dto : pages.getContent()) {
//		for(UserInfornation dto : pages) {
			System.out.println(dto.getUserName());
		}
	}

}