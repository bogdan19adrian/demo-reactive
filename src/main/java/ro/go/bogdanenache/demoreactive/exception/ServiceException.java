package ro.go.bogdanenache.demoreactive.exception;

public class ServiceException  extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }
}