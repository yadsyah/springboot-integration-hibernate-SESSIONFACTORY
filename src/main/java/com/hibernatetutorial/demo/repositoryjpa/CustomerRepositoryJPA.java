package com.hibernatetutorial.demo.repositoryjpa;

import com.hibernatetutorial.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(value = "emTX")
public interface CustomerRepositoryJPA extends JpaRepository<Customer,Long> {

    boolean existsByEmail(String email);
}
