package com.dobudobu.user_service.Contoller;

import com.dobudobu.user_service.Dto.Request.LoginRequest;
import com.dobudobu.user_service.Dto.Request.RegisterRequest;
import com.dobudobu.user_service.Dto.Response.RegisterResponse;
import com.dobudobu.user_service.Dto.Response.ResponseHandling;
import com.dobudobu.user_service.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(
            value = "/register"
    )
    public ResponseEntity<ResponseHandling<RegisterResponse>> register(@Valid @RequestBody RegisterRequest registerRequest){
        ResponseHandling<RegisterResponse> responseHandling = userService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.OK).body(responseHandling);
    }

    @PostMapping(
            value = "/token"
    )
    public String getToken(@RequestBody LoginRequest loginRequest){
        return userService.generateToken(loginRequest);
    }

    @GetMapping(
            value = "/validate"
    )
    public String validateToken(@RequestParam("token")String token){
        boolean val = userService.validateToken(token);
        if (val){
            return "Token is valid";
        }else {
            return "Invalid";
        }
    }

}
