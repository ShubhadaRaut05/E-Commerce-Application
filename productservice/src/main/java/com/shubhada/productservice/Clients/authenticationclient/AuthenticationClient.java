package com.shubhada.productservice.Clients.authenticationclient;

import com.shubhada.productservice.Clients.authenticationclient.dtos.ValidateTokenRequestDto;
import com.shubhada.productservice.Clients.authenticationclient.dtos.ValidateTokenResponseDto;
import com.shubhada.productservice.Clients.fakestoreapi.FakeStoreProductDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class AuthenticationClient {

    private RestTemplateBuilder restTemplateBuilder;
    public AuthenticationClient(RestTemplateBuilder restTemplateBuilder){

        this.restTemplateBuilder=restTemplateBuilder;
    }
    //validate token and get the details of that user
    public ValidateTokenResponseDto validate(String token,Long userId)
    {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ValidateTokenRequestDto request=new ValidateTokenRequestDto();
        request.setToken(token);
        request.setUserId(userId);
        ResponseEntity<ValidateTokenResponseDto> l= restTemplate.postForEntity(
                "http://localhost:9000/auth/validate",
               request  ,
               ValidateTokenResponseDto.class
        );
        return l.getBody();
    }
    public void getUserDetails(String token){

    }
}
