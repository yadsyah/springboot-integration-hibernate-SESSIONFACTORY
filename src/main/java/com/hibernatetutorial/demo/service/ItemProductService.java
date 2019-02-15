package com.hibernatetutorial.demo.service;

import com.hibernatetutorial.demo.dao.TblItemProductDAO;
import com.hibernatetutorial.demo.dao.TblKategoriProductDAO;
import com.hibernatetutorial.demo.entity.TblItemProduct;
import com.hibernatetutorial.demo.entity.TblKategoriProduct;
import com.hibernatetutorial.demo.payload.request.ItemProductRequest;
import com.hibernatetutorial.demo.payload.response.global.DataApiResponse;
import com.hibernatetutorial.demo.repositoryjpa.ItemProductRepositoryPaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ItemProductService {

    @Autowired
    TblItemProductDAO tblItemProductDAO;

    @Autowired
    TblKategoriProductDAO tblKategoriProductDAO;

    @Autowired
    ItemProductRepositoryPaging itemProductRepositoryPaging;

    public DataApiResponse saveItemProduct(ItemProductRequest itemProductRequest) {
        TblItemProduct tblItemProduct = new TblItemProduct();
        tblItemProduct.setIdKategoryProduct(itemProductRequest.getIdKategoryProduct());
        tblItemProduct.setNameProduct(itemProductRequest.getNameProduct());
        tblItemProduct.setPrice(BigDecimal.valueOf(Double.parseDouble(itemProductRequest.getPrice())));
        tblItemProduct.setStockProduct(itemProductRequest.getStockProduct());
        tblItemProductDAO.persistAndSave(tblItemProduct);
        return new DataApiResponse(true, tblItemProductDAO.findById(tblItemProduct.getIdItem()));
    }

    public DataApiResponse findAllPaging(int page, int limit){
        Pageable pageable = PageRequest.of(page, limit);
        Page<TblItemProduct> items = itemProductRepositoryPaging.findAll(pageable);
        List<TblItemProduct> itemProductsList = items.getContent();
        if(itemProductsList.size()>0){
            return new DataApiResponse(true,itemProductsList);
        }
        return new DataApiResponse(true,"Data Not Found!");
    }

    public DataApiResponse getOneItemProduct(String idItemProduct){
        TblItemProduct itemProduct = tblItemProductDAO.findById(idItemProduct);
        return new DataApiResponse(true,itemProduct);
    }

    public DataApiResponse getAllKategoriItem(){
            List<TblKategoriProduct> tblKategoriProducts = tblKategoriProductDAO.findAll();
            return new DataApiResponse(true,tblKategoriProducts);
    }

    public DataApiResponse createKategoriItem(TblKategoriProduct tblKategoriProduct){
        tblKategoriProductDAO.persistAndSave(tblKategoriProduct);
        return new DataApiResponse(true,tblKategoriProductDAO.findById(tblKategoriProduct.getIdKategory()),"Success Save!");
    }

    public DataApiResponse getOneKategoriItem(Long idKategori){
        return new DataApiResponse(true,tblKategoriProductDAO.findById(idKategori));
    }

}
