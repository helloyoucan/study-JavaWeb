package spring.ioc.demo;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("testBean2")
@Scope("singleton")
public abstract class TestBean {

    @Lookup
    public abstract  AnotherBean anotherBean();

    public void printAnotherBean(){
        System.out.println("anotherBean= "+ anotherBean());
    }
}
