/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components;

import java.util.Map;
import lombok.Setter;

/**
 * @author nholvoet
 */
public class JWTSigner {

    private final com.auth0.jwt.JWTSigner signer;

    private final Expiration expiration;

    public JWTSigner(final String secret) {
        signer = new com.auth0.jwt.JWTSigner(secret);
        expiration = new Expiration();
    }

    public JWTSigner(final String secret, final int minutesValid) {
        signer = new com.auth0.jwt.JWTSigner(secret);
        expiration = new Expiration(minutesValid);
    }

    public JWT sign(final Payload payload) {
        payload.addClaim(expiration.createClaim());
        ClaimConverter converter = new ClaimConverter();
        Map<String, Object> claimMap = converter.toClaimMap(payload);
        String base64EncodedValue = signer.sign(claimMap);
        JWT jwt = new JWT(base64EncodedValue);
        jwt.setPayload(payload);
        return jwt;
    }

}
