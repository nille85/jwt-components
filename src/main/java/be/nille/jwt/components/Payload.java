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
public class Payload {
    
    private final List<Claim> claims;
    
    public Payload(){
        claims = new ArrayList<>();
    }
    
    public static PayloadBuilder builder(){
        return new PayloadBuilder();
    }
    
    public void addClaim(Claim claim){
        claims.add(claim);
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
        for (Iterator<Claim> iter = claims.listIterator(); iter.hasNext();) {
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
