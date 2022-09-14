package de.szut.webshop.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(long id) {
        super(String.format("The resource with the id '%d' couldn't be found.", id));
    }

    public ResourceNotFoundException(Class clazz, long id) {
        super(String.format(String.format("The resource of the name '%s' with the id '%d' couldn't be found.",
                clazz.getSimpleName(), id)));
    }
}
