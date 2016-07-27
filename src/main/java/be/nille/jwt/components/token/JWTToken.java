/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components.token;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author nholvoet
 */
@Getter
@Setter(AccessLevel.PACKAGE)
public class JWTToken {
    
    private String value;
    
    public JWTToken(final String value){
        this.value = value;
    }

}
