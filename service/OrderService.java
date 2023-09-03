package com.major.service;


import com.major.dao.OrderDAO;
import com.major.model.Category;
import com.major.model.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderDAO orderDAO;

    public void addOrder(OrderDetails orderDetails){
        orderDAO.save(orderDetails);
    }
    public List<OrderDetails> getAllOrders(){
        return orderDAO.getAllOrders();
    }
}
