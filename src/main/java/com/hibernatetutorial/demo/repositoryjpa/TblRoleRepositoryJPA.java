package com.hibernatetutorial.demo.repositoryjpa;

import com.hibernatetutorial.demo.entity.TblRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblRoleRepositoryJPA extends JpaRepository<TblRoles, Long> {
}
