package spring.ioc.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("spring.ioc.demo") //指定包扫描路径
public class MyConfiguration { }
