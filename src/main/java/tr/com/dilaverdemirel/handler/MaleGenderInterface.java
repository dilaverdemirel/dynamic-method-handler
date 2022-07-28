package tr.com.dilaverdemirel.handler;

/**
 * @author dilaverdemirel
 * @since 28/07/2022
 */
public interface MaleGenderInterface {
    @Gender(name = "Bay")
    String hello(String name);
}
