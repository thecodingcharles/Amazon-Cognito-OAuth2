package com.anothercharles.oauth2.cognito.Controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/login")
public class LoginController {

     @Autowired
     RestTemplate restTemplate;
     @GetMapping(value ={"/oauth2/code/cognito","/oauth2/code/cognito/"})
     public String code(@RequestParam("code") String code) throws JsonProcessingException {
        ResponseEntity<String> response = null;
        System.out.println("Authorization Code------" + code);

        String credentials = "3phrqf57iqba577vtdvf5mjqh7:";
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        //headers.add("Authorization", "Basic " + encodedCredentials);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> request = new HttpEntity<String>(headers);

        String access_token_url = "https://movix.auth.us-west-2.amazoncognito.com/oauth2/token";
        access_token_url += "?code=" + code;
         access_token_url += "&client_id=3phrqf57iqba577vtdvf5mjqh7";

         access_token_url += "&grant_type=authorization_code";
        access_token_url += "&redirect_uri=http://localhost:8080/login/oauth2/code/cognito/";

        response = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);

        System.out.println("Access Token Response ---------" + response.getBody());

        // Get the Access Token From the recieved JSON response
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response.getBody());
       return node.toString();
        //String token = node.path("id_token").asText();

       // return token;

    }

}
