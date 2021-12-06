/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.AxieWatcher.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author 55229
 */
public class Auth {

    @JsonProperty
    private String id;
    @JsonProperty
    private String roles;
    @JsonProperty
    private String username;
    @JsonProperty
    private String password;

    public Auth() {
    }

    public Auth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Auth(String id, String roles, String username, String password) {
        this.id = id;
        this.roles = roles;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
