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

