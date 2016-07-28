/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components;

/**
 * @author nholvoet
 */
public class JWTRefresher {

    private final JWTSigner signer;
    private final JWTVerifier verifier;
    private final Expiration expiration;

    public JWTRefresher(final String secret) {
        this.signer = new JWTSigner(secret);
        this.verifier = new JWTVerifier(secret);
        expiration = new Expiration();
    }
    
    public JWTRefresher(final String secret, final int minutesValid) {
        this.signer = new JWTSigner(secret);
        this.verifier = new JWTVerifier(secret);
        expiration = new Expiration(minutesValid);
    }

    public JWT refresh(final JWT jwt) {
        Payload payload = verifier.verify(jwt);
        payload.addClaim(expiration.createClaim());
        return signer.sign(payload);
    }
    
    

}
