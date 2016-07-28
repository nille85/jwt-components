## What is JSON Web Token?

JSON Web Token (JWT) is an open standard (RFC 7519) that defines a compact and self-contained way for securely transmitting information between parties as a JSON object. This information can be verified and trusted because it is digitally signed. JWTs can be signed using a secret. More information about JWT, the structure and use cases where they can be applied can be found at https://jwt.io .

## Components
This little library contains several components that will enable you to work with JWTs in an easy to understand way.

### JWTSigner

```java
JWTClaimStore claimStore = new JWTClaimStore();
JWTClaim claim = new JWTClaim("iss", "NILLE");
claimStore.addClaim(claim);
claim = new JWTClaim("sub", "JWT");
claimStore.addClaim(claim2);
JWT jwt = signer.sign(claimStore);
String jwtAsString = jwt.getBase64EncodedValue();
```

### JWTVerifier

### JWTDecoder
