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
import org.junit.Test;

/**
 * @author nholvoet
 */
public class JWTTest {
    
    @Test
    public void create(){
        JWT jwt = new JWT("tokenvalue");
        assertEquals("tokenvalue",jwt.getBase64EncodedValue());
        assertNotNull(jwt.getClaimStore());
    }
    
    @Test
    public void hasClaim(){
        JWTClaimStore store = new JWTClaimStore();
        JWTClaim claim1 = new JWTClaim("iss", "Nille");
        store.addClaim(claim1);
        JWTClaim claim2 = new JWTClaim("sub", "Token");
        store.addClaim(claim2);
        JWTClaim claimToRetrieve = new JWTClaim("sub", "Token");
        JWTSigner signer = new JWTSigner("asecret");
        JWT token = signer.sign(store);
        assertTrue(token.hasClaim(claimToRetrieve));
    }
    
    @Test
    public void doesNotHaveClaim(){
        JWTClaimStore store = new JWTClaimStore();
        JWTClaim claim1 = new JWTClaim("iss", "Nille");
        store.addClaim(claim1);
        JWTClaim claim2 = new JWTClaim("sub", "Toke");
        store.addClaim(claim2);
        JWTClaim claimToRetrieve = new JWTClaim("sub", "Token");
        JWTSigner signer = new JWTSigner("asecret");
        JWT token = signer.sign(store);
        assertFalse(token.hasClaim(claimToRetrieve));
    }
    
    @Test(expected=UnexistingJWTClaimException.class)
    public void hasClaimWhenClaimNameNotFound(){
        JWTClaimStore store = new JWTClaimStore();
        JWTClaim claim1 = new JWTClaim("iss", "Nille");
        store.addClaim(claim1);
        JWTClaim claim2 = new JWTClaim("sub", "Toke");
        store.addClaim(claim2);
        JWTClaim claimToRetrieve = new JWTClaim("other", "Token");
        JWTSigner signer = new JWTSigner("asecret");
        JWT token = signer.sign(store);
        token.hasClaim(claimToRetrieve);
    }

}
