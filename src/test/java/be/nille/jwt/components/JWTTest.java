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
        assertNotNull(jwt.getPayload());
    }
    
    @Test
    public void hasClaim(){
        Payload payload = new Payload();
        Claim claim1 = new Claim("iss", "Nille");
        payload.addClaim(claim1);
        Claim claim2 = new Claim("sub", "Token");
        payload.addClaim(claim2);
        Claim claimToRetrieve = new Claim("sub", "Token");
        JWTSigner signer = new JWTSigner("asecret");
        JWT token = signer.sign(payload);
        assertTrue(token.hasClaim(claimToRetrieve));
    }
    
    @Test
    public void doesNotHaveClaim(){
        Payload payload = new Payload();
        Claim claim1 = new Claim("iss", "Nille");
        payload.addClaim(claim1);
        Claim claim2 = new Claim("sub", "Toke");
        payload.addClaim(claim2);
        Claim claimToRetrieve = new Claim("sub", "Token");
        JWTSigner signer = new JWTSigner("asecret");
        JWT jwt = signer.sign(payload);
        assertFalse(jwt.hasClaim(claimToRetrieve));
    }
    
    @Test(expected=UnexistingJWTClaimException.class)
    public void hasClaimWhenClaimNameNotFound(){
        Payload store = new Payload();
        Claim claim1 = new Claim("iss", "Nille");
        store.addClaim(claim1);
        Claim claim2 = new Claim("sub", "Toke");
        store.addClaim(claim2);
        Claim claimToRetrieve = new Claim("other", "Token");
        JWTSigner signer = new JWTSigner("asecret");
        JWT jwt = signer.sign(store);
        jwt.hasClaim(claimToRetrieve);
    }

}
