package spring.ioc.demo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        // 通过构造函数实例化Bean
        Bean1 bean1 = context.getBean("bean1", Bean1.class);
        System.out.println("Bean1Test:" + bean1);


        // 通过静态方法实例化Bean
        // Bean2 bean2 = Bean2Factory.getBean2();
        Bean2 bean2 = context.getBean("bean2", Bean2.class);
        System.out.println("Bean2Test:" + bean2);

        //通过实例方法实例化Bean
        //Bean3Factory bean3Factory = new Bean3Factory();
        //Bean3 bean3 = bean3Factory.getBean3();
        Bean3 bean3 = context.getBean("bean3", Bean3.class);
        System.out.println("Bean3Test:" + bean3);

        // 别名
        Bean1 bean1_1 = context.getBean("bean1_1", Bean1.class);
        Bean1 bean1_2 = context.getBean("bean1_2", Bean1.class);
        Bean1 bean1_3 = context.getBean("bean1_3", Bean1.class);
        System.out.println("Bean1_1:" + bean1_1);
        System.out.println("bean1_2:" + bean1_2);
        System.out.println("bean1_3:" + bean1_3);
    }
}
