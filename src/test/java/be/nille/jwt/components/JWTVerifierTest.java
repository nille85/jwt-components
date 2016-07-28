/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components;

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
        Payload payload = Payload.builder()
                .withClaim("iss", "Nille")
                .withClaim("sub", "Token")
                .build();
        JWTSigner signer = new JWTSigner("asecret");
        JWT jwt = signer.sign(payload);
        Payload verifiedPayload = verifier.verify(jwt);
        assertTrue(verifiedPayload.getClaims().size() == 3);
       
    }
    
    @Test(expected = InvalidJWTException.class )
    public void testVerifyTokensThrowsInvalidTokenException(){
        JWT jwt = new JWT("klmsdlmdfs.lmdkfd.klmsdflm");
        verifier.verify(jwt);
    }
    
    @Test(expected = InvalidJWTException.class )
    public void verifyWhenSignedWithOtherSecret(){
        Payload payload = Payload.builder()
                .withClaim("iss", "Nille")
                .withClaim("sub", "Token")
                .build();
        JWTSigner signer = new JWTSigner("password");
        JWT token = signer.sign(payload);
        verifier.verify(token);
    }
}
