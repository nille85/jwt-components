/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components.verifier;

import com.auth0.jwt.JWTVerifyException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.util.Map;

/**
 * @author nholvoet
 */
public class JWTPublicKeyVerifier extends JWTVerifier{
    
    private final com.auth0.jwt.JWTVerifier verifier;
    
    public JWTPublicKeyVerifier(final PublicKey publicKey){
        this.verifier = new com.auth0.jwt.JWTVerifier(publicKey);
    }

    @Override
    protected Map<String, Object> internalVerify(String tokenValue) throws IOException, GeneralSecurityException, JWTVerifyException {
         return verifier.verify(tokenValue);
    }

}
