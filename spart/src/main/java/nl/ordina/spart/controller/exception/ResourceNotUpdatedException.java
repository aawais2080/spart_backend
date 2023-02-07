package nl.ordina.spart.controller.exception;

import java.util.UUID;

public class ResourceNotUpdatedException extends Exception {
    public static final String MESSAGE = "The requested resources cannot be updated";

    public ResourceNotUpdatedException() {
        super(MESSAGE);
    }

    public ResourceNotUpdatedException(String entityName, UUID id) {
        super(String.format("Resource %s with id %s resources cannot be updated", entityName, id));
    }
}
