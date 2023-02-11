package com.delivery.securityauth.auth;

import com.delivery.securityauth.user.Role;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private Integer phoneNumber;
    private String password;
}
