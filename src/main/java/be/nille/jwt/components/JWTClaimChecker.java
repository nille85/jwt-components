/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components;

import be.nille.jwt.components.claim.JWTClaim;
import be.nille.jwt.components.claim.JWTClaimStore;
import be.nille.jwt.components.token.JWTToken;
import lombok.extern.slf4j.Slf4j;

/**
 * @author nholvoet
 */
@Slf4j
public class JWTClaimChecker {
    
    private final JWTVerifier tokenVerifier;

    public JWTClaimChecker(final String secret) {
        this.tokenVerifier = new JWTVerifier(secret);
    }

    
    public boolean tokenContainsClaim(JWTToken token, final JWTClaim claim) {
        JWTClaimStore claimStore = tokenVerifier.verify(token);
        JWTClaim retrievedClaim = claimStore.getClaim(claim.getName());
        return claim.getValue().equals(retrievedClaim.getValue());
    }
    

}
