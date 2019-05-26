package com.hibernatetutorial.demo.repositoryjpa;

import com.hibernatetutorial.demo.entity.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TblUserRepositoryJPA extends JpaRepository<TblUser, Long> {

    Optional<TblUser> findByEmail(String email);

    Optional<TblUser> findByUsername(String userName);

    Optional<TblUser> findByUsernameOrEmail(String userName, String email);

    List<TblUser> findByIdIn(List<Long> userIds);

    Boolean existsUserByUsername(String userName);

    Boolean existsUserByEmail(String email);
}
