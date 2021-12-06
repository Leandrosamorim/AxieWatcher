/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.AxieWatcher.util;

import com.example.AxieWatcher.model.User;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 55229
 */
public class UserAddCmd {
    private User newUser;
    private MultipartFile newFile;

    public UserAddCmd(User newUser, MultipartFile newFile) {
        this.newUser = newUser;
        this.newFile = newFile;
    }

    public UserAddCmd() {
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public MultipartFile getNewFile() {
        return newFile;
    }

    public void setNewFile(MultipartFile newFile) {
        this.newFile = newFile;
    }
    
    
    
}
