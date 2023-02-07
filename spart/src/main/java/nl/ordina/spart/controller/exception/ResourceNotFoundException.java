package nl.ordina.spart.controller.exception;

import java.util.UUID;

public class ResourceNotFoundException extends Exception {
    public static final String MESSAGE = "The requested resources cannot be found";

    public ResourceNotFoundException() {
        super(MESSAGE);
    }

    public ResourceNotFoundException(String entityName) {
        super(String.format("The requested '%s' resources cannot be found", entityName));
    }

    public ResourceNotFoundException(String entityName, UUID id) {
        super(String.format("%s with id '%s' cannot be found", entityName, id));
    }
}
