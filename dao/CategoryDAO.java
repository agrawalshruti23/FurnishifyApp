package com.major.dao;

import com.major.model.Category;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDAO extends DAO {
    public List<Category> getAllCategory() {
        Query<Category> query = getSession().createQuery("FROM Category ");
        List<Category> list = query.list();
        return list;
    }


    public void save(Category category) {
        try {
            begin();
            getSession().save(category);
            commit();
//            close();

        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            System.out.print("Exception occurred"+ e.getMessage());

        }
    }
//    public void save(Category category) {
//        try {
//            begin();
//
//            int i = (int) getSession().save(category);
//
////            Category c = new Category();
////            c.setId(category.getId());
////            c.setName(category.getName());
//            getSession().beginTransaction().commit();
////            getSession().saveOrUpdate("CATEGORY",category);
////            getSession().save("Category.class",category);
//            commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void update(Category category) {
        try {
            begin();
            getSession().update(category);
            commit();
//            close();
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }
//        try {
//
//            Transaction txn = getSession().beginTransaction();
//            javax.persistence.Query query = getSession().createQuery("update Category set name=:name where id = :id");
//            query.setParameter("name", category.getName());
//            query.setParameter("id", category.getId());
//            query.executeUpdate();
//            txn.commit();
////            commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
//
    public void deleteById(int id){
        Transaction txn = getSession().beginTransaction();
        javax.persistence.Query query = getSession().createQuery("delete Category where id = :ID");
        query.setParameter("ID", id);

        int result = query.executeUpdate();
        if (result > 0) {
            System.out.println(id + " Category was removed");
        }
        txn.commit();
        close();
    }

    public Category findById(int id) {
        Query query = getSession().createQuery("FROM Category WHERE id = :id");
        query.setParameter("id", id);
        return (Category) query.uniqueResult();
    }
}
