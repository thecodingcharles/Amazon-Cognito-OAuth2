
package com.anothercharles.oauth2.cognito.config;

 //Request scoped bean that holds the IDToken associated with the user.

public class JwtIdTokenCredentialsHolder {

    public String getIdToken() {
        return idToken;
    }

    public JwtIdTokenCredentialsHolder setIdToken(String idToken) {
        this.idToken = idToken;
        System.out.println(idToken);
        return this;
    }

    private String idToken;

}
