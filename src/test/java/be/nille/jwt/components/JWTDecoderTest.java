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
import org.junit.Test;

/**
 * @author nholvoet
 */
@Slf4j
public class JWTDecoderTest {
    
    @Test
    public void decode() throws UnsupportedEncodingException{
        JWTClaimStore store = new JWTClaimStore();
        JWTClaim claim1 = new JWTClaim("iss", "Nille");
        store.addClaim(claim1);
        JWTClaim claim2 = new JWTClaim("sub", "Token");
        store.addClaim(claim2);
        JWTSigner signer = new JWTSigner("asecret");
        JWTToken token = signer.sign(store);
        Base64 decoder = new Base64(true);
        log.debug(token.getValue());
        
        String[] tokenParts = token.getValue().split("\\.");
        log.debug("length:" + tokenParts.length);
        for(String part : tokenParts){
            log.debug(part);
        }
        
        /*
        final String jsontring = new String(decoder.decode(token.getValue()), "UTF-8");
     
       
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new StringReader(jsonString));
        reader.setLenient(true);
        JsonObject json = gson.fromJson(reader, JsonObject.class);
        for(Map.Entry<String,JsonElement> entry : json.entrySet()){
            log.debug(entry.getKey() + ":" + entry.getValue());
        }
                */
    }
    
    public void catchGroupsWithRegex(String jsonString){
        
        String pattern = "(\\{.*?\\})(\\{.*\\})(.*)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(jsonString);
        
        System.out.println(m.matches());

        
        if (m.find()) {
            System.out.println("Found value: " + m.group(1));
            System.out.println("Found value: " + m.group(2));
            System.out.println("Found value: " + m.group(3));
        } else {
            System.out.println("NO MATCH");
        }
             
    }

}
