
package ua.lviv.iot.exception;

public class AirlineNotFoundException extends RuntimeException {
    public AirlineNotFoundException(Integer id) {
        super("Could not find 'airline' with id = " + id);
    }
}
