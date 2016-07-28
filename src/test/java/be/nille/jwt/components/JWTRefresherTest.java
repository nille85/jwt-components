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
        Payload claimStore = new Payload();
        Claim claim1 = new Claim("iss", "Nille");
        claimStore.addClaim(claim1);
        Claim claim2 = new Claim("sub", "Token");
        claimStore.addClaim(claim2);
        JWT token = signer.sign(claimStore);
        Thread.sleep(10);
        JWT refreshedToken = refresher.refresh(token);
        assertNotEquals(token.getBase64EncodedValue(), refreshedToken.getBase64EncodedValue());
    }
}
