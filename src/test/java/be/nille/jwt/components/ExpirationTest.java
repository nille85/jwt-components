/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components;

import be.nille.jwt.components.Expiration;
import java.util.Date;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * @author nholvoet
 */
public class ExpirationTest {
    
    @Test
    public void testCreateExpiration(){
        Expiration expiration = new Expiration(Expiration.minutes(60));
        assertNotNull(expiration);
    }
   

}
