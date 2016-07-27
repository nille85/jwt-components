/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components.token;


import be.nille.jwt.components.claim.JWTClaim;
import be.nille.jwt.components.claim.JWTClaimStore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author nholvoet
 */
@Getter
@Setter(AccessLevel.PACKAGE)
public class JWTToken {
    
    private String base64EncodedValue;
    
    public JWTToken(final String base64EncodedValue){
        this.base64EncodedValue = base64EncodedValue;
    }
    
    /*
    public containsClaim(JWTClaim claim){
        JWTClaimStore claimStore = tokenVerifier.verify(this);
        JWTClaim retrievedClaim = claimStore.getClaim(claim.getName());
        return claim.getValue().equals(retrievedClaim.getValue());
    }
    */

}
