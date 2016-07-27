/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components;

import be.nille.jwt.components.JWTSigner;
import be.nille.jwt.components.claim.JWTClaim;
import be.nille.jwt.components.claim.JWTClaimStore;
import be.nille.jwt.components.token.JWTToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * @author nholvoet
 */
@Slf4j
public class JWTDecoderTest {
    
    private JWTDecoder decoder;
    
    @Before
    public void setup(){
        decoder = new JWTDecoder();
    }
    
    @Test
    public void decode(){
        JWTClaimStore store = new JWTClaimStore();
        JWTClaim claim1 = new JWTClaim("iss", "Nille");
        store.addClaim(claim1);
        JWTClaim claim2 = new JWTClaim("sub", "Token");
        store.addClaim(claim2);
        JWTSigner signer = new JWTSigner("asecret");
        JWTToken token = signer.sign(store);
        String tokenToDecode = token.getValue();
        JWTClaimStore claimStore = decoder.decode(tokenToDecode);
        assertTrue(claimStore.getClaims().size() == 3);
    }
    
    @Test(expected = InvalidJWTException.class)
    public void decodeWithInvalidToken(){
        decoder.decode("klmsdfklm.lmslmsdfmlsdf");
    }
    
    @Test(expected = InvalidJWTException.class)
    public void decodeWithInvalidTokenForGson(){
        decoder.decode("klmsdfklm.lmslmsdfmlsdf.klmsdlm");
    }

}
