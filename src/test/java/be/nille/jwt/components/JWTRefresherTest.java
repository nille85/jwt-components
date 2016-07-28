/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components;

import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * @author nholvoet
 */
public class JWTRefresherTest {

    
    private JWTRefresher refresher;
    
    @Before
    public void setup(){
        refresher = new JWTRefresher("asecret");
    }
    
    @Test
    public void refresh() throws InterruptedException{
        JWTSigner signer = new JWTSigner("asecret");
        Payload payload = new Payload();
        Claim claim1 = new Claim("iss", "Nille");
        payload.addClaim(claim1);
        Claim claim2 = new Claim("sub", "Token");
        payload.addClaim(claim2);
        JWT jwt = signer.sign(payload);
        Thread.sleep(10);
        JWT refreshedJwt = refresher.refresh(jwt);
        assertNotEquals(jwt.getBase64EncodedValue(), refreshedJwt.getBase64EncodedValue());
    }
}
