/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components.claim;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author nholvoet
 */
@Slf4j
@Getter
public class JWTClaim {

    private final String name;
    private final Object value;

    public JWTClaim(final String name, final Object value) {
        this.name = name;
        this.value = value;
        log.debug("Create JWT claim with name " + name + " and value " + this.value);
    }

}
