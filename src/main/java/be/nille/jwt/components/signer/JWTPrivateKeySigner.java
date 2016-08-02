/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components.signer;

import com.auth0.jwt.Algorithm;
import com.auth0.jwt.JWTSigner.Options;
import java.security.PrivateKey;
import java.util.Map;

/**
 * @author nholvoet
 */
public class JWTPrivateKeySigner extends JWTSigner {
    
    private final com.auth0.jwt.JWTSigner signer;
    
    public JWTPrivateKeySigner(final PrivateKey privateKey){
        super();
        signer = new com.auth0.jwt.JWTSigner(privateKey);
    }

    @Override
    protected String internalSign(Map<String, Object> claimMap) {
        final Options options = new Options();
        options.setAlgorithm(Algorithm.RS256);
        return signer.sign(claimMap,options);
    }

}
