/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.AxieWatcher.service;

import com.example.AxieWatcher.model.Auth;
import com.example.AxieWatcher.model.Axie;
import com.example.AxieWatcher.model.AxieList;
import com.example.AxieWatcher.util.AxieDeleteCmd;
import java.util.Base64;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author 55229
 */
@Service
public class AxieService {

    public List<Axie> getRecentAxies() {
        RestTemplate template = new RestTemplate();
        ParameterizedTypeReference<List<Axie>> typeRef = new ParameterizedTypeReference<List<Axie>>() {
        };
        HttpEntity<Axie> request = new HttpEntity<>(new Axie());

        ResponseEntity<List<Axie>> responseEntity = template.exchange("http://localhost:8081/recent", HttpMethod.GET, request, typeRef);
        List<Axie> axies = responseEntity.getBody();
        for (Axie axie : axies) {
            axie.setPrice(axie.getAuction().getCurrentPriceUSD());
        }

        /*List<Axie> recentAxies = template.getForObject(, List.class);
        List<Axie> axies = recentAxies;*/
        return axies;
    }

    public Axie getById(int id) {
        try {
            String url = String.format("http://localhost:8081/get?id=%s", id);

            RestTemplate template = new RestTemplate();
            Axie axie = template.getForObject(url, Axie.class);

            return axie;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
