##### java web发展史

1. javaBean + Servlet + JSP
2. 面对EJB重量级框架带来的种种麻烦
3. SpringMVC/Struts + Spring + Hibernate/myBeaatis
4. 享受SpringBoot "约定大于配置"的乐趣
5. 以Dubbo框架为代表的SOA微服务架构体系
6. SpringCloud微服务架构技术生态圈



##### 课程目的

- 理解ioc的概念以及ioc存在的意义
- 学习使用SpringIoC来完成对Bean声明周期的管理

##### Ioc

> Inversion of Control，控制反转，依赖注入

1. 控制对象的创建及销毁（生命周期）
2. 将对象的控制权交给IoC容器

#####  约定

- 所有Bean的生命周期交由IoC容器创建
- 所有被依赖的Bean通过构造方法执行注入
- 被依赖的Bean需要优先创建



###### demo02代码回顾

- 所有的依赖关系被集中统一管理起来，清晰明了
- 每个类只需要关注自己的业务逻辑
- 修改依赖关系将很容易

###### 使用Spring实例化Bean(demo05)

1. 通过构造函数实例化Bean

   ```xml
   <bean id="bean1" class="spring.ioc.demo.Bean1"/>
   ```

2. 通过静态方法实例化Bean

   ```xml
    <bean class="spring.ioc.demo.Bean2Factory" factory-method="getBean2" id="bean2"/>
   ```

3. 通过实例方法实例化Bean

   ```xml
   <bean id="bean3Factory" class="spring.ioc.demo.Bean3Factory"/>
   <bean id="bean3" class="spring.ioc.demo.Bean3"
         factory-bean="bean3Factory" factory-method="getBean3"/>
   ```

   

###### 注入Bean

1. 通过构造方法注入Bean
2. 通过set方法注入Bean

```xml
// 通过bean标签
<bean class="spring.ioc.demo.Bean" id="bean">
<!--        通过构造方法注入Bean-->
        <constructor-arg
                index="0"
                name="anotherBean"
                type="spring.ioc.demo.AnotherBean"
                ref="anotherBean"/>
        <constructor-arg
                index="1"
                name="string"
                type="java.lang.String"
                value="aaa"/>
<!--        通过set方法注入Bean-->
        <property name="anotherBean1" ref="anotherBean"/>
        <property name="string1" value="bbb"/>
    </bean>
```

```xml
//通过c、p（简写）
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:c="http://www.springframework.org/schema/c"
       xmlns:p="http://www.springframework.org/schema/p"
       ...
<bean class="spring.ioc.demo.Bean"
          id="bean"
          c:anotherBean-ref="anotherBean" c:string="ccc"
          p:anotherBean1-ref="anotherBean" p:string1="eeee"
    />
...
```

3. 集合类型Bean的注入（List、Set、Map、Properties）

   ```xml
   <bean class="spring.ioc.demo.Bean" id="bean">
    
           <property name="stringList">
               <list>
                   <value>aaaa</value>
                   <value>bbbb</value>
               </list>
           </property>
           <property name="anotherBeanList">
               <list>
                   <ref bean="anotherBean"/>
                   <ref bean="anotherBean"/>
               </list>
           </property>
   
           <property name="stringSet">
               <set>
                   <value>aaaa</value>
                   <value>bbbb</value>
               </set>
           </property>
           <property name="anotherBeanSet">
               <set>
                   <ref bean="anotherBean"/>
                   <ref bean="anotherBean"/>
               </set>
           </property>
   
           <property name="stringMap">
               <map>
                   <entry key="ccc" value="ddd"/>
                   <entry key="eee" value="fff"/>
               </map>
           </property>
           <property name="anotherBeanMap">
               <map>
                   <entry key-ref="anotherBean" value-ref="anotherBean"/>
                   <entry key-ref="anotherBean" value-ref="anotherBean"/>
               </map>
           </property>
           <property name="properties">
               <props>
                   <prop key="aaaa">bbbb</prop>
               </props>
           </property>
       </bean>
   ```

   

4. null值注入

   ```xml
   <property name="anotherBean2">
         <null/>
   </property>
   ```

5. 注入时创建内部Bean

```xml
<!-- <property name="anotherBean1" ref="anotherBean"/> -->
// 不使用ref的方式使用bean
<property name="anotherBean1" >
   <bean class="spring.ioc.demo.AnotherBean"/>
</property>
```





#### Bean作用域



- Singleton（单例模式）作用域（一个Spring上下文中（ClassPathXmlApplicationContext））
- prototype作用域（多例模式）

使用场景(demo07)

> Bean1是Singleton，Bean2是prototype，Bean1依赖Bean2。
>
> 希望：每次调用Bean1的某个方法时，该方法拿到的Bean2都是一个新的实例

```java
protected  abstract Bean2 createBean2();

public void pritBean2(){
  System.out.println("bean2 = "+ createBean2());
}
```

```xml
<bean class="spring.ioc.demo07.Bean1" id="bean1" scope="prototype">
    <lookup-method name="createBean2" bean="bean2"/>
</bean>
```



- web环境作用域(demo08)

  - request作用域 #每个请求都会创建一个单独的实例
  - session作用域 #每个会话
  - application作用域 #每个servletContext都会创建一个单独的实例
  - websocket作用域 #每个websocket链接

- 自定义作用域(demo09）

  ```java
  // 创建一个类并且实现Scope接口
  public class MyScope implements Scope {
  ...
  ```

  ```xml
  <bean class="spring.ioc.demo.MyScope" id="myScope"/>
  <bean class="org.springframework.beans.factory.config.CustomScopeConfigurer">
       <property name="scopes">
          <map>
              <entry key="myScope" value-ref="myScope"/>
          </map>
      </property>
  </bean>
   <bean class="spring.ioc.demo.Bean" id="bean" scope="myScope"/>
  ```
```
  
  - SimpleTheadScope作用域（内置）（每个线程会有一个新的实例）
  
    ```xml
    <bean class="org.springframework.context.support.SimpleThreadScope" id="SimpleThreadScope"/>
     <bean class="org.springframework.beans.factory.config.CustomScopeConfigurer">
            <property name="scopes">
                <map>
                    <entry key="SimpleThreadScope" value-ref="SimpleThreadScope"/>
                </map>
            </property>
        </bean>
        <bean class="spring.ioc.demo.Bean" id="bean" scope="SimpleThreadScope"/>
```

    ```java
    for (int i = 0; i < 10; i++) {
        new Thread(new Runnable() {
            public void run() {
                Bean bean = context.getBean("bean", Bean.class);
                System.out.println("bean=" + bean);
            }
        }).start();
    }
    ```


​    

#### Bean的懒加载

Singleton作用域的Bean在spring上下文实例化前就已经被创建

```java
public Bean() {
        System.out.println("Baen已创建= " + this.toString());
    }
```



```java
ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
System.out.println("context已被创建 = " + context);
Bean bean = context.getBean("bean", Bean.class);
System.out.println("Bean = " + bean);
```

```java
//最终输出
Baen已创建= ...
context已被创建 = ...
Bean = ...
```

###### singleton的作用域的bean

> Spring容器会在创建容器提前初始化Singleton作用域的bean。
>
> 但是如果Bean被标注了lazy-init="true"，则改Bean只有在其被需要的时候才会被初始化

单个bean配置
```xml
<bean class="spring.ioc.demo.Bean" id="bean" lazy-init="true"/>
```
所有Bean配置
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:c="http://www.springframework.org/schema/c"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd"
       default-lazy-init="true">
    <bean class="spring.ioc.demo.Bean" id="bean"/>

</beans>
```


```java
//最终输出
context已被创建 = ...
Baen已创建= ...
Bean = ...
```

适用场景

- 某个bean在创徐整个运行周期可能不会被使用

优点

- 尽可能节省资源

缺点

- 可能会导致某个操作响应时间增加



#### Bean的初始化和销毁

Bean实例化后执行一些逻辑，可以通过

- 使用init-method
- 让Bean实现InitializingBean接口

销毁前执行一些逻辑、

- 使用destroy-method

  ```xml
  // 单个
  <bean 
        init-method="onInit" 
        destory-method="onDestroy"></bean> ...
  // 当前配置文件所有（不具有这两个方法的Bean也不会报错）
  <beans 
         default-init-method="onInit" 
         default-destory-method="onDestroy"></beans> ...
  ```

- 让Bean实现DisposableBean接口

```java
// 主动销毁(AbstractApplicationContext类型，被ClassPathXmlApplicationContext继承)
 AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
Bean bean = context.getBean("bean", Bean.class);
System.out.println("Bean = " + bean);
context.close()
```



#### Bean属性继承

Class1和Class2分别继承ParentClass

关键属性

>  abstract="true"
>
>  parent="parentClass"

```xml
 <bean class="spring.ioc.demo.ParentClass" abstract="true" id="parentClass">
     <property name="attribute1" value="1111"/>
     <property name="attribute2" value="222"/>
     <property name="attribute3" value="3333"/>
</bean>

<bean class="spring.ioc.demo.Class1" id="class1" parent="parentClass">
    <!-- <property name="attribute1" value="1111"/>
        <property name="attribute2" value="222"/>
        <property name="attribute3" value="3333"/>-->
    <property name="attribute4" value="4444"/>
    <property name="attribute5" value="5555"/>
</bean>

<bean class="spring.ioc.demo.Class2" id="class2"  parent="parentClass">
    <!--<property name="attribute1" value="1111"/>
        <property name="attribute2" value="222"/>
        <property name="attribute3" value="3333"/>-->
    <property name="attribute7" value="77777"/>
    <property name="attribute8" value="88888"/>
</bean>
```



SpringIoc相关注解的基本使用

关键类

> AnnotationConfigApplicationContext（通过xml配置使用的是ClassPathXmlApplicationContext类）
>
> @Configuration，@Bean

```java
@Configuration
public class MyConfiguration {
    @Bean
    public Bean1 bean1(){ // 这里的bean1方法名相当于bean配置的id
        return new Bean1();
    }
    
    @Bean(value="bean222") // 这里的bean222相当于bean配置的id
    public Bean1 bean2(){
        return new Bean2();
    }
}

```

```java
ApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);   
Bean1 bean1 = context.getBean("bean1", Bean1.class);
Bean2 bean2 = context.getBean("bean222", Bean2.class);
```



###### 进一步优化使用注解

关键注解

> @ComponentScan
>
> @Component

```java
@Configuration
@ComponentScan("spring.ioc.demo") //指定包扫描路径，扫描该目录下有@Component注解的Class
/* xml也可以使用扫描
<context:conpoment-scan base-package="spring.ioc.demo"/>
*/
public class MyConfiguration { }
```

```java
@Component // 默认beanId为首字母小写的类名
public class Bean1 {
}
```

```java
@Component(value="bean222") // beanId为bean222
public class Bean2 {
}
```

```java
// 无变化
ApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);   
Bean1 bean1 = context.getBean("bean1", Bean1.class);
Bean2 bean2 = context.getBean("bean222", Bean2.class);
```

类似@Component注解

- @Controller # 被标注在Controller层
- @Service  # 被标注在Service 层
- @Repository # 被标注在Dao层
- @Component# 通用型注解



###### 别名（同一个Bean多个BeanId）

```java
@Bean(value={"bean222","bean33333"})
public Bean1 bean2(){
    return new Bean2();
}
```

xml方式

```xml
<bean id="bean1" name="bean222,bean33333" ...
```

```xml
<alias name="bean1" alias="bean444444"
```



#### 注入Bean（demo13）

- 通过方法注入Bean

  - 通过构造函数注入（@Autowired）

    ```java
    @Component
    public class MyBean {
        private AnotherBean anotherBean1;
        @Autowired
        public MyBean(AnotherBean anotherBean1){
            this.anotherBean1 = anotherBean1;
        }
    }
    ```

  - 通过set方法注入

    ```java
    @Component
    public class MyBean {
        private AnotherBean anotherBean2;
        @Autowired
        public void setAnotherBean2(AnotherBean anotherBean2) {
            this.anotherBean2 = anotherBean2;
        }
    }
    ```

    

- 通过属性注入Bean

  ```java
  @Component
  public class MyBean {
      @Autowired
      private AnotherBean anotherBean3;
  }
  ```

- 集合类型Bean的注入

  - 直接注入集合实例
  - 将多个泛型的实例注入到集合

    - 将多个泛型的实例注入到List

    - 控制泛型实例在List中的顺序

    - 将多个泛型的实例注入到Map

    List/Set/Map

    ```java
    @Component
    public class MyBean {
     	private  List<String> stringList;
        public List<String> getStringList() {
            return stringList;
        }
        @Autowired
        public void setStringList(List<String> stringList) {
            this.stringList = stringList;
        }
    }
    ```

    ```java
    @Configuration
    @ComponentScan("spring.ioc.demo")
    public class MyConfigruation {
        // 1.
         /*@Bean
        public List<String> stringList(){
            List<String> list = new ArrayList<String>();
            list.add("11111");
            list.add("222");
            return list;
        }*/
        
        //2.Spring会到自己的上下文中寻找所有该List中对应泛型所有的实例，然后将这些实例作用到List里面（1、2两种方式同时存在，2的优先级高）
        @Bean
        public String string1(){
            return "333";
        }
        @Bean
        public String string2(){
            return "444";
        }
    }
    ```

    ###### 向List注入指定的java Bean（优先级最高）

    ````java
    @Bean("stringList")
    public List<String> stringList(){
        List<String> list = new ArrayList<String>();
        list.add("11111");
        list.add("222");
        return list;
    }
    ````

    ```java
    @Component
    public class MyBean {
     	private  List<String> stringList;
        public List<String> getStringList() {
            return stringList;
        }
        @Autowired
        @Qualifier("stringList")
        public void setStringList(List<String> stringList) {
            this.stringList = stringList;
        }
    }
    ```

    ###### 注入顺序

    ```java
    @Bean
    @Order(222) //数字越大优先级越高
    public String string1(){
    	return "333";
    }
    @Bean
    @Order(111)
    public String string2(){
    	return "444";
    }
    ```


- String、Integer等基本类型直接赋值

  ```java
   @Component
  public class MyBean {
      private String string;
      public String getString() {
      	return string;
      }
      
      @Value("string111") //不是String的会自动将String转换成其它类型
      public void setString(String string) {
      	this.string = string;
      }
  }
  ```

- SpringIoC容器内置接口实例注入

  ```java
  @Component
  public class MyBean {
      private ApplicationContext context;// 除了这个，还可以注入BeanFactory、Environment、ResourceLoader、ApplicationPubLister、MessageSource及其实现类
      public ApplicationContext getContext() {
          return context;
      }
  
      @Autowired
      public void setContext(ApplicationContext context) {
          this.context = context;
      }
  }
  
  ```

  

#### SpringIoC注解设置Scope（demo14）

//1.@Component、@Scope

```java
@Component("testBean2")
@Scope("prototype")
	public class TestBean {
}
```

//2.@Bean、@Scope

```java
@Configuration
@ComponentScan("spring.ioc.demo")
public class TestConfiguration {

    @Bean("testBean1")
    @Scope("prototype")
    public TestBean testBean(){
        return new TestBean();
    }
}

```

###### 自定义域

```java
@Configuration
@ComponentScan("spring.ioc.demo")
public class TestConfiguration {
    @Bean("testBean1")
    @Scope("myScope")
    public TestBean testBean() {
        return new TestBean();
    }

    @Bean
    public MyScope myScope() {
        return new MyScope();
    }

    @Bean
    public CustomScopeConfigurer customScopeConfigurer() {
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        configurer.addScope("myScope", myScope());
        return configurer;
    }
}
```

```java
@Component("testBean2")
@Scope("myScope")
public class TestBean {}

```

###### 通过注解进行方法注入

```java
@Component
@Scope("prototype")
public class AnotherBean {}
```

```java
@Component("testBean2")
@Scope("singleton")
public abstract class TestBean {
    @Lookup
    public abstract  AnotherBean anotherBean();
    public void printAnotherBean(){
        System.out.println("anotherBean= "+ anotherBean());
    }
}
```

```java
@Configuration
@ComponentScan("spring.ioc.demo")
public class TestConfiguration {
    @Bean
    public MyScope myScope() {
        return new MyScope();
    }

}
```

```java
public class DemoTest {
    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);
        TestBean testBean2 = context.getBean("testBean2", TestBean.class);
        for (int i = 0; i < 10; i++) {
            testBean2.printAnotherBean();
        }
    }
}
```



#### SpringIoC注解懒加载

// 单个

```java
@Component("testBean2")
@Lazy
public class TestBean {
}
```

```java
@Configuration
@ComponentScan("spring.ioc.demo")
public class TestConfiguration {

    @Bean("testBean1")
    @Lazy
    public TestBean testBean(){
        return new TestBean();
    }
}
```

//全部

```java
@Configuration
@ComponentScan("spring.ioc.demo")
@Lazy
public class TestConfiguration {
    @Bean("testBean1")
    public TestBean testBean(){
        return new TestBean();
    }
}

```



#### SpringIoC注解- Bean的初始化和销毁

```java
@Configuration
@ComponentScan("spring.ioc.demo")
public class TestConfiguration {}
```

// 1.implements InitializingBean,DisposableBean

```java
@Component("testBean2")
public class TestBean implements InitializingBean,DisposableBean {
    public void destory() throws Exception{
        
    }
    
     public void afterPropertiesSet() throws Exception{
        
    }
}
```

//2.@PostConstruct、 @PreDestory

```java
@Component("testBean2")
public class TestBean{
    @PostConstruct
    public void onInit(){
        
    }
    @PreDestory
     public void onDestory(){
        
    }
}
```

//3.@Bean(initMethod="onInit2",destroyMethod="onDestory"2)

```java
public class TestBean{
    public void onInit2(){}
     public void onDestory2(){}
}
```

```java
@Configuration
@ComponentScan("spring.ioc.demo")
public class TestConfiguration {
    @Bean(initMethod="onInit2",destroyMethod="onDestory"2)
    public TestBean testBean(){
        return new TestBean();
    }
}
```





























小知识：

> java应用 -> jar包
>
> web应用 -> war包
>