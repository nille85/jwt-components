/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components.signer;

import be.nille.jwt.components.converter.ClaimConverter;
import be.nille.jwt.components.model.JWT;
import be.nille.jwt.components.model.Payload;
import java.util.Map;

/**
 * @author nholvoet
 */
public abstract class JWTSigner {
 
    public JWTSigner() {
    }

   
    public JWT sign(final Payload payload) {
        ClaimConverter converter = new ClaimConverter();
        Map<String, Object> claimMap = converter.toClaimMap(payload);
        String base64EncodedValue = internalSign(claimMap);
        JWT jwt = new JWT(base64EncodedValue);
        return jwt;
    }
    
    abstract protected String internalSign(Map<String,Object> claimMap);
}
