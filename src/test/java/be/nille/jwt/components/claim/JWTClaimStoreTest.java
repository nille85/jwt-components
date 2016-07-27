/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components.claim;

import be.nille.jwt.components.claim.JWTClaimStore;
import be.nille.jwt.components.claim.JWTClaim;
import be.nille.jwt.components.claim.UnexistingJWTClaimException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * @author nholvoet
 */
public class JWTClaimStoreTest {
    
    JWTClaimStore store;
    
    @Before
    public void setup(){
        store = new JWTClaimStore();
    }
    
    @Test
    public void create(){
        assertTrue(store.getClaims().isEmpty());
    }
    
    @Test
    public void add(){
        JWTClaim claim = new JWTClaim("iss","Nille");
        store.addClaim(claim);
        assertTrue(store.getClaims().size() == 1);
    }
    
    @Test
    public void get(){
        JWTClaim claim = new JWTClaim("iss","Nille");
        store.addClaim(claim);
        assertEquals("Nille",store.getClaim("iss").getValue());
    }
    
    @Test(expected = UnexistingJWTClaimException.class)
    public void getWhenNameNotFound(){
        JWTClaim claim = new JWTClaim("iss","Nille");
        store.addClaim(claim);
        store.getClaim("sub");
    }
    
    @Test
    public void remove(){
        JWTClaim claim = new JWTClaim("iss","Nille");
        store.addClaim(claim);
        store.removeClaim("iss");
        assertTrue(store.getClaims().isEmpty());
    }
    
    @Test(expected = UnexistingJWTClaimException.class)
    public void removeWhenNameNotFound(){
        JWTClaim claim = new JWTClaim("iss","Nille");
        store.addClaim(claim);
        store.removeClaim("sub");
    }

}
