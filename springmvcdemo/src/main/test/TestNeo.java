import com.demo.NeoTestService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestNeo {
    public static void main( String[] args ) {
        ApplicationContext context = new ClassPathXmlApplicationContext("test.xml");
        System.out.println();
        NeoTestService neoTestService = (NeoTestService) context.getBean("neoTestService");
        neoTestService.doWork();
    }
}
