/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components.verifier;

import be.nille.jwt.components.converter.ClaimConverter;
import be.nille.jwt.components.exception.ExpiredJWTException;
import be.nille.jwt.components.exception.InvalidJWTException;
import be.nille.jwt.components.model.JWT;
import be.nille.jwt.components.model.Payload;
import com.auth0.jwt.JWTVerifyException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.Map;

/**
 * @author nholvoet
 */
public abstract class JWTVerifier {

    public Payload verify(final JWT token) {
        try {
            String tokenValue = token.getBase64EncodedValue();
            Map<String, Object> claims = internalVerify(tokenValue);
            checkExpiryDate(claims);
            ClaimConverter converter = new ClaimConverter();
            return converter.toPayload(claims);
        } catch (IOException | GeneralSecurityException | JWTVerifyException ex) {
            throw new InvalidJWTException("Token could not be verified:" + ex.getMessage());
        }

    }

    protected abstract Map<String, Object> internalVerify(final String tokenValue) throws IOException, GeneralSecurityException, JWTVerifyException;

    private void checkExpiryDate(Map<String, Object> claims) {
        Long expiryDate = (Long) claims.get("exp");
        if (expiryDate != null) {
            Calendar cal = Calendar.getInstance();
            if (cal.getTimeInMillis() > expiryDate) {
                throw new ExpiredJWTException("Token has expired");
            }
        }
    }

}
