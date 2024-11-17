package com.dobudobu.user_service.Dto.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @Size(max = 30, message = "Full name must not exceed 30 characters")
    private String fullName;

    @Size(max = 20, message = "Username must not exceed 20 characters")
    private String username;

    @Email
    @Size(max = 50, message = "Email must not exceed 50 characters")
    private String email;

    @Size(max = 50, message = "Password must not exceed 50 characters")
    private String password;

}
