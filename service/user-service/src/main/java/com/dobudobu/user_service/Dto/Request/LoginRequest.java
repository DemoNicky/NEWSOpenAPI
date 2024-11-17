package com.dobudobu.user_service.Dto.Request;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @Email
    private String email;

    private String password;

}
