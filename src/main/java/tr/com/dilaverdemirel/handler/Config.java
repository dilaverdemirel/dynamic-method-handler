package tr.com.dilaverdemirel.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

/**
 * @author dilaverdemirel
 * @since 28/07/2022
 */
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
