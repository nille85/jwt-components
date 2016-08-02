/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components.verifier;

import be.nille.jwt.components.model.JWT;
import be.nille.jwt.components.model.Payload;
import be.nille.jwt.components.signer.JWTPrivateKeySigner;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

/**
 * @author nholvoet
 */
public class JWTPublicKeyVerifierTest {

    private JWTPublicKeyVerifier verifier;
    private JWTPrivateKeySigner signer;

    @Test
    public void signVerifyRoundTrip() throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(1024);
        KeyPair kp = kpg.genKeyPair();
        PrivateKey privateKey = kp.getPrivate();
        PublicKey publicKey = kp.getPublic();
        signer = new JWTPrivateKeySigner(privateKey);
        verifier = new JWTPublicKeyVerifier(publicKey);
        
        Payload payload = Payload.builder()
                .withClaim("iss", "Nille")
                .withClaim("sub", "Token")
                .build();
        
        JWT jwt = signer.sign(payload);
        Payload verifiedPayload = verifier.verify(jwt);
        assertFalse(verifiedPayload.getClaims().isEmpty());
        
        
    }
}
