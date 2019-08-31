### Spring简介

- 开源的轻量级java开发框架

- Spring核心部分

  - AOP：面向切面编程，扩展功能不是通过修改源代码来实现的

  - IOC：控制反转（创建对象的方式不通过new来实现，而是交给Spring配置创建类对象）

    

- Spring是一站式框架
  - 在JAVAEE三层结构中，每一层都提供了不同的解决技术
  - -web层：Spring MVC
  - -service层：Spring的IOC
  - -dao层：Spring的jdbcTemplate

### IOC

🏃‍*IOC操作**

1. IOC配置文件方式
2. IOC注解方式

🏃‍**IOC底层原理**

>用new方式来创建对象耦合度太多，设计思想：高内聚，低耦合

1. xml配置文件
2. dom4j解决xml
3. 工厂设计模式
4. 反射

```java
//假如现在有两个类
public class UserService{
    
}

public class UserServlet{
    /*在这个方法里面如果想要得到UserService对象，最原始的方法是通过new创建一个对象
    但是在Spring里，它通过工厂模式得到*/
}

👉配置xml对象
<bean id="userService" class="lllr.UserService"/>

👉创建工厂类，通过dom4j解析配置文件+反射

1.根据id值得到class属性值
String classValue = "class属性值"
    
2.使用反射创建对象
Class XX = Class.forName(classValue);
UserService userService = XX.newInstance();
return service;
```

🏃‍**IOC案例**

1. 导入jar包

   - Spring的4个核心文件
   - 日志

2. 创建一个实体类

3. 设置配置文件

   - 导入约束

   - 配置对象

     ```xml
     <?xml version="1.0" encoding="UTF-8"?>
     <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="
             http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
     
         <bean id="user" class="lllr.bean.User"></bean>
     
     </beans>
     ```

     

4. 测试（一般项目中不会这样写，只是这里这样测试）

   ```java
   package lllr.bean;
   
   import org.junit.Test;
   import org.springframework.context.ApplicationContext;
   import org.springframework.context.support.ClassPathXmlApplicationContext;
   
   public class IOC_test {
   
   	@Test
   	public void testUser() {
   		//1.加载Sprign配置文件
   		@SuppressWarnings("resource")
   		ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
   		//2.得到配置创建的对象
   	    User user =(User) context.getBean("user");
   	    System.out.println(user);
   	    user.testUser();
   	}
   }
   
   ```

   

   ## Bean

   🏃‍**bean操作**

   > 👉创建对象
   >
   > 在Spring里通过配置文件创建对象
   >
   > 👉bean实例化三种方式实现
   >
   > - 使用类的无参数构造创建（==重点==）
   > - 使用静态工厂创建（创建静态的方法，返回类对象）
   > - 使用实例工厂创建（创建不是静态的方法，返回类对象）

   ⭐使用类的无参数构造创建：

   如果类本来没有构造函数，本来就会产生一个无参的构造函数，但是如果自己写了一个有参构造方法，那么应该再重载一个

   

   🏃bean标签常用属性

   > 1. id属性：
   >    - 不能包含特殊符号，比如_user里面的 _
   > 2. class属性：
   >    - 创建对象所在类的全路径
   > 3. name属性：
   >    - 功能和id属性的功能是一样的
   >    - 可以任意命名，可以包含特殊符号
   >    - 比较少用，一般用id
   > 4. scope属性
   >    - ==singleton== 单例的（默认值）
   >    - ==prototype== 多例的
   >    - request： 创建对象把对象放到request域里面
   >    - session：创建对象把对象放到session域里面
   >    - globalSession：创建对象把对象放到globalSession域里面
   >
   > 

   ```java
   👉singleton
   public void testUser() {
   		//1.加载Sprign配置文件
   		@SuppressWarnings("resource")
   		ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
   		//2.得到配置创建的对象
   	    User user1 =(User) context.getBean("user");
   	    User user2 =(User) context.getBean("user");
   	    System.out.println(user1);
   	    System.out.println(user2);
   	}
   结果：
   lllr.bean.User@192d3247
   lllr.bean.User@192d3247
   ⭐说明这两个是同一个对象
   
   👉prototype
   //修改xml文件中的内容
   <bean id="user" class="lllr.bean.User" scope="prototype"></bean>
   
   运行上述 testUser()结果：
   lllr.bean.User@37374a5e
   lllr.bean.User@4671e53b
   ⭐说明这两个不是同一个对象
   ```

   

## 属性注入

🏃‍**属性注入方法**

- set方法

- 有参构造注入

- 使用接口注入（Spring不支持）

  

- [x] **有参构造注入方法**

```java
👉先创建一个java类
package lllr.bean.property;

public class Property {

	private String username;

	public Property(String username) {
		this.username = username;
	}
	
	public void test() {
		System.out.println("demo1......"+username);
	}
}

//xml文件中配置
<bean id="demo" class="lllr.bean.property.Property">
   <constructor-arg name="username" value="校长"></constructor-arg>
</bean> 

//测试
Property p =(Property) context.getBean("demo");
p.test();

//输出结果：
demo1......校长
```

- [x] **set方法注入**

  ```java
  <!-- 使用set方法注入属性 -->
  <bean id="setMethod" class="lllr.bean.property.Property">
    <property name="username"  value="小往"></property>
  </bean>
  
  
  //输出
  demo1......小往
  /*
  注意：
  1.使用这个方法需要有一个无参的构造函数
  2.有标准的getter和setter方法
  */
  
  ```

  

  - [x] 注入对象类型的属性

    ```java
    /*
    在项目中，经常要在Service层调用dao层的对象方法，正常来说过程是在service层里面写
    UserDao userDao = new UserDao();
    userDao.add();
    现在直接利用Spring的配置来完成这一个过程
    */
    
    👉
    ```

    

  

 