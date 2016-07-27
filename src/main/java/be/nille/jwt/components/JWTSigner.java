/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components;

import be.nille.jwt.components.token.JWTToken;
import be.nille.jwt.components.claim.JWTClaim;
import be.nille.jwt.components.claim.JWTClaimStore;
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
    
    public JWTToken sign(final JWTClaimStore claimStore) {
        JWTClaim expirationClaim = createExpirationClaim();
        claimStore.addClaim(expirationClaim);
        ClaimConverter converter = new ClaimConverter();
        Map<String,Object> claimMap = converter.toClaimMap(claimStore);
        String signedValue = signer.sign(claimMap);
        JWTToken token = new JWTToken(signedValue);
        return token;
    }
    
    private JWTClaim createExpirationClaim(){
        Expiration expiration = new Expiration(Expiration.minutes(minutesValid));
        JWTClaim expirationClaim = new JWTClaim("exp",expiration.getTimeInMillis());
        return expirationClaim;
    }
    
    
}
