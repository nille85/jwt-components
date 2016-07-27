/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components;

import be.nille.jwt.components.claim.JWTClaim;
import be.nille.jwt.components.claim.JWTClaimStore;
import be.nille.jwt.components.token.JWTToken;
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
        JWTClaimStore claimStore = new JWTClaimStore();
        JWTClaim claim1 = new JWTClaim("iss", "Nille");
        claimStore.addClaim(claim1);
        JWTClaim claim2 = new JWTClaim("sub", "Token");
        claimStore.addClaim(claim2);
        JWTToken token = signer.sign(claimStore);
        Thread.sleep(10);
        JWTToken refreshedToken = refresher.refresh(token);
        assertNotEquals(token.getValue(), refreshedToken.getValue());
    }
}
