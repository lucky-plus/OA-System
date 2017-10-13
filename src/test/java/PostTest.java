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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oa.personnel.entity.dto.PostDTO;
import com.oa.personnel.service.IPostService;
import com.oa.utils.ExtjsAjaxResult;
import com.oa.utils.ExtjsPageable;

@RunWith(MyJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath*:ApplicationContext-jpa.xml",
		"classpath*:ApplicationContext.xml"})
public class PostTest {
	
	@Autowired
	private IPostService postService;

	@Test
	public void saveTest() {
		PostDTO post = new PostDTO();
//		post.setPostName("人事部经理");
//		post.setDeptId(1);
//		post.setPostName("财务部经理");
//		post.setDeptId(2);
//		post.setPostName("市场部经理");
//		post.setDeptId(3);
//		post.setPostId(4);
//		post.setPostName("test4");
//		post.setDeptId(1);
//		post.setPostName("test");
//		post.setDeptId(1);
		postService.save(post);
	}
	
	@Test
	public void findAllTest() {
		int page = 0;
		int size = 10;
		Pageable pageable = new PageRequest(page, size);
		Page<PostDTO> pages = postService.findAll(pageable);
		
		System.out.println("总记录数：" + pages.getTotalElements());
		
		for(PostDTO post : pages.getContent()) {
			System.out.println(post.getPostId());
			System.out.println(post.getPostName());
		}
	}
	
	@Test
	public void findByConditionTest() {
		int page = 0;
		int size = 10;
		Pageable pageable = new PageRequest(page, size);
		
		String postName = "人事";
		String deptName = "人事";
		PostDTO postDTO = new PostDTO();
		postDTO.setPostName(postName);
		postDTO.setDeptName(deptName);
		
		Page<PostDTO> pages = postService.findAll(PostDTO.getWhereClause(postDTO), pageable);
		
		System.out.println("总记录数：" + pages.getTotalElements());

		for(PostDTO post : pages.getContent()) {
			System.out.println(post.getPostId());
			System.out.println(post.getPostName());
		}
	}
	
	@Test
	public void deleteTest() {
		Integer[] ids = {6};
		postService.delete(ids);
	}
}