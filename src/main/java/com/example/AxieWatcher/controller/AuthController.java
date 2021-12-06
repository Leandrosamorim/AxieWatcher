/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.AxieWatcher.controller;

import com.example.AxieWatcher.model.Auth;
import com.example.AxieWatcher.model.User;
import com.example.AxieWatcher.service.UserService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author 55229
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    
    
     @RequestMapping(value="/signin",method=RequestMethod.GET)
    public String signin(){
        return "signin";
    }
    
    @RequestMapping(value="/signout",method=RequestMethod.GET)
    public String signout(HttpSession session){
        session.setAttribute("user", null);
        return "index";
    }
    @RequestMapping(value="/signin/validate",method=RequestMethod.POST)
    public String validate(HttpSession session, User user) throws Exception{
        Auth auth = userService.auth(user);
        Assert.notNull(auth, "Falha no login");
        session.setAttribute("user", auth);
        session.setAttribute("pw", user.getPassword());
        
        return "index";
    }
}
