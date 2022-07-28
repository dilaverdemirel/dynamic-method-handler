package tr.com.dilaverdemirel.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author dilaverdemirel
 * @since 28/07/2022
 */
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