/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components.signer;

import be.nille.jwt.components.model.Claim;
import java.util.Calendar;
import lombok.Getter;

/**
 * @author nholvoet
 */
@Getter
public class Expiration {
    
    private static final int DEFAULT_MINUTES_VALID = 1440;

    public static int minutes(int minutes) {
        return minutes;
    }
    
    private final int expirationLength;

    public Expiration(){
        this.expirationLength = DEFAULT_MINUTES_VALID;    
    }
    
    public Expiration(final int expirationLength) {
        this.expirationLength = expirationLength;
    }

    private Long getExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, expirationLength);
        return cal.getTimeInMillis();
    }
    
    public Claim createClaim(){
        Claim expirationClaim = new Claim("exp",getExpiryDate());
        return expirationClaim;
    }

}
