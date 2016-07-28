/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author nholvoet
 */
@Getter
@Setter(AccessLevel.PACKAGE)
public class JWT {
    
    private String base64EncodedValue;
    private Payload payload;
    
    public JWT(final String base64EncodedValue){
        this.base64EncodedValue = base64EncodedValue;
        this.payload = new Payload();
    }
    
    public boolean hasClaim(final Claim claim){
        Claim retrievedClaim = payload.getClaim(claim.getName());
        return claim.getValue().equals(retrievedClaim.getValue());
    }
    
    
}
