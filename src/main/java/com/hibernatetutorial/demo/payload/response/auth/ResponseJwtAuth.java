package com.hibernatetutorial.demo.payload.response.auth;

public class ResponseJwtAuth {

    private String accessToken;

    private String tokenType;

    public ResponseJwtAuth() {
    }

    public ResponseJwtAuth(String accessToken, String tokenType) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
