package spring.ioc.demo07;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//        Bean2 bean2_1 = context.getBean("bean2", Bean2.class);
//        System.out.println("bean2_1 = " + bean2_1);
//        Bean2 bean2_2 = context.getBean("bean2", Bean2.class);
//        System.out.println("bean2_2 = " + bean2_2);
//        Bean1 bean1 = context.getBean("bean1", Bean1.class);
//        System.out.println("bean1 = " + bean1);
//
//        ApplicationContext context1 = new ClassPathXmlApplicationContext("spring.xml");
//        Bean2 bean2_3 = context1.getBean("bean2", Bean2.class);
//        System.out.println("bean2_3 = " + bean2_3);
//        Bean2 bean2_4 = context1.getBean("bean2", Bean2.class);
//        System.out.println("bean2_4 = " + bean2_4);

        // 测试Bean1和Bean2分别为Singleton和prototype时输出的不同
//        Bean1 bean2_5 = context.getBean("bean1", Bean1.class);
//        System.out.println("bean2_5 = " + bean2_5);
//        Bean1 bean2_6 = context.getBean("bean1", Bean1.class);
//        System.out.println("bean2_6 = " + bean2_6);
//        System.out.println(bean2_5 == bean2_6);

        Bean1 bean1 = context.getBean("bean1",Bean1.class);
        bean1.pritBean2();
        bean1.pritBean2();
        bean1.pritBean2();
        bean1.pritBean2();
    }
}
