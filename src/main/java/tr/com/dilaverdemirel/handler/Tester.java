package tr.com.dilaverdemirel.handler;

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
