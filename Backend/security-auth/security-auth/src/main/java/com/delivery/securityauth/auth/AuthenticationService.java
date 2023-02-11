package com.delivery.securityauth.auth;

import com.delivery.securityauth.Dto.AuthenticationRequest;
import com.delivery.securityauth.Dto.AuthenticationResponse;
import com.delivery.securityauth.Exception.ApiRequestException;
import com.delivery.securityauth.config.JwtService;
import com.delivery.securityauth.user.Role;
import com.delivery.securityauth.user.User;
import com.delivery.securityauth.user.UserRepository;
import com.delivery.securityauth.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        String jwtToken = null;
        try{
          var  user = User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .passw(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .phoneNumb(request.getPhoneNumber())
                    .build();
            userService.createUser(user);
            jwtToken = jwtService.generateToken(user);
        }catch (Exception e){
                throw new ApiRequestException(e.getMessage());
        }

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }



    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        String jwtToken;
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),request.getPassword()
                    )
            );
            var user = userService.findBYEmail(request.getEmail())
                    .orElseThrow();
            jwtToken = jwtService.generateToken(user);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
