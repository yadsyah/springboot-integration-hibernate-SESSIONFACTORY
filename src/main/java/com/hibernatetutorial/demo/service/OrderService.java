package com.hibernatetutorial.demo.service;

import com.hibernatetutorial.demo.constant.UtilityConstant;
import com.hibernatetutorial.demo.dao.CustomerDAO;
import com.hibernatetutorial.demo.dao.TblHistoryOrderDAO;
import com.hibernatetutorial.demo.dao.TblItemProductDAO;
import com.hibernatetutorial.demo.dao.TblOrderDAO;
import com.hibernatetutorial.demo.entity.Customer;
import com.hibernatetutorial.demo.entity.TblHistoryOrder;
import com.hibernatetutorial.demo.entity.TblItemProduct;
import com.hibernatetutorial.demo.entity.TblOrder;
import com.hibernatetutorial.demo.payload.request.ListOrderRequest;
import com.hibernatetutorial.demo.payload.request.OrderRequest;
import com.hibernatetutorial.demo.payload.response.OrderHistoryResponse;
import com.hibernatetutorial.demo.payload.response.OrderResponse;
import com.hibernatetutorial.demo.payload.response.global.DataApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    TblOrderDAO tblOrderDAO;

    @Autowired
    TblHistoryOrderDAO tblHistoryOrderDAO;

    @Autowired
    TblItemProductDAO tblItemProductDAO;

    @Autowired
    CustomerDAO customerDAO;

    public DataApiResponse orderProduct(OrderRequest orderRequest) {
        TblHistoryOrder historyOrder;
        TblItemProduct product;
        TblOrder order = new TblOrder();
        Double totalPrice = 0.0;
        String splitIdProduct;
        StringBuilder stringBuilder = new StringBuilder();
        Customer customer = customerDAO.findByIdCustomer(orderRequest.getIdCustomer());
        List<ListOrderRequest> listOrderRequests = orderRequest.getListOrder();
        if (customer != null) {
            if (listOrderRequests.size() > 0) {
                for (ListOrderRequest request : listOrderRequests) {
                    product = tblItemProductDAO.findById(request.getIdProductItem());
                    if (product != null) {
                        stringBuilder = stringBuilder.append(product.getIdItem().substring(2, 7)
                                .replace("0", ""));
                    } else {
                        return new DataApiResponse(false, "Product Item Not Found " + request.getIdProductItem());
                    }
                }
                splitIdProduct = stringBuilder.toString();
                order.setIdOrder(UtilityConstant.generateIdOrder(customer.getCustomerId(), splitIdProduct));
                order.setIdHistoryOrder(order.getIdOrder() + "01");
                for (ListOrderRequest requestPersist : listOrderRequests) {
                    product = tblItemProductDAO.findById(requestPersist.getIdProductItem());
                    int quantityLama = product.getStockProduct();
                    if (quantityLama < requestPersist.getQuantity()) {
                        return new DataApiResponse(false, "Product Item '" + product.getIdItem() + "' Stock Tidak Cukup!");
                    }
                    int quantityBaru = quantityLama - requestPersist.getQuantity();
                    product.setStockProduct(quantityBaru);
                    historyOrder = new TblHistoryOrder();
                    historyOrder.setIdHistoryOrder(order.getIdHistoryOrder());
                    historyOrder.setIdProductItem(product.getIdItem());
                    historyOrder.setPrice(BigDecimal.valueOf(product.getPrice().doubleValue() * requestPersist.getQuantity()));
                    historyOrder.setQuantity(new Long(requestPersist.getQuantity()));
                    tblHistoryOrderDAO.save(historyOrder);
                    tblItemProductDAO.saveOrUpdate(product);
//                    tblItemProductDAO.updateProduct(quantityBaru, requestPersist.getIdProductItem());
                    totalPrice += historyOrder.getPrice().doubleValue();
                }
                order.setCustomerId(customer.getCustomerId());
                order.setTotalOrder(BigDecimal.valueOf(totalPrice));
                tblOrderDAO.persistAndSave(order);
                return new DataApiResponse(true, "Success Save!");
            }
        }
        return new DataApiResponse(false, "Customer Id Not Found");
    }

    public DataApiResponse getAllOrderProduct() {
        OrderResponse orderResponse = new OrderResponse();
        OrderHistoryResponse historyResponse = new OrderHistoryResponse();
        List<OrderResponse> responseList = new ArrayList<>();
        List<OrderHistoryResponse> orderHistoryResponses = new ArrayList<>();
        List<TblOrder> orderList = tblOrderDAO.findAll();
        if (orderList.size() > 0) {
            for (TblOrder order : orderList) {
                List<TblHistoryOrder> historyOrders = tblHistoryOrderDAO.findByIdHistoryIn(Long.valueOf(order.getIdHistoryOrder()));
                Customer customer = customerDAO.findByIdCustomer(order.getCustomerId());
                for (TblHistoryOrder historyOrder : historyOrders) {
                    TblItemProduct product = tblItemProductDAO.findById(historyOrder.getIdProductItem());
                    if (product != null) {
                        historyResponse.setIdHistoryOrderPK(historyOrder.getIdHistoryOrderPK());
                        historyResponse.setIdHistoryOrder(historyOrder.getIdHistoryOrder());
                        historyResponse.setIdProductItem(historyOrder.getIdProductItem());
                        historyResponse.setNameProductItem(product.getNameProduct());
                        historyResponse.setTotalPrice(String.valueOf(historyOrder.getPrice()));
                        historyResponse.setQuantity(historyOrder.getQuantity());
                        historyResponse.setPriceProduct(product.getPrice().toString());
                        orderHistoryResponses.add(historyResponse);
                    }
                }
                orderResponse.setIdHistoryOrder(order.getIdHistoryOrder());
                orderResponse.setIdOrder(order.getIdOrder());
                orderResponse.setNamaCustomer(customer.getName());
                orderResponse.setTotalOrder(String.valueOf(order.getTotalOrder()));
                orderResponse.setOrderHistory(orderHistoryResponses);
                responseList.add(orderResponse);
                orderResponse = new OrderResponse();
                historyResponse = new OrderHistoryResponse();
                orderHistoryResponses = new ArrayList<>();
            }
            return new DataApiResponse(true, responseList);
        }
        return new DataApiResponse(false, "Data Not Found!");
    }
}
