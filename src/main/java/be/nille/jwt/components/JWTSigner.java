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

    private static final int DEFAULT_MINUTES_VALID = 1440;
    
    private final com.auth0.jwt.JWTSigner signer;
    @Setter
    private int minutesValid;
    
    public JWTSigner(final String secret){
        signer = new com.auth0.jwt.JWTSigner(secret);
        minutesValid = DEFAULT_MINUTES_VALID;
    }
    
    public JWT sign(final JWTClaimStore claimStore) {
        JWTClaim expirationClaim = createExpirationClaim();
        claimStore.addClaim(expirationClaim);
        ClaimConverter converter = new ClaimConverter();
        Map<String,Object> claimMap = converter.toClaimMap(claimStore);
        String base64EncodedValue = signer.sign(claimMap);
        JWT jwt = new JWT(base64EncodedValue);
        jwt.setClaimStore(claimStore);
        return jwt;
    }
    
    private JWTClaim createExpirationClaim(){
        Expiration expiration = new Expiration(Expiration.minutes(minutesValid));
        JWTClaim expirationClaim = new JWTClaim("exp",expiration.getTimeInMillis());
        return expirationClaim;
    }
    
    
}
