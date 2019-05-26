package com.hibernatetutorial.demo.security;

import com.hibernatetutorial.demo.entity.TblUser;
import com.hibernatetutorial.demo.repositoryjpa.TblUserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    @Autowired
    TblUserRepositoryJPA tblUserRepositoryJPA;


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        TblUser user = tblUserRepositoryJPA. findByUsernameOrEmail(usernameOrEmail,usernameOrEmail).orElseThrow(()->
                new UsernameNotFoundException("User Not Found with username or email : "+usernameOrEmail));
        return UserPrincipal.create(user);
    }
    @Transactional
    public UserDetails loadUserById(Long id){
        TblUser user = tblUserRepositoryJPA.findById(id).orElseThrow( () -> new UsernameNotFoundException("User Not Found With id : "+id));
        return UserPrincipal.create(user);
    }
}
