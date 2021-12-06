/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.AxieWatcher.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;


/**
 *
 * @author 55229
 */

public class Axie implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @JsonProperty
    private String name;
    @JsonProperty("class")
    private String class1;
    @JsonProperty
    private String owner;
    @JsonProperty
    private Integer breedCount;
    @JsonProperty
    private String image;
    @JsonProperty
    private int userid;
    @JsonProperty
    private Auction auction;
    @JsonProperty
    private String price;
    @JsonProperty
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    

    public Axie(Integer id, String name, String class1, String image, String price) {
        this.id = id;
        this.name = name;
        this.class1 = class1;
        this.image = image;
        this.price = price;
    }
public Axie(Integer id, String name, String class1, String owner, Integer breedCount, String image, User userid, String price) {
        this.id = id;
        this.name = name;
        this.class1 = class1;
        this.owner = owner;
        this.breedCount = breedCount;
        this.image = image;
        this.price = price;
        this.userid = userid.getId();
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public Axie() {
    }

    public Axie(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClass1() {
        return class1;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getBreedCount() {
        return breedCount;
    }

    public void setBreedCount(Integer breedCount) {
        this.breedCount = breedCount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Axie)) {
            return false;
        }
        Axie other = (Axie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.AxieWatcherAPI.domain.model.Axie[ id=" + id + " ]";
    }
    
}
