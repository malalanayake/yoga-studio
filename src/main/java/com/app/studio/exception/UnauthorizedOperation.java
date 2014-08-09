package com.app.studio.exception;

/**
 * Exception class to throw when the operation cannot proceed
 *
 * @author Yen
 */
public class UnauthorizedOperation extends Exception {

    public UnauthorizedOperation(String message) {
        super(message);
    }

}
