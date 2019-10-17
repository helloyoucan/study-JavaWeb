package spring.ioc.demo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoTest {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        System.out.println("context已被创建 = " + context);
        Bean bean = context.getBean("bean", Bean.class);
        System.out.println("Bean = " + bean);

    }
}
