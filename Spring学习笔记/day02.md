#  IOC和DI的区别

### IOC

> 控制反转，把对象创建交给Spring管理

### DI

> 依赖注入，向类中的属性设置值

**关系**：依赖注入不能单独存在，需要在IOC基础之上完成操作

### Spring整合web项目原理

```java
👉实现思想：把加载配置文件和创建对象的过程在服务器启动的时候完成

👉实现原理：
（1）ServletContext对象
（2）监听器

🥧具体使用：
1.在服务器启动的时候，为每个项目创建一个ServletContext对象
2.在ServletContext对象创建的时候，使用监听器可以监听到对象在什么时候创建的
3.一旦监听到ServletContext对象被创建了，马上加载Spring配置文件，把配置文件里面的对象创建好，把创建出来的对象放到ServletContext里面（SetAttribute)
4.获取对象的时候，通过ServletContext域得到 
```

# Spring的bean管理（注解）

🏃‍**准备：**

1. 导入jar包，除了之前的还有aop的jar包

2. xml文件引入约束

   ```xml
   <beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context" xsi:schemaLocation="
           http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd"> <!-- bean definitions here -->
   
   </beans>
   ```

   

3. xml文件配置

   ```xml-dtd
   <!-- 扫描该包下的类，方法，属性上是否有注解-->
   <context:component-scan base-package="com.llllr"></context:component-scan>
   ```




### 创建对象



🏃‍**注解创建对象**：

```java
👉Component注解       
//@Component(values="xxx") 这里的xxx相当于id

@Component(value="user")   //<bean id="user" class=""></bean>
public class User {

	public void testUser() {
		System.out.println("user testing...");
	}
}
```

🏃‍**创建对象有四个注解**：

> @Component
>
> @Controller(web层)
>
> @Service(业务层)
>
> @Repository(持久层)

🏃‍**创建对象是单实例还是多实例**：

```java
@Service(values="user")
@Scope(values="prototype") //配置多实例
public class User{
    
}
```



### 注解注入属性

> @AutoWired
>
> @Resource(name="")

和day01一样，如果在UserService里面使用UserDao，应该注入属性

- 所以先创建UserDao

```java
@Component("userDao")    //这里省略value 
public class UserDao {
	public void DaoTest() {
		System.out.println("userDao..........");
	}
}
```

- 然后UserService

```java


@Component("userService")
public class UserService {
    //方法1
	@Autowired
	private UserDao userDao;
	
	public void testUserService() {
		System.out.println("UserService...");
		userDao.DaoTest();
	}
}


@Component("userService")
public class UserService {
    
    //方法2 
	@Resource(name="userDao")
	private UserDao userDao;
	
	public void testUserService() {
		System.out.println("UserService...");
		userDao.DaoTest();
	}
}


输出
UserService...
userDao..........
```



# 配置文件和注解混合使用



- 创建对象操作使用配置文件的方式来实现
- 注入属性的操作使用注解方式来实现

🏃‍**实例**

👉配置文件的方法创建对象

```xml
<!-- 在xml文件里面创建对象-->

<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context" xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd"> <!-- bean definitions here -->

<context:component-scan  base-package="lllr"></context:component-scan>


<bean id="bookService" class="lllr.Service.BookService"></bean>
<bean id= "bookDao" class="lllr.Dao.BookDao"></bean>
<bean id= "orderDao" class="lllr.Dao.OrderDao"></bean>
</beans>

```

👉注解方式注入属性

```java
public class BookService {

	@Resource(name="bookDao")
	BookDao bookDao;
	@Resource(name="orderDao")
	OrderDao orderDao;
	
	public void bookServiceTest() {
		System.out.println("bookService.....");
		bookDao.Book();
		orderDao.Order();
	}
}

//输出
bookService.....
book...........
order...........
```



# AOP

> 面向切面（方面）编程，扩展供能不通过修改源代码来实现
>
> AOP采取横向抽取机制，取代了传承纵向继承体系重复性代码（性能监视，事务管理，安全检查，缓存）
>
> Spring AOP使用纯java实现，不需要专门的编译过程和类加载器，在运行期通过代理方式向目标类织入增强代码



## AOP底层原理



## AOP操作术语

- **Joinpoint(连接点)：类里面可以被增强的方法**
- **Pointcut(切入点)：在类里面有很多方法可以被增强，但是在实际操作中只是增强了类里面的add()和update()，那么这两个方法就被称为切入点**
- **Advice(通知/增强）：增强的逻辑，称为增强，比如扩展日志功能，这个日志功能就被称为增强**
  - 前置通知：在方法之前执行
  - 后置通知：在方法之后执行
  - 异常通知：方法出现异常
  - 最终通知：在后置之后执行
  - 环绕通知：在方法之前和之后执行
- **Aspect(切面）：把增强应用到具体方法上面的过程就称为切面。把增强用到切入点的过程**

# Sring里面的AOP操作

## AspectJ简介

- 一个面向切面的框架，扩展了java语言，定义了AOP语法，所以它有一个专门的编译器来生成遵守java字节编码规范的class文件
- 基于Java语言的AOP框架

## 使用AspectJ实现AOP有两种方式

- 基于xml配置
- 基于注解方式

🏃‍准备

```
1.导入jar包
2.在xml文件中引入约束
```

🏃‍使用表达式配置切入点

> 常用的表达式
>
> execution(<访问修饰符>?<返回类型><方法名>（<参数>）<异常>)
>
> - execution(* lllr.bean.Book.add(..))
> - execution(* lllr.bean.Book.*(..))
> - execution(* \*.\*(..))
> - execution(* lllr.bean.Bo*(..))

🏃‍实操

```xml
👉约束
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:aop="http://www.springframework.org/schema/aop" xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd"> <!-- bean definitions here -->
```



```xml
👉方法1（配置文件)

<!-- 配置对象 -->
<bean id="book" class="lllr.AOP.Book"></bean>
<bean id="mybook" class="lllr.AOP.Mybook"></bean>

<!-- 配置AOP -->
<aop:config>
	<!-- 配置切入点 -->
	<aop:pointcut expression="execution(* lllr.AOP.Mybook.*(..))" id="pointcut1"/>
	<!-- 配置切面 -->
	<aop:aspect ref="mybook">
	     <aop:before method="before" pointcut-ref="book"/>
	</aop:aspect>
</aop:config>


👉方法2（注解）
<!-- 配置对象 -->
<bean id="book" class="lllr.AOP.Book"></bean>
<bean id="mybook" class="lllr.AOP.Mybook"></bean>

<aop:aspectj-autoproxy></aop:aspectj-autoproxy>

@Aspect
public class Mybook {

   @Before(value="execution(* lllr.AOP.Mybook.*(..))")
	public void before() {
		
		System.out.println("before add!...........");
	}
}

<!-- 输出-->
before add!...........
add.......

```



