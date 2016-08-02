/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components.model;

import be.nille.jwt.components.exception.UnexistingJWTClaimException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import lombok.Getter;

/**
 * @author nholvoet
 */
@Getter
public class Payload {
    
    private final Set<Claim> claims;
    
    public Payload(){
        claims = new HashSet<>();
    }
    
    public static PayloadBuilder builder(){
        return new PayloadBuilder();
    }
    
    public void addClaim(Claim claim){
        if(hasClaim(claim.getName())){
           Claim retrievedClaim = getClaim(claim.getName());
           claims.remove(retrievedClaim);
        }
        claims.add(claim);
    }
    
    public boolean hasClaim(final String name){
        try{
            getClaim(name);
            return true;
        }catch(UnexistingJWTClaimException ex){
            return false;
        }
    }
    
    public Claim getClaim(final String name){
        for(Claim claim : claims){
            if(name.equals(claim.getName())){
                return claim;
            }
        }
        throw new UnexistingJWTClaimException("The claim with name " + name + " does not exist");
    }
    
    public void removeClaim(final String name){
        boolean removed = false;
        for (Iterator<Claim> iter = claims.iterator(); iter.hasNext();) {
            Claim claim =  iter.next();
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
    
    public static class PayloadBuilder{
        
        private final Payload payload;
        
        public PayloadBuilder(){
            payload = new Payload();
        }
        
        public PayloadBuilder withClaim(final String name, final Object value){
            Claim claim = new Claim(name, value);
            payload.addClaim(claim);
            return this;
        }
            
        public Payload build(){
            return payload;
        }
    }

    

}
