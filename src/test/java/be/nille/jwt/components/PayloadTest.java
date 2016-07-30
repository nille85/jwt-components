/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * @author nholvoet
 */
public class PayloadTest {
    
    Payload payload;
    
    @Before
    public void setup(){
        payload = new Payload();
    }
    
    @Test
    public void create(){
        assertTrue(payload.getClaims().isEmpty());
    }
    
    @Test
    public void add(){
        Claim claim = new Claim("iss","Nille");
        payload.addClaim(claim);
        assertTrue(payload.getClaims().size() == 1);
    }
    
    @Test
    public void get(){
        Claim claim = new Claim("iss","Nille");
        payload.addClaim(claim);
        assertEquals("Nille",payload.getClaim("iss").getValue());
    }
    
    @Test(expected = UnexistingJWTClaimException.class)
    public void getWhenNameNotFound(){
        Claim claim = new Claim("iss","Nille");
        payload.addClaim(claim);
        payload.getClaim("sub");
    }
    
    @Test
    public void hasClaim(){
        Claim claim = new Claim("iss","Nille");
        payload.addClaim(claim);
        assertTrue(payload.hasClaim("iss"));
    }
    
    @Test
    public void doesntHaveClaim(){
        Claim claim = new Claim("iss","Nille");
        payload.addClaim(claim);
        assertFalse(payload.hasClaim("dsdd"));
    }
    
    @Test
    public void remove(){
        Claim claim = new Claim("iss","Nille");
        payload.addClaim(claim);
        payload.removeClaim("iss");
        assertTrue(payload.getClaims().isEmpty());
    }
    
    @Test(expected = UnexistingJWTClaimException.class)
    public void removeWhenNameNotFound(){
        Claim claim = new Claim("iss","Nille");
        payload.addClaim(claim);
        payload.removeClaim("sub");
    }
    
    @Test
    public void addMultipleClaimsWithBuilder(){
        Payload payload = Payload.builder()
                .withClaim("iss","Nille")
                .withClaim("sub", "Token")
                .build();
        assertTrue(payload.getClaims().size() == 2);
    }

}
