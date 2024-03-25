package com.ecom.backend.services.auth;

import com.ecom.backend.dto.SignupRequest;
import com.ecom.backend.dto.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);

    Boolean hasUserWithEmail(String email);
}
