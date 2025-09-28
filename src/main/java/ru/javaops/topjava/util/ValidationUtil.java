package ru.javaops.topjava.util;

import ru.javaops.topjava.exceptions.NotFoundException;
import ru.javaops.topjava.model.BaseEntity;

public class ValidationUtil {

    private static final String ENTITY_MUST_BE_NEW = "Entity %s must be new (id = null)";
    private static final String ENTITY_MUST_BE_WITH_ANOTHER_ID = "Entity %s must be with another id: %d";

    private ValidationUtil() {}

    public static <T> T checkNotFound(T object, String message) {
        checkNotFound(object != null, message);
        return object;
    }

    public static void checkNotFound(boolean found, String message) {
        if (!found) {
            throw new NotFoundException(message);
        }
    }

    public static void checkIsNew(BaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(String.format(ENTITY_MUST_BE_NEW, entity));
        }
    }

    public static void assureIdConsistent(BaseEntity entity, long id) {
        // conservative when you reply, but accept liberally
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.getId() != id) {
            throw new IllegalArgumentException(String.format(ENTITY_MUST_BE_WITH_ANOTHER_ID, entity, id));
        }
    }

    public static void assureNotNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
