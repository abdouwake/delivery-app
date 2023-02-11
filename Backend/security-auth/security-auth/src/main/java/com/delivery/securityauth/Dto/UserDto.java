package com.delivery.securityauth.Dto;

import com.delivery.securityauth.user.Role;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@RequiredArgsConstructor
public class UserDto {
    private String email;
    private Role role;
    private Integer idUser;

    public UserDto(String email, Role role, Integer idUser){
        this.email=email;
        this.role=role;
        this.idUser=idUser;
    }

}
