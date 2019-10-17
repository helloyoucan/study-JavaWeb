package spring.ioc.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("spring.ioc.demo")
public class MyConfigruation {

    @Bean("stringList")
    public List<String> stringList(){
        List<String> list = new ArrayList<String>();
        list.add("11111");
        list.add("222");
        return list;
    }
    @Bean
    public String string1(){
        return "333";
    }
    @Bean
    public String string2(){
        return "444";
    }
}
