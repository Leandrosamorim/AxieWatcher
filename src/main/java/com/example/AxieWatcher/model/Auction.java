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
public class Auction {
    
    @JsonProperty
    private String currentPriceUSD;
    
    public String getCurrentPriceUSD(){
        return this.currentPriceUSD;
    }
    
}
