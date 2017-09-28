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

@RunWith(MyJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath*:ApplicationContext-jpa.xml",
		"classpath*:ApplicationContext.xml"})
public class RoleTest {
	
	@Autowired
	private IRoleService roleService;

	@Test
	public void roleTest() {
		int page = 0;
		int size = 25;
		Pageable pageable = new PageRequest(page, size);
		Page<RoleDTO> pages = roleService.findAll(pageable);
		
		System.out.println("当前页码：" + pages.getNumber());
		System.out.println("当前页面的记录数：" + pages.getNumberOfElements());
		System.out.println("总页数：" + pages.getTotalPages());
		System.out.println("总记录数：" + pages.getTotalElements());
		System.out.println("每页记录数：" + pages.getSize());
		
		for(RoleDTO dto : pages.getContent()) {
			System.out.println(dto.getModulesText());
		}
	}
	

}