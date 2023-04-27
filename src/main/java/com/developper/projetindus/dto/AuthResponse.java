package com.developper.projetindus.dto;

public class AuthResponse {

    public String jwt;
    private static final long serialVersionUID = -8091879091924046844L;
    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
