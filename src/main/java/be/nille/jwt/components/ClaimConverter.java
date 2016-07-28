/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nholvoet
 */
public class ClaimConverter {
    
    public Payload toJWTClaimStore(final Map<String,Object> claimMap){
        Payload store = new Payload();
        for(Map.Entry<String,Object> claim : claimMap.entrySet()){
            Claim jwtClaim = new Claim(claim.getKey(), claim.getValue());
            store.addClaim(jwtClaim);
        }
        return store;
    }
    
    public Map<String,Object> toClaimMap(final Payload claimStore){
        Map<String,Object> claimMap = new HashMap<>();
        for(Claim claim : claimStore.getClaims()){
            claimMap.put(claim.getName(), claim.getValue());
        }
        return claimMap;
    }

}
