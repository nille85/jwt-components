/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components.model;

import be.nille.jwt.components.model.JWT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
