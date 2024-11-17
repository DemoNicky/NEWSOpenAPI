package com.dobudobu.user_service.Service;

import com.dobudobu.user_service.Dto.Request.LoginRequest;
import com.dobudobu.user_service.Dto.Request.RegisterRequest;
import com.dobudobu.user_service.Dto.Response.RegisterResponse;
import com.dobudobu.user_service.Dto.Response.ResponseHandling;

public interface UserService {


    ResponseHandling<RegisterResponse> register(RegisterRequest registerRequest);

    String generateToken(LoginRequest loginRequest);

    boolean validateToken(String token);
}
