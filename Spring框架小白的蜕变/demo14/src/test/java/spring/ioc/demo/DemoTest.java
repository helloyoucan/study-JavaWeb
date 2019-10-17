package spring.ioc.demo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class DemoTest {

    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);
        /*TestBean testBean1 = context.getBean("testBean1", TestBean.class);
        System.out.println("testBean1=" + testBean1);
        System.out.println("------------");
        for (int i = 0; i < 10; i++) {
            TestBean testBean2 = context.getBean("testBean2", TestBean.class);
            System.out.println("testBean2=" + testBean2);

        }*/
        TestBean testBean2 = context.getBean("testBean2", TestBean.class);
        for (int i = 0; i < 10; i++) {
            testBean2.printAnotherBean();
        }
    }
}
