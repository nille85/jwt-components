/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.nille.jwt.components.decoder;

import be.nille.jwt.components.exception.InvalidJWTException;
import be.nille.jwt.components.exception.ImplementationException;
import be.nille.jwt.components.model.Payload;
import be.nille.jwt.components.model.Claim;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import com.google.gson.JsonSyntaxException;

/**
 *
 * @author Niels Holvoet
 */
@Slf4j
public class JWTDecoder {
    
    public Payload decode(final String jwtAsString){
        Payload payload = new Payload();
        String payloadString = getPayloadAsString(jwtAsString);
        JsonObject jsonObject = createJsonObject(payloadString);
        for(Map.Entry<String,JsonElement> entry : jsonObject.entrySet()){
            String claimName = entry.getKey();
            JsonElement claimValue = entry.getValue();
            Gson gson = new Gson();
            Object claimObject = gson.fromJson(claimValue, Object.class);
            Claim claim = new Claim(claimName, claimObject);
            payload.addClaim(claim);
            log.debug(entry.getKey() + ":" + entry.getValue());
        }
        return payload;
    }
    
    private String getPayloadAsString(final String token){
        String[] parts = token.split("\\.");
        if(parts.length!=3){
            throw new InvalidJWTException("The provided token is not a valid token");
        }
        String payload = parts[1];
        Base64 decoder = new Base64(true);
        try{
            return new String(decoder.decode(payload), "UTF-8");
        }catch(UnsupportedEncodingException ex){
            throw new ImplementationException("The provided encoding is not supported", ex);
        }
    }
    
    private JsonObject createJsonObject(final String payloadString){
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new StringReader(payloadString));
        reader.setLenient(true);
        try{
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            return jsonObject;
        }catch(JsonSyntaxException ex){
            throw new InvalidJWTException("The provided string is not valid JSON", ex);
        }
        
    }
    
}
