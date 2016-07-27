/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components;

import be.nille.jwt.components.claim.JWTClaim;
import be.nille.jwt.components.claim.JWTClaimStore;
import be.nille.jwt.components.token.JWT;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * @author nholvoet
 */
public class JWTVerifierTest {

    private JWTVerifier verifier;
    
    @Before
    public void setup(){
        verifier = new JWTVerifier("asecret");
    }
    
    @Test
    public void verify(){
        JWTClaimStore store = new JWTClaimStore();
        JWTClaim claim1 = new JWTClaim("iss", "Nille");
        store.addClaim(claim1);
        JWTClaim claim2 = new JWTClaim("sub", "Token");
        store.addClaim(claim2);
        JWTSigner signer = new JWTSigner("asecret");
        JWT token = signer.sign(store);
        JWTClaimStore verifiedStore = verifier.verify(token);
        assertTrue(verifiedStore.getClaims().size() == 3);
       
    }
    
    @Test(expected = InvalidJWTException.class )
    public void testVerifyTokensThrowsInvalidTokenException(){
        JWT token = new JWT("klmsdlmdfs.lmdkfd.klmsdflm");
        verifier.verify(token);
    }
    
    @Test(expected = InvalidJWTException.class )
    public void verifyWhenSignedWithOtherSecret(){
        JWTClaimStore store = new JWTClaimStore();
        JWTClaim claim1 = new JWTClaim("iss", "Nille");
        store.addClaim(claim1);
        JWTClaim claim2 = new JWTClaim("sub", "Token");
        store.addClaim(claim2);
        JWTSigner signer = new JWTSigner("password");
        JWT token = signer.sign(store);
        verifier.verify(token);
    }
}
