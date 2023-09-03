package com.major.controller;

import com.major.dao.OrderDAO;
import com.major.dao.UserDAO;
import com.major.global.GlobalDataCart;
import com.major.model.OrderDetails;
import com.major.model.User;
import com.major.service.OrderService;
import com.major.model.Product;
import com.major.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {

    @Autowired
    ProductService pService;

    @Autowired
    OrderService oService;

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    UserDAO userDAO;

    @GetMapping("/addToCart/{id}")
    public String addToCartPage(@PathVariable int id){
        GlobalDataCart.cart.addProduct(pService.getProductById(id));
        return "redirect:/shop";
    }
    @GetMapping("/cart")
    public String cartGetPage(Model modelcart){
        System.out.println("I am at /cart path");
        modelcart.addAttribute("cartCount", GlobalDataCart.cart.getCartSize());
        int count = 1;
        modelcart.addAttribute("total", GlobalDataCart.cart.getProducts().stream().mapToDouble(Product::getPrice).sum());
        modelcart.addAttribute("cart", GlobalDataCart.cart);
        modelcart.addAttribute("count",count);
        return "cart";
    }

    @GetMapping("cart/removeItem/{index}")
    public String cartItemRemovePage(@PathVariable int index){
        System.out.println("I am at /cart/removeItem/{index} path");
        GlobalDataCart.cart.removeProduct(index);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkoutPage(Model modelCart){
        System.out.println("I am at /checkout path");
        modelCart.addAttribute("total", GlobalDataCart.cart.getProducts().stream().mapToDouble(Product::getPrice).sum());
        return "checkout";
    }

//    @PostMapping("/orderPlaced")
//    public String OrderPlaced(Model model){
//        GlobalDataCart.cart.getProducts().forEach(product -> {
//            Product currentProduct = productService.getProductById(product.getId());
//            currentProduct.setCount(currentProduct.getCount()-product.getCount());
//            productService.updateProduct(currentProduct);
//        });
//        model.addAttribute("result", "Text");
//        model.addAttribute("parameters",new HashMap<>());
//        System.out.println(model);
//        System.out.println(model.getAttribute("result"));
//        System.out.println(model.getAttribute("parameters"));
//        GlobalDataCart.cart.updateProductCount();
//        return "orderPlaced";
//    }
    @PostMapping("/orderPlaced")
    public String OrderPlacedPage(Model modelCart, @ModelAttribute("orderDetails") OrderDetails orderDetails){
        System.out.println("I am at /orderPlaced path");
        List<Product> products = new ArrayList<>();
        for (Product product : GlobalDataCart.cart.getProducts()) {
            products.add(pService.getProductById(product.getId()));
            System.out.println("products are"+ product);
        }
        orderDetails.setProductList(products);
        orderDAO.save(orderDetails);

        // Update product counts and calculate total cost of the orderDetails
        double totalCost = 0;
//        model.addAttribute("orderDetails",orderService.addOrder();)
        for (Product product : GlobalDataCart.cart.getProducts()) {
            Product currentProduct = pService.getProductById(product.getId());
//            currentProduct.setCount(currentProduct.getCount()-product.getCount());
//            productService.updateProduct(currentProduct);
            totalCost += product.getPrice();
        }
        // Add data to the model
        modelCart.addAttribute("result", "Total cost: $" + totalCost);
        Map<String, Double> productMap = new HashMap<>();
        for (Product product : GlobalDataCart.cart.getProducts()) {
            productMap.put(product.getName(), product.getPrice());
        }

        OrderDetails orderDetails1 = new OrderDetails();
//        orderDetails.setTotalCost(totalCost);
        List<Product> productList = GlobalDataCart.cart.getProducts();
        orderDetails1.setProductList(productList);

// Add the object to a list
        List<OrderDetails> orderList = new ArrayList<>();
        orderList.add(orderDetails1);

// Add the list as an attribute to the model
        modelCart.addAttribute("orderList", orderList);
//        orderDetails.setProductList(products);
        modelCart.addAttribute("parameters", productMap);
        // Update cart product counts and return the view name
//        GlobalDataCart.cart.updateProductCount();
        return "orderPlaced";
    }

//    @GetMapping("/history")
//    public String HistoryPage(Model modelCart, Model model){
////        User user1 = userDAO.findUserByEmail(username);
//        model.getAttribute("user1");
//        List<OrderDetails> orders = orderDAO.getAllOrders();
//        modelCart.addAttribute("orders", orders);
//        return "history";
////        System.out.println("I am at /history path");
//        orderDAO.getAllOrders();
//        orderDAO.save(orderDetails);
        // Update product counts and calculate total cost of the orderDetails
//        double totalCost = 0;
////        model.addAttribute("orderDetails",orderService.addOrder();)
//        for (Product product : GlobalDataCart.cart.getProducts()) {
//            Product currentProduct = pService.getProductById(product.getId());
////            currentProduct.setCount(currentProduct.getCount()-product.getCount());
////            productService.updateProduct(currentProduct);
//            totalCost += product.getPrice();
//        }
//        // Add data to the model
//        modelCart.addAttribute("result", "Total cost: $" + totalCost);
//        Map<String, Double> productMap = new HashMap<>();
//        for (Product product : GlobalDataCart.cart.getProducts()) {
//            productMap.put(product.getName(), product.getPrice());
//        }
//        modelCart.addAttribute("parameters", productMap);
//        // Update cart product counts and return the view name
////        GlobalDataCart.cart.updateProductCount();
//        return "history";
//    }
}
