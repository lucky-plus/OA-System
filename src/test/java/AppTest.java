import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(MyJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath*:spring/applicationContext-spring.xml",
		"classpath*:spring/applicationContext-springmvc.xml"})
public class AppTest {
	

}