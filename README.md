## What is JSON Web Token?

JSON Web Token (JWT) is an open standard (RFC 7519) that defines a compact and self-contained way for securely transmitting information between parties as a JSON object. This information can be verified and trusted because it is digitally signed. JWTs can be signed using a secret. More information about JWT, the structure and use cases where they can be applied can be found at https://jwt.io .

## Model
The internal model of this library has the following structure. A JWT contains a payload. The payload can contain multiple claims.

## Components
This little library contains several components that will enable you to work with JWTs in an easy to understand way.

### JWTSigner
The JWTSigner contains a method that enables you to sign a payload. The sign method requires a Payload as input. It returns a JWT that contains a Base64 encoded String representation of the JWT.
When the JWT is signed a Claim containing the expiry date is automatically added. The expiration time of a JWT can be configured by setting the minutesValid method.
In order to create a JWTSigner, you have to provide it with a secret. The secret should be the same secret that you in order to verify the token. 

You can use it in the following way:
```java
JWTSigner signer = JWTSigner("myveryverysecret");
Payload payload = Payload.builder()
                .withClaim("iss", "Nille")
                .withClaim("sub", "Token")
                .build();
JWT jwt = signer.sign(payload);
String base64EncodedValue = jwt.getBase64EncodedValue();
```

### JWTVerifier
The JWTVerifier contains the method verify. It takes a Signed JWT as input and tries to verify it. When it can verify the JWT, it returns the payload with the claims. It also checks the expiry date of the token. It throws runtime exceptions when the JWT can't be verified or when the JWT has expired.

You can use it in the following way:
```java
JWTVerifier verifier = new JWTVerifier("myveryverysecret");
Payload verifiedPayload = verifier.verify(jwt);
List<Claim> claims = verifiedPayload.getClaims();
```

### JWTDecoder
