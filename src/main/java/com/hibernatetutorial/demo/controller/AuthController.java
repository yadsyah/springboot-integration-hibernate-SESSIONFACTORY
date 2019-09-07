package com.hibernatetutorial.demo.controller;

import com.hibernatetutorial.demo.constant.RoleName;
import com.hibernatetutorial.demo.entity.TblRoles;
import com.hibernatetutorial.demo.entity.TblUser;
import com.hibernatetutorial.demo.exception.AppException;
import com.hibernatetutorial.demo.payload.request.frontend.LoginRequest;
import com.hibernatetutorial.demo.payload.request.frontend.SignupRequest;
import com.hibernatetutorial.demo.payload.response.auth.ResponseJwtAuth;
import com.hibernatetutorial.demo.payload.response.global.DataApiResponse;
import com.hibernatetutorial.demo.repositoryjpa.TblRoleRepositoryJPA;
import com.hibernatetutorial.demo.repositoryjpa.TblUserRepositoryJPA;
import com.hibernatetutorial.demo.security.JwtTokenProvider;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    TblUserRepositoryJPA tblUserRepositoryJPA;

    @Autowired
    TblRoleRepositoryJPA tblRoleRepositoryJPA;

    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@Valid @RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new ResponseJwtAuth(jwt,"Bearer"));
    }

    @PostMapping(value = "/signup",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest req){
        if(tblUserRepositoryJPA.existsUserByEmail(req.getEmail())){
            return new ResponseEntity( new DataApiResponse(false,"Email is already taken!"), HttpStatus.NOT_ACCEPTABLE);
        }
        if(tblUserRepositoryJPA.existsUserByUsername(req.getUsername())){
            return new ResponseEntity( new DataApiResponse(false,"Username is already taken!"), HttpStatus.NOT_ACCEPTABLE);
        }
        TblUser tblUser = new TblUser();
        tblUser.setEmail(req.getEmail());
        tblUser.setName(req.getName());
        tblUser.setUsername(req.getUsername());
        tblUser.setPassword(passwordEncoder.encode(req.getPassword()));

        TblRoles userRole = tblRoleRepositoryJPA.findbyName(RoleName.ROLE_USER)
                .orElseThrow(()-> new AppException("User Role not set"));

        tblUser.setRoles(Collections.singleton(userRole));

        TblUser result = tblUserRepositoryJPA.save(tblUser);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new DataApiResponse(true,"User registered success!!"));
    }

}
