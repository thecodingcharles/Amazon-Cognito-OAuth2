package com.anothercharles.oauth2.cognito.Controller;

import com.anothercharles.oauth2.cognito.config.JwtAuthentication;
import com.oracle.javafx.jmx.json.JSONException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;
import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {


    private RestTemplate restTemplate;

    public HelloController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static HttpEntity<String> buildHttpEntity(Object body){
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type","application/json;charset=UTF-8");

            if(body == null)
                return new HttpEntity<>(headers);

            return new HttpEntity<>(body.toString(),headers);
    }

    @GetMapping(value = {"hello", ""}, produces = MediaType.TEXT_HTML_VALUE)
    public String hello(HttpServletRequest request){


        UriComponentsBuilder builder = fromHttpUrl("https://hifxxyog1h.execute-api.us-west-2.amazonaws.com/DEV/hello")
                .queryParam("name", request.getUserPrincipal().getName());

        HttpEntity<String> entity = buildHttpEntity(null);

        ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        String greeting;

        try {
            greeting = new JSONObject(response.getBody()).getString("message");

        } catch (JSONException e) {
            return null;
        }

        return "<h1>"+ greeting+"</h1>";



    }

}
