package com.dobudobu.user_service.Contoller;

import com.dobudobu.user_service.Dto.Response.ResponseHandling;
import com.dobudobu.user_service.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping(
            path = "/create-role",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseHandling<String>> createRole(@RequestParam(value = "role") String roleName){
        ResponseHandling<String> responseHandling = roleService.createRole(roleName);
        return ResponseEntity.status(HttpStatus.OK).body(responseHandling);
    }

}
