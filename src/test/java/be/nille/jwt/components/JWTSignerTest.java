/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components;

import be.nille.jwt.components.claim.JWTClaim;
import be.nille.jwt.components.claim.JWTClaimStore;
import be.nille.jwt.components.token.JWTToken;
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
        JWTClaimStore claimStore = new JWTClaimStore();
        JWTClaim claim1 = new JWTClaim("iss", "Nille");
        claimStore.addClaim(claim1);
        JWTClaim claim2 = new JWTClaim("sub", "Token");
        claimStore.addClaim(claim2);
        JWTToken token = signer.sign(claimStore);
        assertNotNull(token);
        log.debug(token.getBase64EncodedValue());
    }

    @Test
    public void testSignMultipleTimes() throws InterruptedException {
        JWTClaimStore claimStore = new JWTClaimStore();
        JWTClaim claim1 = new JWTClaim("iss", "Nille");
        claimStore.addClaim(claim1);
        JWTClaim claim2 = new JWTClaim("sub", "Token");
        claimStore.addClaim(claim2);
        JWTToken token1 = signer.sign(claimStore);
        Thread.sleep(10);
        JWTToken token2 = signer.sign(claimStore);
        assertNotEquals(token1.getBase64EncodedValue(), token2.getBase64EncodedValue());
    }

}
