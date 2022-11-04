
package ua.lviv.iot.exception;

public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException(Integer id) {
        super("Could not find 'flight' with id = " + id);
    }
}
