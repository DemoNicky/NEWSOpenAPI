package com.dobudobu.user_service.Dto.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {

    private String userCode;

    private String email;

    private String username;

}
