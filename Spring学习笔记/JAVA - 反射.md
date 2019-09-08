# JAVA - 反射

## 1.1 Class类的使用

> 任何一个类都是Class类的实例对象

👉现有一个类Foo

```java

class Foo{
    
  public void testFoo(){
        System.out.println("Successfully build Foo...");
    }
}
```

👉创建这个类的实例

```java
 @Test
    public  void testClass(){
        Foo foo = new Foo();
    }
```

⭐就像foo是Foo的实例对象，==其实Foo这个类也是Class类的实例对象==。那么Class类的实例对象应该怎么表示呢

- 任何一个类都有一个隐含的静态成员变量class

```java
Class c1 = Foo.class
```

为了看出c1到底是什么，在控制台输出c1

结果：

```
class TestClass.Foo
```

- 已经知道该类的对象通过getClass的方法获取

```java
Class c2 = foo.getClass();
```

- Class.forName()方法

⭐一个总结代码：

```java
    @Test
public  void testClass() throws ClassNotFoundException {
        Foo foo = new Foo();
        Class c1 = Foo.class;
        Class c2 = foo.getClass();
        Class c3 = null;
        c3 = Class.forName("TestClass.Foo");
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
    }

//输出结果：
class TestClass.Foo
class TestClass.Foo
class TestClass.Foo
```

> 我们可以通过类的类类型来创建该类的实例对象
>
> 👉也就是说可以通过c1 c2 c3来创建Foo的实例对象

**newInstance()**

```java
    try {
            Foo  foo2 =(Foo) c1.newInstance();//前提是需要有一个无参数的构造方法
            foo2.testFoo();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


//输出结果：
class TestClass.Foo
class TestClass.Foo
class TestClass.Foo
Successfully build Foo...
```



## 2.1 JAVA动态加载类

> Class.forName("类的全称")
>
> - 不仅表示类的类类型，还代表了动态加载类
> - 区分编译和运行
> - 编译时刻加载的类是静态加载类，运行时刻加载类是动态加载类

#### 编译和运行的区别

👉现创建一个Office.java

```java
class Office{
	
	public static void mian(String[] args){
		
		Word word = new Word();
		word.start();
		
		Excel excel = new Excel();
		excel.start();
	}
}
```

在cmd中输入javac Office.java

输出：

找不到Word 和 Excel

于是在同级目录下再创建Word.java

```java
class Word{
	
	public static void start(){
		System.out.println("Word start...");
	}
}
```

这时候只报错说找不到Excel了

==new 创建对象，是静态加载类，在编译时刻就需要加载所有可能用到的类==

👇这种写法就是动态加载类的过程

```java
class OfficeBetter
{
	public static void main(String[] args){
		try{
			Class c = Class.forName(args[0]);
			Word w = (Word)c.newInstance();
			w.start();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
```

## 获取类的信息

#### 1.获取类的类类型

```java
 @Test
    public  void testClass() throws ClassNotFoundException {
        Foo foo = new Foo();
        Class c = foo.getClass();
        System.out.println(c);
        //获取类的名称
        System.out.println(c.getName());
        //获取所有public的函数，包括从父类继承的
        Method[] methods = c.getMethods();
        //获取所有自己该类的方法，不考虑访问权限，不包括父类的方法
        Method[] methods2 = c.getDeclaredMethods();
        //获取方法名
        for(int i=0;i<methods.length;i++)
        {
            /*
            * 获取返回值的类类型
            * 如果返回值是String,那么得到的returnType就是String.class
            * 所以再用getName()方法
            */
            Class returnType = methods[i].getReturnType();
            System.out.print(returnType.getName()+" ");
            //获取方法名
            System.out.print(methods[i].getName()+" ");
            //获取参数类型，如果参数列表是int型，那么返回的就是int.class型
            System.out.print("(");
            Class[] paramTypes = methods[i].getParameterTypes();
            for(int k=0;k<paramTypes.length;k++){
                if(k!=paramTypes.length-1)
                    System.out.print(paramTypes[k]+",");
                else
                    System.out.print(paramTypes[k]);
            }
            System.out.print(")");
            System.out.println();
        }
    }

//输出结果
class TestClass.Foo
TestClass.Foo
void method1 (int,int)
void wait ()
void wait (long,int)
void wait (long)
boolean equals (class java.lang.Object)
java.lang.String toString ()
int hashCode ()
java.lang.Class getClass ()
void notify ()
void notifyAll ()
```

#### 2.获取成员变量构造函数信息

```java
 @Test
    public  void testClass() throws ClassNotFoundException {

        Foo foo = new Foo();
        Class c = foo.getClass();
        /*
         *成员变量也是对象
         * java.lang.reflect.Field类封装了关于成员变量的操作
         * getFields()方法获取的是所有的public成员变量的方法
         * getDeclaredFields获取的是自己声明的成员变量的信息
         */
        Field[] fs = c.getDeclaredFields();
        for(Field field : fs){
            //得到成员变量的类型的类类型
            Class fieldType = field.getType();
            String typeName = fieldType.getName();
            //得到成员变量的名称
            String fieldName = field.getName();
            System.out.println(typeName+" "+fieldName);
        }
    }




  @Test
    public  void testClass() throws ClassNotFoundException {

        /*
        * 构造函数也是对象
        * java.lang.Constructor中封装了构造函数的信息
        * getContructors获取所有的public的构造函数
        * getDeclaredContructors得到了所有的构造函数
        */
        Constructor[] cs = getClass().getDeclaredConstructors();
        for(Constructor contructor : cs){
            System.out.println(contructor.getName()+"(");
            //获取构造函数的参数列表
            Class[] paramTypes = contructor.getParameterTypes();
            for(Class c2:paramTypes){
                System.out.println(c2.getName()+",");
            }

        }
    }

```



## JAVA方法的反射

⭐**如何获取某个方法**

方法的名称和参数的列表才能唯一确定某个方法

⭐**方法的反射操作**

method.invoke(对象，参数列表)