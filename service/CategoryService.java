package com.major.service;


import com.major.model.Category;
import com.major.dao.CategoryDAO;
//import com.sheryians.major.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryDAO categoryDAO;

//    @Autowired
//    CategoryRepository categoryRepository;

    public List<Category> getAllCategory(){
        return categoryDAO.getAllCategory();
    }

    public void addCategory(Category category){
        categoryDAO.save(category);
    }

//    public void addCategory(Category category){
//        categoryRepository.save(category);
//    }
    public void removeCategoryById(int id){
        categoryDAO.deleteById(id);
    }

    public void updateCategoryById(Category category) {categoryDAO.update(category);}

    public Optional<Category> getCategoryById(int id){

        return Optional.ofNullable(categoryDAO.findById(id));
    }

}
