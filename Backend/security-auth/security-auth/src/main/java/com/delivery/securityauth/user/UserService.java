package com.delivery.securityauth.user;

import java.util.Optional;


public interface UserService {
    User createUser(User user);
    public Optional<User> findBYEmail(String email);
}
