/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components.token;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * @author nholvoet
 */
public class JWTTest {
    
    @Test
    public void create(){
        JWT jwt = new JWT("tokenvalue");
        assertEquals("tokenvalue",jwt.getBase64EncodedValue());
    }

}
