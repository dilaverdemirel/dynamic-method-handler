# dynamic-method-handler
It shows that OpenFeign how is work and how integrate with Spring Framework.

**"Feign makes writing java http clients easier"**

### Feign, How makes it this?

There is a pattern when making http request. There is a pattern when making http request. That are Marshalling and request and response 
and unmarshalling.

Basically, Feing uses the **java.lang.reflect.Proxy** class for make http requests easier. 

"Proxy provides static methods for creating objects that act like instances of interfaces but allow for customized method invocation.
A proxy class is a class created at runtime that implements a specified list of interfaces, known as proxy interfaces. A proxy instance is 
an instance of a proxy class. Each proxy instance has an associated invocation handler object, which implements the 
interface InvocationHandler. "

#### I need an Interface;

```java
public interface MaleGenderInterface {
    @Gender(name = "Bay")
    String hello(String name);
}

public interface FemaleGenderInterface {
    @Gender(name = "Bayan")
    String hello(String name);
}
```
**@Gender** provides parameterization to us.

#### Next one is InvocationHandler;
```java
public class CustomInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (args != null) {
            for (Object arg : args)
                System.out.println("args = " + arg);
        }

        final var methodAnnotation = method.getAnnotation(Gender.class);

        System.out.println("Invoked method:" + method.getName());
        return "Merhaba " + methodAnnotation.name() + " " + args[0] + "!";
    }
}
```
**CustomInvocationHandler** handles method invocation of Interfaces. We can get additional data from method annotations. 
For example with **@Gender**.

#### Tester
```java
import java.lang.reflect.Proxy;

/**
 * @author dilaverdemirel
 * @since 28/07/2022
 */
public class Tester {
    public static void main(String[] args) {
        MaleGenderInterface maleGenderInterface = (MaleGenderInterface) Proxy.newProxyInstance(
                MaleGenderInterface.class.getClassLoader(),
                new Class[]{MaleGenderInterface.class},
                new CustomInvocationHandler());
        System.out.println("result = " + maleGenderInterface.hello("Dilaver"));

        FemaleGenderInterface femaleGenderInterface = (FemaleGenderInterface) Proxy.newProxyInstance(
                FemaleGenderInterface.class.getClassLoader(),
                new Class[]{FemaleGenderInterface.class},
                new CustomInvocationHandler());
        System.out.println("result = " + femaleGenderInterface.hello("Derya"));
    }
}
```
I am binding Interface and InvocationHandler, above and invoke hello methods.

**Output:**
```text
args = Dilaver
Invoked method:hello
result = Merhaba Bay Dilaver!
args = Derya
Invoked method:hello
result = Merhaba Bayan Derya!
```

## Spring Integration
```java
@Configuration
public class Config {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public MaleGenderInterface maleGenderInterface() {
        return (MaleGenderInterface) Proxy.newProxyInstance(
                MaleGenderInterface.class.getClassLoader(),
                new Class[]{MaleGenderInterface.class},
                new CustomInvocationHandler());
    }

    @Bean
    public FemaleGenderInterface femaleGenderInterface() {
        return (FemaleGenderInterface) Proxy.newProxyInstance(
                FemaleGenderInterface.class.getClassLoader(),
                new Class[]{FemaleGenderInterface.class},
                new CustomInvocationHandler());
    }
}

@Component
public class DemoBean {
    @Autowired
    private MaleGenderInterface maleGenderInterface;

    @Autowired
    private FemaleGenderInterface femaleGenderInterface;

    public void test() {
        System.out.println(maleGenderInterface.hello("Dilaver"));
        System.out.println(femaleGenderInterface.hello("Derya"));
    }
}
```

**Output:**
```text
args = Dilaver
Invoked method:hello
Merhaba Bay Dilaver!
args = Derya
Invoked method:hello
Merhaba Bayan Derya!
```