package com.major.dao;


import com.major.model.OrderDetails;
import com.major.model.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserDAO extends DAO{

    public User findUserByEmail(String email) {
        Query query = getSession().createQuery("FROM User WHERE email = :email");
        query.setParameter("email", email);
        return (User) query.uniqueResult();
    }

//    public User findUserById(String email) {
//        Query query = getSession().createQuery("FROM User WHERE email = :email");
//        query.setParameter("email", email);
//        return (User) query.uniqueResult();
//    }

    public void save(User user) {
        try {
            begin();
            getSession().save(user);
            commit();
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<User> getAllUsers()   {
        Query<User> query = getSession().createQuery("FROM User ");
        List<User> list = query.list();
        return list;
    }
}
