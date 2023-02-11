package com.delivery.securityauth.auth;

import com.delivery.securityauth.Dto.AuthenticationRequest;
import com.delivery.securityauth.Dto.AuthenticationResponse;
import com.delivery.securityauth.Dto.UserDto;
import com.delivery.securityauth.Dto.tokenValidation;
import com.delivery.securityauth.Exception.ApiRequestException;
import com.delivery.securityauth.config.JwtService;
import com.delivery.securityauth.user.Role;
import com.delivery.securityauth.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {


    final AuthenticationService service;
    private final JwtService jwtService;
    @Autowired
    private UserService userService;
    private final UserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        try {
            return ResponseEntity.ok(service.authenticate(request));
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PostMapping("/validateToken")
    public UserDto validateToken(
            @RequestBody tokenValidation token
    ) {
        String userEmail="";
        try {
            userEmail = jwtService.extractUsername(token.getToken());
            if (userEmail != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
                if (jwtService.isTokenValid(token.getToken(), userDetails)) {
                    var user = userService.findBYEmail(userEmail)
                            .orElseThrow();
                    return new UserDto(userEmail,user.getRole(),user.getId());
                }
            }
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
            }
        return new UserDto(null, Role.USER,null);
    }
}