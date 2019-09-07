package com.hibernatetutorial.demo.service;


import com.hibernatetutorial.demo.constant.RoleName;
import com.hibernatetutorial.demo.entity.TblRoles;
import com.hibernatetutorial.demo.entity.TblUser;
import com.hibernatetutorial.demo.exception.AppException;
import com.hibernatetutorial.demo.payload.request.frontend.LoginRequest;
import com.hibernatetutorial.demo.payload.request.frontend.SignupRequest;
import com.hibernatetutorial.demo.payload.response.global.DataApiResponse;
import com.hibernatetutorial.demo.repositoryjpa.TblRoleRepositoryJPA;
import com.hibernatetutorial.demo.repositoryjpa.TblUserRepositoryJPA;
import com.hibernatetutorial.demo.security.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServices.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    TblUserRepositoryJPA tblUserRepositoryJPA;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TblRoleRepositoryJPA tblRoleRepositoryJPA;
    public DataApiResponse authUserService(LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtTokenProvider.generateToken(authentication);

        return new DataApiResponse(true, jwtToken);
    }

    public DataApiResponse signupUserService(SignupRequest signupRequest){
        if(tblUserRepositoryJPA.existsUserByEmail(signupRequest.getEmail())){
            return new DataApiResponse(false, "Email is Already taken!");
        }
        if(tblUserRepositoryJPA.existsUserByUsername(signupRequest.getUsername())) {
            return new DataApiResponse(false, "Username is Already taken!");
        }

        TblUser user = new TblUser();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        TblRoles userRole = tblRoleRepositoryJPA.findbyName(RoleName.ROLE_USER)
                .orElseThrow(()->new AppException("User Role is not set!"));

        user.setRoles(Collections.singleton(userRole));
        TblUser result=  tblUserRepositoryJPA.save(user);

        return new DataApiResponse(true, result);
    }

}
