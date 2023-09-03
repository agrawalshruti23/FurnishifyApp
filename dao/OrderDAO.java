package com.major.dao;

import com.major.model.Category;
import com.major.model.OrderDetails;
import com.major.model.Product;
import com.major.model.User;
import org.hibernate.criterion.Order;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDAO extends DAO {

    public void save(OrderDetails orderDetails)   {
        try {
            begin();
            getSession().save(orderDetails);
            commit();
            close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Exception occurred"+ e.getMessage());

        }
    }
    public List<OrderDetails> getAllOrders()   {
        Query<OrderDetails> query = getSession().createQuery("FROM OrderDetails ");
            List<OrderDetails> list = query.list();
            return list;
    }

    public List<OrderDetails> getAllOrdersAndProducts()   {
        Query<OrderDetails> query = getSession().createQuery("FROM OrderDetails ");
        List<OrderDetails> list = query.list();
        return list;
    }

    public List<Object> getAllOrdersAndProducts1(OrderDetails order) {
        Query<Object> query = getSession().createQuery(
                "FROM ORDERDETAILS od \n" +
                "JOIN ORDER_PRODUCTS op ON od.id = :orderId\n" +
                "JOIN Product p ON op.PRODUCT_ID = :productId;");
        query.setParameter("orderId", order.getId());
        List<Object> resultList = query.list();

        return resultList;
    }

//    public List<OrderDetails> getAllOrdersAccordingToUserLoggedIn(User user)   {
////        try {
//        Query<OrderDetails> query = getSession().createQuery("SELECT o FROM OrderDetails o JOIN o.userLoggedIn u WHERE u.id = :userId");
//        query.setParameter("userId", user.getEmail());
//        List<OrderDetails> list = query.list();
//        return list;
//    }

}
