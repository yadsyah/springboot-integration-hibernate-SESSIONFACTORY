package com.hibernatetutorial.demo.repositoryjpa;

import com.hibernatetutorial.demo.entity.CustomerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(value = "emTX")
public interface CustomerDetailRepositoryJPA extends JpaRepository<CustomerDetail,Long> {

    Boolean existsByNoKTP(String noKTP);
}
