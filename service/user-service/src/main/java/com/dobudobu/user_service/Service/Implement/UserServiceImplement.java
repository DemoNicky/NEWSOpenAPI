package com.dobudobu.user_service.Service.Implement;

import com.dobudobu.user_service.Client.FileFeignClient;
import com.dobudobu.user_service.Dto.Request.LoginRequest;
import com.dobudobu.user_service.Dto.Request.RegisterRequest;
import com.dobudobu.user_service.Dto.Response.RegisterResponse;
import com.dobudobu.user_service.Dto.Response.ResponseHandling;
import com.dobudobu.user_service.Entity.Roles;
import com.dobudobu.user_service.Entity.User;
import com.dobudobu.user_service.Exception.ServiceCustomException.CustomAuthenticationFailed;
import com.dobudobu.user_service.Exception.ServiceCustomException.CustomExceptionAlreadyExsist;
import com.dobudobu.user_service.Repository.RoleRepository;
import com.dobudobu.user_service.Repository.UserRepository;
import com.dobudobu.user_service.Service.UserService;
import com.dobudobu.user_service.Util.GeneratedCode.GeneratedCodeUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private FileFeignClient fileFeignClient;

    @Autowired
    private GeneratedCodeUtil generatedCodeUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtServiceImplement jwtServiceImplement;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final String photoDefault = "wbglseewlrr1r4ivtgmr";

    @Override
    public ResponseHandling<RegisterResponse> register(RegisterRequest registerRequest) {
        ResponseHandling<RegisterResponse> responseHandling = new ResponseHandling<>();

        Optional<User> userUsername = userRepository.findByUsername(registerRequest.getUsername());
        if (userUsername.isPresent()){
            throw new CustomExceptionAlreadyExsist("username already exsists");
        }
        Optional<User> userEmail = userRepository.findByEmail(registerRequest.getEmail());
        if (userEmail.isPresent()){
            throw new CustomExceptionAlreadyExsist("email already exsists");
        }

//        ResponseHandling<FilePostResponse> fileResponse;
//        try {
//            fileResponse = fileFeignClient.postFile(multipartFile);
//        }catch (FeignException.BadRequest e){
//            throw new CustomExceptionUploadFileError("Failed to post image due to a bad request: " + e.contentUTF8());
//        } catch (FeignException e) {
//            throw new CustomExceptionUploadFileError("Failed to post image due to an error: " + e.getMessage());
//        }

        Optional<Roles> roles = roleRepository.findById(1L);

        User user = new User();
        user.setUserCode(generatedCodeUtil.generatedCode(userRepository::existsByUserCode));
        user.setFullName(registerRequest.getFullName());
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(roles.get());
        user.setProfileImage(photoDefault);
        user.setActive(false);

        userRepository.save(user);

        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setUserCode(user.getUserCode());
        registerResponse.setEmail(user.getEmail());
        registerResponse.setUsername(user.getUsername());

        responseHandling.setData(registerResponse);
        responseHandling.setStatus("success");
        responseHandling.setMessage("success registing user");
        responseHandling.setHttpStatus(HttpStatus.OK);
        responseHandling.setErrors(false);

        return responseHandling;
    }

    @Override
    public String generateToken(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            if (authentication.isAuthenticated()) {
                return jwtServiceImplement.generateToken(loginRequest.getEmail());
            } else {
                throw new CustomAuthenticationFailed("Invalid Email or Password");
            }
        } catch (Exception e) {
            System.out.println("Error during authentication: " + e.getMessage());
            throw new CustomAuthenticationFailed("invalid email or password");
        }
    }


    @Override
    public boolean validateToken(String token) {
        try {
            jwtServiceImplement.validateToken(token);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature: " + e.getMessage());
            return false;
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token has expired: " + e.getMessage());
            return false;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
            return false;
        }
    }


}
