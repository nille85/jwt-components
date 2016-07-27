/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components;

import be.nille.jwt.components.token.JWTToken;
import be.nille.jwt.components.claim.JWTClaimStore;
import com.auth0.jwt.JWTVerifyException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.Map;

/**
 * @author nholvoet
 */
public class JWTVerifier {
    
    private final com.auth0.jwt.JWTVerifier verifier;
    
    public JWTVerifier(final String secret){
        verifier = new com.auth0.jwt.JWTVerifier(secret);
    }
    
    public JWTClaimStore verify(final JWTToken token){
        try {
            String tokenValue = token.getValue();
            Map<String, Object> claims = verifier.verify(tokenValue);
            checkExpiryDate(claims);
            ClaimConverter converter = new ClaimConverter();
            return converter.toJWTClaimStore(claims);
        } catch (IOException | GeneralSecurityException | JWTVerifyException | RuntimeException ex) {
            throw new InvalidJWTTokenException("Token could not be verified:" + ex.getMessage());
        }
        
    }
    
    private void checkExpiryDate(Map<String, Object> claims){
        Long expiryDate = (Long) claims.get("exp");
            Calendar cal = Calendar.getInstance();
            if (cal.getTimeInMillis() > expiryDate) {
                throw new ExpiredJWTTokenException("Token has expired");
            }
    }
    
    

}