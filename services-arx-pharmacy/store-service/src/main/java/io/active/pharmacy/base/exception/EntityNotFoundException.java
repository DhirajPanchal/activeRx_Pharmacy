package io.active.pharmacy.base.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityType, Long entityId) {

        super(String.format("Entity Not Found : %s # %s", entityType, entityId));
    }
}
