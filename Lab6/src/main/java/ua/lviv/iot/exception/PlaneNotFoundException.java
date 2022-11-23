package ua.lviv.iot.exception;

public class PlaneNotFoundException extends RuntimeException {
    public PlaneNotFoundException(Integer planesId){
        super("'plane' with id=" + planesId + "not found");
    }
}
