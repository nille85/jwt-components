/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components;

/**
 * @author nholvoet
 */
public class InvalidJWTTokenException extends RuntimeException {

    public InvalidJWTTokenException(final String message) {
        super(message);
    }

}
