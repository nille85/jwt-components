/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components.verifier;

import be.nille.jwt.components.exception.InvalidJWTException;
import be.nille.jwt.components.model.JWT;
import be.nille.jwt.components.model.Payload;
import be.nille.jwt.components.signer.JWTSigner;
import be.nille.jwt.components.signer.JWTSecretKeySigner;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * @author nholvoet
 */
public class JWTSecretKeyVerifierTest {

    private JWTVerifier verifier;
    
    @Before
    public void setup(){
        verifier = new JWTSecretKeyVerifier("asecret");
    }
    
    @Test
    public void verify(){
        Payload payload = Payload.builder()
                .withClaim("iss", "Nille")
                .withClaim("sub", "Token")
                .build();
        JWTSigner signer = new JWTSecretKeySigner("asecret");
        JWT jwt = signer.sign(payload);
        Payload verifiedPayload = verifier.verify(jwt);
        assertTrue(verifiedPayload.getClaims().size() == 2);
       
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
        JWTSigner signer = new JWTSecretKeySigner("password");
        JWT token = signer.sign(payload);
        verifier.verify(token);
    }
}
