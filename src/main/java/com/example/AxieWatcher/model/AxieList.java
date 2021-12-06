/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.AxieWatcher.model;

import java.util.ArrayList;


/**
 *
 * @author 55229
 */
public class AxieList {
    
    private ArrayList<Axie> axies;

    public AxieList() {
    }

    public ArrayList<Axie> getAxies() {
        return axies;
    }

    public void setAxies(ArrayList<Axie> axies) {
        this.axies = axies;
    }

    public AxieList(ArrayList<Axie> axies) {
        this.axies = axies;
    }
    
}
