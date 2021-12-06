/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.AxieWatcher.controller;

import com.example.AxieWatcher.model.Auth;
import com.example.AxieWatcher.model.Axie;
import com.example.AxieWatcher.model.User;
import com.example.AxieWatcher.service.UserService;
import com.example.AxieWatcher.util.UserAddCmd;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author 55229
 */
@Controller
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/fav")
    public ModelAndView getMyFavorites(HttpSession session) {
        List<Axie> axies = userService.getFavorites(session);
        ModelAndView model = new ModelAndView("favorites");
        model.addObject("axies", axies);
        return model;
    }

    /*@PostMapping("/fav")
        public boolean addFavorite(HttpSession session, @RequestParam int id, @RequestParam String img, @RequestParam String name, @RequestParam String class1, @RequestParam String price){
           boolean added = userService.addFavorite(session, id, img, name, class1, price);
            return added;
        }*/
    @RequestMapping(value = "fav", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseBody
    public boolean addFavorite(Axie axie, HttpSession session, BindingResult bindingResult) {
        /*String strId = (String) axie.getFirst("id");
            Integer id = (Integer) Integer.parseInt(strId);
            String image = (String) axie.getFirst("image");
            String name = (String) axie.getFirst("name");
            String class1 = (String) axie.getFirst("class1");
            String price = (String) axie.getFirst("price");
            Axie myAxie = new Axie(id, image, name, class1, price);*/
        boolean added = userService.addFavorite(session, axie);
        return added;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseBody
    public boolean removeFavorite(Axie axie, HttpSession session, BindingResult bindingResult) {
        /*String strId = (String) axie.getFirst("id");
            Integer id = (Integer) Integer.parseInt(strId);
            String image = (String) axie.getFirst("image");
            String name = (String) axie.getFirst("name");
            String class1 = (String) axie.getFirst("class1");
            String price = (String) axie.getFirst("price");
            Axie myAxie = new Axie(id, image, name, class1, price);*/
        boolean added = userService.removeFavorite(session, axie);
        return added;
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(Axie axie, HttpSession session, BindingResult bindingResult) {
        return "register";
    }

    /**
     *
     * @param session
     * @param user
     * @return
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "register", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String register(HttpSession session, @ModelAttribute UserAddCmd user) throws Exception {
        userService.register(user.getNewUser(), user.getNewFile());
        Auth auth = userService.auth(user.getNewUser());
        session.setAttribute("user", auth);
        session.setAttribute("pw", user.getNewUser().getPassword());
        return "redirect:/";
    }

}
