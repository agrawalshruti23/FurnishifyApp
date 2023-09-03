package com.major.dao;


import com.major.model.Role;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

@Component
public class RoleDAO extends DAO{

    public Role findById(Integer id) {
        System.out.println(id);
        Query query = getSession().createQuery("FROM Role WHERE id = :id");
        query.setParameter("id", id);
        return (Role) query.uniqueResult();
    }

}
