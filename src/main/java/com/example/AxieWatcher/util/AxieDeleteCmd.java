/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.AxieWatcher.util;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author 55229
 */
public class AxieDeleteCmd {
    
    @JsonProperty
    private int userId;
    @JsonProperty
    private int id;

    public AxieDeleteCmd(int userId, int id) {
        this.userId = userId;
        this.id = id;
    }

    public AxieDeleteCmd() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
