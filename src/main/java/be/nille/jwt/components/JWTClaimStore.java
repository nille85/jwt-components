/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.Getter;

/**
 * @author nholvoet
 */
@Getter
public class JWTClaimStore {
    
    private final List<JWTClaim> claims;
    
    public JWTClaimStore(){
        claims = new ArrayList<>();
    }
    
    public void addClaim(JWTClaim claim){
        claims.add(claim);
    }
    
    public JWTClaim getClaim(final String name){
        for(JWTClaim claim : claims){
            if(name.equals(claim.getName())){
                return claim;
            }
        }
        throw new UnexistingJWTClaimException("The claim with name " + name + " does not exist");
    }
    
    public void removeClaim(final String name){
        boolean removed = false;
        for (Iterator<JWTClaim> iter = claims.listIterator(); iter.hasNext();) {
            JWTClaim claim =  iter.next();
            if (name.equals(claim.getName()))  {
                iter.remove(); 
                removed = true;
                break;
            }
        }
        if(!removed){
            throw new UnexistingJWTClaimException("The claim with name " + name + " does not exist");
        }
        
    }

}
