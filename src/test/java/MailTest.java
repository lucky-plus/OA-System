import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;

import org.junit.Test;

import com.oa.utils.MailUtil;

public class MailTest {
	
	@Test
	public void testMail() {
		try {
			MailUtil.sendMessage("598959863@qq.com", "taweq", "asdadasdw");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}