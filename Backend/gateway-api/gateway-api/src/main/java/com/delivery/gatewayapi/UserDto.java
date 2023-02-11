package com.delivery.gatewayapi;

import com.delivery.gatewayapi.Exception.ApiRequestException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Getter
@Setter
public class UserDto {
    private String email;
    private String role;
    private Integer idUser;

    UserDto(String email,String role){
        this.email=email;
        this.role=role;
    }

    UserDto(){
    }

    public static UserDto getUserDto(String token) {
        HashMap<String,String> uriVariables = new HashMap<>();
        uriVariables.put("token",token);
        ResponseEntity<UserDto> responseEntity;
        try{
            responseEntity = new RestTemplate().postForEntity("http://localhost:49156/api/v1/auth/validateToken",uriVariables,UserDto.class);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        return responseEntity.getBody();
    }
}
