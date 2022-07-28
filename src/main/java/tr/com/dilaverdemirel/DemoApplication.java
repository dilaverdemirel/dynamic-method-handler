package tr.com.dilaverdemirel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tr.com.dilaverdemirel.handler.DemoBean;

/**
 * @author dilaverdemirel
 * @since 28/07/2022
 */

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private DemoBean demoBean;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        demoBean.test();
    }

}