/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.AxieWatcher.controller;

import com.example.AxieWatcher.model.Axie;
import com.example.AxieWatcher.service.AxieService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author 55229
 */
@Controller
public class AxieController {
    @Autowired
    private final AxieService axieService;

        public AxieController(AxieService axieService) {
            this.axieService = axieService;
        }
        

        @GetMapping("/recent")
        public ModelAndView getRecentAxies(){
            List<Axie> axies = axieService.getRecentAxies();
            ModelAndView model = new ModelAndView("recentAxies");
            model.addObject("axies", axies);
            return model;
        }
        
        @GetMapping("/axie")
        public ModelAndView getAxie(@RequestParam int id){
            Axie axie = axieService.getById(id);
            ModelAndView model = new ModelAndView("detail");
            model.addObject("axie", axie);
            return model;
        }
            
            
                
            
    
}
