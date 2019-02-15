package com.hibernatetutorial.demo.repositoryjpa;

import com.hibernatetutorial.demo.entity.TblItemProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * ItemProductRepositoryPaging
 */
@Repository
@Transactional(value = "emTX")
public interface ItemProductRepositoryPaging extends PagingAndSortingRepository<TblItemProduct,String> {
}