/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components;

import be.nille.jwt.components.JWTSigner;
import be.nille.jwt.components.JWTClaimChecker;
import be.nille.jwt.components.claim.JWTClaim;
import be.nille.jwt.components.claim.JWTClaimStore;
import be.nille.jwt.components.claim.UnexistingJWTClaimException;
import be.nille.jwt.components.token.JWT;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * @author nholvoet
 */
public class JWTClaimCheckerTest {

    private JWTClaimChecker checker;

    @Before
    public void setup() {
        checker = new JWTClaimChecker("asecret");
    }

    @Test
    public void tokenContainsClaim() {
        JWTClaimStore store = new JWTClaimStore();
        JWTClaim claim1 = new JWTClaim("iss", "Nille");
        store.addClaim(claim1);
        JWTClaim claim2 = new JWTClaim("sub", "Token");
        store.addClaim(claim2);
        JWTClaim claimToRetrieve = new JWTClaim("sub", "Token");
        JWTSigner signer = new JWTSigner("asecret");
        JWT token = signer.sign(store);
        assertTrue(checker.tokenContainsClaim(token, claimToRetrieve));
    }

    @Test
    public void tokenDoesNotContainCorrectClaimValue() {
        JWTClaimStore store = new JWTClaimStore();
        JWTClaim claim1 = new JWTClaim("iss", "Nille");
        store.addClaim(claim1);
        JWTClaim claim2 = new JWTClaim("sub", "Token");
        store.addClaim(claim2);
        JWTClaim claimToRetrieve = new JWTClaim("sub", "Cookie");
        JWTSigner signer = new JWTSigner("asecret");
        JWT token = signer.sign(store);
        assertFalse(checker.tokenContainsClaim(token, claimToRetrieve));
    }

    @Test(expected = UnexistingJWTClaimException.class)
    public void tokenDoesNotContainClaim() {
        JWTClaimStore store = new JWTClaimStore();
        JWTClaim claim1 = new JWTClaim("iss", "Nille");
        store.addClaim(claim1);
        JWTClaim claim2 = new JWTClaim("sub", "Token");
        store.addClaim(claim2);
        JWTClaim claimToRetrieve = new JWTClaim("scope", "read");
        JWTSigner signer = new JWTSigner("asecret");
        JWT token = signer.sign(store);
        assertFalse(checker.tokenContainsClaim(token, claimToRetrieve));
    }

}
