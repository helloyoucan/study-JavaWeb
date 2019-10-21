package spring.aop.schema.advice;


import base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestAopSchemaAdivce extends UnitTestBase {
    public TestAopSchemaAdivce(){
        super("classpath:spring.aop.schema.advice.xml");
    }
    @Test
    public void testBiz(){
        spring.aop.schema.advice.biz.AspectBiz biz = super.getBean("aspectBiz");
        biz.biz();
    }
}
