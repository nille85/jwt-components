/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components;

import java.util.Calendar;
import lombok.Getter;

/**
 * @author nholvoet
 */
@Getter
public class Expiration {

    public static int minutes(int minutes) {
        return minutes;
    }

    private final Long timeInMillis;
    private final int expirationLength;

    public Expiration(final int expirationLength) {
        this.expirationLength = expirationLength;
        this.timeInMillis = getExpiryDate();
    }

    private Long getExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, expirationLength);
        return cal.getTimeInMillis();
    }

}
