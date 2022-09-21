package de.szut.webshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for resource that couldn't be found in e.g. the db
 * should be thrown in services
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(long id) {
        super(String.format("The resource with the id '%d' couldn't be found.", id));
    }

    public ResourceNotFoundException(Class<?> clazz, String field, String identifier) {
        super(String.format(String.format("The resource of the name '%s' with the " +
                        "field '%s' with the content '%s' couldn't be found.",
                clazz.getSimpleName(), field, identifier)));
    }

    public ResourceNotFoundException(Class<?> clazz, long id) {
        super(String.format(String.format("The resource of the name '%s' with the id '%d' couldn't be found.",
                clazz.getSimpleName(), id)));
    }
}
