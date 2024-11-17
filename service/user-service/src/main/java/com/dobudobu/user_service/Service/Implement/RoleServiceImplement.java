package com.dobudobu.user_service.Service.Implement;

import com.dobudobu.user_service.Dto.Response.ResponseHandling;
import com.dobudobu.user_service.Entity.Roles;
import com.dobudobu.user_service.Exception.ServiceCustomException.CustomExceptionAlreadyExsist;
import com.dobudobu.user_service.Repository.RoleRepository;
import com.dobudobu.user_service.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImplement implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public ResponseHandling<String> createRole(String roleName) {
        ResponseHandling<String> responseHandling = new ResponseHandling<>();
        Optional<Roles> roles = roleRepository.findByRoleName(roleName);
        if (roles.isPresent()){
            throw new CustomExceptionAlreadyExsist("role name already exists");
        }
        Roles rolesSave = new Roles();
        rolesSave.setRoleName(roleName);
        roleRepository.save(rolesSave);

        responseHandling.setData(roleName);
        responseHandling.setStatus("success");
        responseHandling.setMessage("success get file");
        responseHandling.setHttpStatus(HttpStatus.OK);
        responseHandling.setErrors(false);
        return responseHandling;
    }
}
