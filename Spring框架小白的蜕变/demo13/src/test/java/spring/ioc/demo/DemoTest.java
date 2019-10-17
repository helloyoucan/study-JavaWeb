package spring.ioc.demo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class DemoTest {

    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfigruation.class);
        MyBean myBean = context.getBean("myBean", MyBean.class);
        System.out.println("myBean=" + myBean);
        for (String s:myBean.getStringList()) {
            System.out.println("s=" + s);
        }
    }
}
