/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components.claim;

/**
 * @author nholvoet
 */
public class UnexistingJWTClaimException extends RuntimeException {

    public UnexistingJWTClaimException(final String message) {
        super(message);
    }

}
