package com.hibernatetutorial.demo.repositoryjpa;

import com.hibernatetutorial.demo.constant.RoleName;
import com.hibernatetutorial.demo.entity.TblRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TblRoleRepositoryJPA extends JpaRepository<TblRoles, Long> {

    Optional<TblRoles> findbyName(RoleName roleName);
}
