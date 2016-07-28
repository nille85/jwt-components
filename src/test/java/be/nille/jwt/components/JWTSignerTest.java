/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components;

import lombok.extern.slf4j.Slf4j;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 * @author nholvoet
 */
@Slf4j
public class JWTSignerTest {

    private JWTSigner signer;

    @Before
    public void setup() {
        signer = new JWTSigner("asecret");
    }

    @Test
    public void testSign() {
        Payload payload = Payload.builder()
                .withClaim("iss", "Nille")
                .withClaim("sub", "Token")
                .build();
        JWT jwt = signer.sign(payload);
        assertNotNull(jwt);
        log.debug(jwt.getBase64EncodedValue());
    }
    
    @Test
    public void testSignWithExpiration() {
        Payload payload = Payload.builder()
                .withClaim("iss", "Nille")
                .withClaim("sub", "Token")
                .build();
        JWTSigner signerWithCustomExpiration = new JWTSigner("asecret", 60);
        JWT jwt = signerWithCustomExpiration.sign(payload);
        assertNotNull(jwt);
        log.debug(jwt.getBase64EncodedValue());
    }

    @Test
    public void testSignMultipleTimes() throws InterruptedException {
        Payload payload = Payload.builder()
                .withClaim("iss", "Nille")
                .withClaim("sub", "Token")
                .build();
        JWT jwt1 = signer.sign(payload);
        Thread.sleep(10);
        JWT jwt2 = signer.sign(payload);
        assertNotEquals(jwt1.getBase64EncodedValue(), jwt2.getBase64EncodedValue());
    }

}
