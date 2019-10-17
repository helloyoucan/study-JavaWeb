package spring.ioc.demo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoTest {

    @Test
    public void test() {
        /**
         * java中规定，内部类只能访问外部类中的成员变量，
         * 不能访问方法中定义的变量，
         * 如果要访问方法中的变量，就要把方法中的变量声明为final（常量）的，
         * 因为这样可以使变量全局化，就相当于是在外部定义的而不是在方法里定义的
         */
        final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        for (int i = 0; i < 10; i++) {
            Bean bean = context.getBean("bean", Bean.class);
            System.out.println("bean=" + bean);
        }
        System.out.println("---------------");
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    Bean bean = context.getBean("bean", Bean.class);
                    System.out.println("bean=" + bean);
                }
            }).start();
        }
    }
}
