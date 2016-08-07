/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components.decoder;

import be.nille.jwt.components.decoder.JWTDecoder;
import be.nille.jwt.components.exception.InvalidJWTException;
import be.nille.jwt.components.model.Payload;
import be.nille.jwt.components.model.JWT;
import be.nille.jwt.components.model.Claim;
import be.nille.jwt.components.signer.JWTSigner;
import be.nille.jwt.components.signer.JWTSecretKeySigner;
import lombok.extern.slf4j.Slf4j;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * @author nholvoet
 */
@Slf4j
public class JWTDecoderTest {
    
    private JWTDecoder decoder;
    
    @Before
    public void setup(){
        decoder = new JWTDecoder();
    }
    
    @Test
    public void decode(){
        Payload payload = new Payload();
        Claim claim1 = new Claim("iss", "Nille");
        payload.addClaim(claim1);
        Claim claim2 = new Claim("sub", "Token");
        payload.addClaim(claim2);
        JWTSigner signer = new JWTSecretKeySigner("asecret");
        JWT token = signer.sign(payload);
        String tokenToDecode = token.getBase64EncodedValue();
        Payload decodedPayload = decoder.decode(tokenToDecode);
        assertTrue(decodedPayload.getClaims().size() == 2);
        assertEquals("Nille", decodedPayload.getClaim("iss").getValue());
        assertEquals("Token", decodedPayload.getClaim("sub").getValue());
    }
    
    @Test(expected = InvalidJWTException.class)
    public void decodeWithInvalidToken(){
        decoder.decode("klmsdfklm.lmslmsdfmlsdf");
    }
    
    @Test(expected = InvalidJWTException.class)
    public void decodeWithInvalidTokenForGson(){
        decoder.decode("klmsdfklm.lmslmsdfmlsdf.klmsdlm");
    }

}