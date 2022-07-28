package tr.com.dilaverdemirel.handler;

/**
 * @author dilaverdemirel
 * @since 28/07/2022
 */
public interface FemaleGenderInterface {
    @Gender(name = "Bayan")
    String hello(String name);
}
