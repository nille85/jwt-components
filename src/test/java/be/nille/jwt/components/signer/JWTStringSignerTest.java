/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components.signer;

import be.nille.jwt.components.model.JWT;
import be.nille.jwt.components.model.Payload;
import lombok.extern.slf4j.Slf4j;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 * @author nholvoet
 */
@Slf4j
public class JWTStringSignerTest {

    private JWTStringSigner signer;

    @Before
    public void setup() {
        
        signer = new JWTStringSigner("asecret");
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
                .withExpirationInMinutes(60)
                .build();
       
        JWT jwt = signer.sign(payload);
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
        assertEquals(jwt1.getBase64EncodedValue(), jwt2.getBase64EncodedValue());
    }

}
