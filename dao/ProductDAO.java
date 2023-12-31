package com.major.dao;

import com.major.model.Product;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDAO extends DAO{

    public List<Product> findAll() {
        Query<Product> query = getSession().createQuery("FROM Product ");
        List<Product> list = query.list();
        return list;
    }

    public void save(Product product) {
        try {
            begin();
            getSession().save(product);
            commit();
//            close();
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }
    }

    public void update(Product product) {
        try {
            begin();
            getSession().update(product);
            commit();
            close();
        } catch (Exception e) {
//            rollback();
            e.printStackTrace();
        }
    }

    public void deleteById(long id){
        Transaction txn = getSession().beginTransaction();
        javax.persistence.Query query = getSession().createQuery("delete Product where id = :ID");
        query.setParameter("ID", id);

        int result = query.executeUpdate();
        if (result > 0) {
            System.out.println(id + " product is removed");
        }
        txn.commit();
        close();
    }

    public Product findById(long id) {
        Query query = getSession().createQuery("FROM Product WHERE id = :id");
        query.setParameter("id", id);
        return (Product) query.uniqueResult();
    }

    public List<Product> findAllByCategory_Id(int categoryId){
//        System.out.println("findAllByCategory_Id "+categoryId.getClass());
        Query<Product> query = getSession().createQuery("FROM Product where category_id = :categoryId");
        query.setParameter("categoryId", categoryId);
        return query.list();
    }

}
