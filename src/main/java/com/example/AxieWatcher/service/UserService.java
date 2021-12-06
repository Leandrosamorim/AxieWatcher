/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.AxieWatcher.service;

import com.example.AxieWatcher.model.Auth;
import com.example.AxieWatcher.model.Axie;
import com.example.AxieWatcher.model.User;
import com.example.AxieWatcher.util.AxieDeleteCmd;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.javac.util.Assert;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 55229
 */
@Service
public class UserService {

    public Auth auth(User user) {
        RestTemplate restTemplate = new RestTemplate();
        String username = user.getUsername();
        Auth authUser = restTemplate.getForObject("http://localhost:8081/auth?username={username}", Auth.class, username);
        Assert.checkNonNull(user);
        Assert.checkNonNull(authUser, "Usuario nao encontrado");
        String pass = user.getPassword();
        if (user.getUsername().equals(authUser.getUsername())) {
            if (new BCryptPasswordEncoder().matches(pass, authUser.getPassword())) {
                return authUser;
            }
        }
        Auth invalidAuth = new Auth();
        invalidAuth = null;
        return invalidAuth;
    }

    public List<Axie> getFavorites(HttpSession session) {
        try {
            // request url
            String url = "http://localhost:8081/favorites?userid=";

            // create auth credentials
            Auth auth = (Auth) session.getAttribute("user");
            String username = auth.getUsername();
            String password = (String) session.getAttribute("pw");

            String authStr = String.format("%s:%s", username, password);
            String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

            // create headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);

            // create request
            HttpEntity request = new HttpEntity(headers);
            ParameterizedTypeReference<List<Axie>> typeRef = new ParameterizedTypeReference<List<Axie>>() {
            };

            // make a request
            ResponseEntity<List<Axie>> response = new RestTemplate().exchange(url + auth.getId(), HttpMethod.GET, request, typeRef);

            // get JSON response
            List<Axie> favorites = response.getBody();
            return favorites;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /* RestTemplate restTemplate = new RestTemplate();
        Auth user = (Auth) session.getAttribute("user");
        String username = user.getUsername();
        String password = user.getPassword();
        String id = user.getId().toString();
        ParameterizedTypeReference<List<Axie>> typeRef = new ParameterizedTypeReference<List<Axie>>() {};
        HttpEntity<Axie> request = new HttpEntity<>(new Axie());

        ResponseEntity<List<Axie>> responseEntity = restTemplate.exchange("http://localhost:8081/recent", HttpMethod.GET, request, typeRef);
        
        List<Axie> favorites = responseEntity.getBody();
        return favorites;*/
    public boolean addFavorite(HttpSession session, Axie axie) {
        try {
            // request url
            String url = "http://localhost:8081/axies";

            // create auth credentials
            Auth auth = (Auth) session.getAttribute("user");
            String username = auth.getUsername();
            String password = (String) session.getAttribute("pw");
            String role = auth.getRoles();
            int id = Integer.parseInt(auth.getId());
            axie.setUserid(id);

            String authStr = String.format("%s:%s", username, password);
            String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

            // create headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);
            headers.setContentType(MediaType.APPLICATION_JSON);

            // create request
            HttpEntity<Axie> entity;
            entity = new HttpEntity<>(axie, headers);

            // make a request
            ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.POST, entity, String.class);
            boolean created = response.getBody().equals("true");

            // get JSON response
            return created;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public boolean removeFavorite(HttpSession session, Axie axie) {
        try {
            // request url

            // create auth credentials
            Auth auth = (Auth) session.getAttribute("user");
            String username = auth.getUsername();
            String password = (String) session.getAttribute("pw");
            String role = auth.getRoles();
            int userId = Integer.parseInt(auth.getId());
            int axieId = axie.getId();
            AxieDeleteCmd cmd = new AxieDeleteCmd(userId, axieId);

            String authStr = String.format("%s:%s", username, password);
            String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

            // create headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);

            // create request
            HttpEntity request = new HttpEntity(headers);

            String url = String.format("http://localhost:8081/axies?id=%s&userid=%s", axieId, userId);

            // make a request
            ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.DELETE, request, String.class);
            boolean created = response.getBody().equals("true");

            // get JSON response
            return created;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public boolean register(User user, MultipartFile file) throws Exception {
        user.setRoles("USER");
        MultiValueMap<String, Object> multipartRequest = new LinkedMultiValueMap<>();

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);//Main request's headers

        HttpHeaders requestHeadersAttachment = new HttpHeaders();
        requestHeadersAttachment.setContentType(MediaType.IMAGE_PNG);// extract mediatype from file extension
        HttpEntity<ByteArrayResource> attachmentPart;
        ByteArrayResource fileAsResource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };
        attachmentPart = new HttpEntity<>(fileAsResource, requestHeadersAttachment);

        multipartRequest.set("newFile", attachmentPart);

        HttpHeaders requestHeadersJSON = new HttpHeaders();
        requestHeadersJSON.setContentType(MediaType.APPLICATION_JSON);
        User requestBody = user;
        HttpEntity<User> requestEntityJSON = new HttpEntity<>(requestBody, requestHeadersJSON);

        multipartRequest.set("newUser", requestEntityJSON);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(multipartRequest, requestHeaders);//final request 

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8081/users", HttpMethod.POST, requestEntity, String.class);
        return response.getBody().equals("true");
    }

    /*try {
            // request url
            String url = "http://localhost:8081/users";
            user.setRoles("USER");
              MultipartFile newFile = file;
  MultiValueMap<String, Object> parts = 
          new LinkedMultiValueMap<String, Object>();
  parts.add("newFile", new ByteArrayResource(file.getBytes()));
  parts.add("newUser", user);
  
   RestTemplate restTemplate = new RestTemplate();
  HttpHeaders headers = new HttpHeaders();
  headers.setContentType(MediaType.MULTIPART_FORM_DATA);

  HttpEntity<MultiValueMap<String, Object>> requestEntity =
          new HttpEntity<MultiValueMap<String, Object>>(parts, headers);

  ResponseEntity<String> response =
          restTemplate.exchange("http://localhost:8081/users", 
                  HttpMethod.POST, requestEntity, String.class);

  if (response != null) {
    return response.getBody();
  }

  return "error";
            
            
            
            // create headers
            
           /* HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            
            MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
            MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
            multiValueMap.add("Content-Type", "multipart/form-data");
            
            MultiValueMap<String, Object> body
  = new LinkedMultiValueMap<>();
body.add("newUser", user);
body.add("newFile", file);

HttpEntity<MultiValueMap<String, Object>> requestEntity
  = new HttpEntity<>(body, headers);

RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> response = restTemplate
  .postForEntity(url, requestEntity, String.class);
boolean result = response.getStatusCode().equals(200);*/
 /*headers.addAll(multiValueMap);
            
              ObjectMapper objectMapper = new ObjectMapper();
    String jsonInString = objectMapper.writeValueAsString(user);
    params.add("newUser", jsonInString);
    params.add("newFile", file);

            // make a request
            RestTemplate template = new RestTemplate();
            template.postForObject(url, headers, String.class);
            ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.POST, params, String.class);
            boolean created = response.getBody().equals("true");

            // get JSON response*/
 /* } catch (Exception ex) {
            throw ex;
        }
    }*/
}
