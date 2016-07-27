/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components;

import be.nille.jwt.components.token.JWT;
import be.nille.jwt.components.claim.JWTClaimStore;

/**
 * @author nholvoet
 */
public class JWTRefresher {

    private final JWTSigner signer;
    private final JWTVerifier verifier;

    public JWTRefresher(final String secret) {
        this.signer = new JWTSigner(secret);
        this.verifier = new JWTVerifier(secret);
    }

    public JWT refresh(final JWT token) {
        JWTClaimStore claimStore = verifier.verify(token);
        return signer.sign(claimStore);
    }

}
