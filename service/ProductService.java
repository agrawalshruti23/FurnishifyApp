package com.major.service;

import com.major.dao.ProductDAO;
import com.major.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {



    @Autowired
    ProductDAO productDAO;

    public List<Product> getAllProduct(){
        return productDAO.findAll();
    }

    public void addProduct(Product product){
        productDAO.save(product);
    }

    public void updateProduct(Product product){
        productDAO.update(product);
    }

    public void removeProductById(long id){
        productDAO.deleteById(id);
    }

    public Product getProductById(long id){
        return  productDAO.findById(id);
    }

    public List<Product> getAllProductsByCategoryId(int id){
//        System.out.println("getAllProductsByCategoryId "+id.getClass());
        return productDAO.findAllByCategory_Id(id);
    }

}
