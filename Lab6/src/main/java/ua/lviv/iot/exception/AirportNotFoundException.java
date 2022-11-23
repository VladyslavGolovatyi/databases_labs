package ua.lviv.iot.exception;

public class AirportNotFoundException extends RuntimeException {
    public AirportNotFoundException(Integer id) {
        super("Could not find 'airport' with id = " + id);
    }

}
