import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(MyJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath*:ApplicationContext-jpa.xml",
		"classpath*:ApplicationContext.xml"})
public class AppTest {

	int a = 6666661;

}