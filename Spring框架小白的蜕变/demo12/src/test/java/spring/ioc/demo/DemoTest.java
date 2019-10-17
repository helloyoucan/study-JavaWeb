package spring.ioc.demo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class DemoTest {
    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);
        Bean1 bean1 = context.getBean("bean1", Bean1.class);
        System.out.println("bean1=" + bean1);
    }
}
