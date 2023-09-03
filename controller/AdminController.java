package com.major.controller;

import com.major.dao.OrderDAO;
import com.major.dao.UserDAO;
import com.major.model.*;
import com.major.service.CategoryService;
import com.major.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
    @Autowired
    CategoryService cService;
    @Autowired
    ProductService pService;

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    UserDAO userDAO;

    //ADMIN SECTION
    @GetMapping("/admin")
    public String adminHomePage(){
        return "adminHome";
    }
    @GetMapping("/admin/categories")
    public String getCategories(Model modelCategory){
        modelCategory.addAttribute("categories", cService.getAllCategory());
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String getCategoriesAddPage(Model modelCategory){
        modelCategory.addAttribute("category", new Category());
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String postCategoriesAddPage(@ModelAttribute("category") Category category){
//        Category c = new Category();
//        c.setId(category.getId());
//        c.setName(category.getName());
////        Category b = category;
//        if(c.getId() ==  0){
////            categoryService.updateCategoryById(category);
//            cService.addCategory(c);
//        }
//////        categoryService.addCategory(category);
//        else {
//            cService.updateCategoryById(c);
//        }
//        return "redirect:/admin/categories";
        if(category.getId() ==  null){
            cService.addCategory(category);
        }
        else {
            cService.updateCategoryById(category);
        }
        int tempId = 0;
        {

        }

        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategoriesPage(@PathVariable int id){

        cService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String updateCategoriesPage(@PathVariable int id, Model modelCategory){
        Optional<Category> categoryUpdate = cService.getCategoryById(id);
        if(categoryUpdate.isPresent()){
            modelCategory.addAttribute("category", categoryUpdate.get());
            return "categoriesAdd";
        } else
            return "404";
    }

    //PRODUCT SECTION
    @GetMapping("/admin/products")
    public String getProductsPage(Model modelProduct){
        modelProduct.addAttribute("products", pService.getAllProduct());
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String getProductAddandGetPage(Model modelProduct){
        modelProduct.addAttribute("productDTO", new ProductDTO());
        modelProduct.addAttribute("categories", cService.getAllCategory());
        return "productsAdd";
    }
    @PostMapping("/admin/products/add")
    public String productAddandPost(@ModelAttribute("productDTO")ProductDTO productDTO, @RequestParam("productImage") MultipartFile file, @RequestParam("imgName")String imgName) throws IOException {

        Product product = new Product();

        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(cService.getCategoryById(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        product.setDescription(productDTO.getDescription());
        product.setCount(productDTO.getCount());

        String iUuid;

        if(!file.isEmpty()){
            iUuid = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, iUuid);
            Files.write(fileNameAndPath, file.getBytes());
        } else{
            iUuid = imgName;
        }

        product.setImageName(iUuid);

        if(productDTO.getId()==null){
            pService.addProduct(product);
        }
        else {
            pService.updateProduct(product);
        }

        return "redirect:/admin/products";
    }
    @GetMapping("/admin/product/delete/{id}")
    public String deleteProductItemPage(@PathVariable long id){
        pService.removeProductById(id);
        return "redirect:/admin/products";

    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProductGetPage(@PathVariable long id, Model modelProduct){
        Product product = pService.getProductById(id);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategoryId((product.getCategory().getId()));
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight((product.getWeight()));
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getImageName());
        productDTO.setCount(product.getCount());

        modelProduct.addAttribute("categories", cService.getAllCategory());
        modelProduct.addAttribute("productDTO", productDTO);

        return "productsAdd";
    }
    @GetMapping("/admin/history")
    public String HistoryPage(Model modelCart, Model model) {
//        model.getAttribute("user1");
        List<OrderDetails> orders = orderDAO.getAllOrders();
        modelCart.addAttribute("orders", orders);
        return "history";
    }
    @GetMapping("/admin/users")
    public String UsersPage(Model modelCart, Model model) {
        List<User> users = userDAO.getAllUsers();
        modelCart.addAttribute("users", users);
        return "users";
    }


}
