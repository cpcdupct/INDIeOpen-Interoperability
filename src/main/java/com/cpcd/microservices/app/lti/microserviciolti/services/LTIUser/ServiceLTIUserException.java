package com.cpcd.microservices.app.lti.microserviciolti.services.LTIUser;

public class ServiceLTIUserException extends Exception{
    public ServiceLTIUserException(String message) {
        super(message);
    }

    public ServiceLTIUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
