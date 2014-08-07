/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.studio.exception;

/**
 * Exception class which is going to tell that Record already exist
 *
 * @author dmalalanayake
 */
public class RecordAlreadyExistException extends Exception {

    public RecordAlreadyExistException(String message) {
        super(message);
    }

}
