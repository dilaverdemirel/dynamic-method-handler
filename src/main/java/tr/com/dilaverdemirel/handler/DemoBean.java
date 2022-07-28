package tr.com.dilaverdemirel.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dilaverdemirel
 * @since 28/07/2022
 */
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
