/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * @author nholvoet
 */
public class ClaimTest {
    
    @Test
    public void create(){
        Claim claim = new Claim("iss", "Nille");
        assertEquals("iss",claim.getName());
        assertEquals("Nille", claim.getValue());
    }

}
