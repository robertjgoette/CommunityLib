package dev.goette.exceptions;


// because this is a runtime exception there is no gurantee that it is caght and handled.
// if this is a checked exception. it must be handeld before ...?
public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(String message){
        super(message);
    }
}
