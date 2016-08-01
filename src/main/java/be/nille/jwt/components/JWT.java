/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author nholvoet
 */
@Getter
@Setter(AccessLevel.PACKAGE)
public class JWT {
    
    private final String base64EncodedValue;
   
    
    public JWT(final String base64EncodedValue){
        this.base64EncodedValue = base64EncodedValue;
     
    }
    
    
    
    
}
