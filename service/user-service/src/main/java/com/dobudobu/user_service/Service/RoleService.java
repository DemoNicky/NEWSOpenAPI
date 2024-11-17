package com.dobudobu.user_service.Service;

import com.dobudobu.user_service.Dto.Response.ResponseHandling;

public interface RoleService {
    ResponseHandling<String> createRole(String roleName);
}
