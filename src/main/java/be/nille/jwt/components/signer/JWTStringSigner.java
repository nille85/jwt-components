/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components.signer;

import java.util.Map;

/**
 * @author nholvoet
 */
public class JWTStringSigner extends JWTSigner {

    private final com.auth0.jwt.JWTSigner signer;
    
    public JWTStringSigner(final String secret){
        super();
        signer = new com.auth0.jwt.JWTSigner(secret);
    }

    @Override
    protected String internalSign(Map<String, Object> claimMap) {
        return signer.sign(claimMap);
    }
}
