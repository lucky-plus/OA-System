import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oa.message.entity.dto.NoticeDTO;
import com.oa.message.service.INoticeService;

@RunWith(MyJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath*:ApplicationContext-jpa.xml",
		"classpath*:ApplicationContext.xml"})
public class NoticeTest {
	
	@Autowired
	private INoticeService noticeService;
	
	@Test
	public void NoticeTest() {
		try {
			
			int page = 0;
			int size = 25;
			Pageable pageable = new PageRequest(page, size);
			
			NoticeDTO noticeDTO = new NoticeDTO();
			String noticeName = "es"; 
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date beginDate;
			beginDate = df.parse("2017-10-01 00:00:00");
			Date endDate = df.parse("2017-10-08 24:00:00");
			noticeDTO.setNoticeName(noticeName);
			noticeDTO.setBeginDate(beginDate);
			noticeDTO.setEndDate(endDate);
			
			Page<NoticeDTO> pages = noticeService.findAll(NoticeDTO.getWhereClause(noticeDTO), pageable);
			
			System.out.println("总记录数：" + pages.getTotalElements());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

}