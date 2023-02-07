package nl.ordina.spart.controller.exception;

public class ResourceNotCreatedException extends Exception {
    public static final String MESSAGE = "The requested resources cannot be created";

    public ResourceNotCreatedException() {
        super(MESSAGE);
    }

    public ResourceNotCreatedException(String entityName) {
        super(String.format("The requested '%s' resources cannot be created", entityName));
    }
}
