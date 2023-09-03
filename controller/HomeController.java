package com.major.controller;

import com.major.global.GlobalDataCart;
import com.major.service.CategoryService;
import com.major.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @Autowired
    CategoryService cService;

    @Autowired
    ProductService pService;

    @GetMapping({"/", "/home"})
    public String homePage(Model modelCategory){
        modelCategory.addAttribute("total", GlobalDataCart.cart.getCartSize());

        return "index";
    }

    @GetMapping("/shop")
    public String shopPage(Model modelCategory){
        modelCategory.addAttribute("categories", cService.getAllCategory());
        modelCategory.addAttribute("products", pService.getAllProduct());
        modelCategory.addAttribute("total", GlobalDataCart.cart.getCartSize());
        return "shop";
    }
    @GetMapping("/shop/category/{id}")
    public String shopByCategoryPage(Model modelCategory, @PathVariable int id){
//        System.out.println("shopByCategory id is "+id.getClass());
        modelCategory.addAttribute("categories", cService.getAllCategory());
        modelCategory.addAttribute("products", pService.getAllProductsByCategoryId(id));
        System.out.println("model id is "+modelCategory.getAttribute("products"));
        modelCategory.addAttribute("total", GlobalDataCart.cart.getCartSize());

        return "shop";
    }
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProductPage(Model modelCategory, @PathVariable Long id){
        modelCategory.addAttribute("product", pService.getProductById(id));
        modelCategory.addAttribute("total", GlobalDataCart.cart.getCartSize());

        return "viewProduct";
    }
}
