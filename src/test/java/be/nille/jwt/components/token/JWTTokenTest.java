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
public class JWTTokenTest {
    
    @Test
    public void create(){
        JWTToken token = new JWTToken("tokenvalue");
        assertEquals("tokenvalue",token.getBase64EncodedValue());
    }

}
