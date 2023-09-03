package com.major.controller;

import com.major.dao.RoleDAO;
import com.major.global.GlobalDataCart;
import com.major.model.Role;
import com.major.model.User;
import com.major.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @GetMapping("/login")
    public String loginPage(@ModelAttribute("user") User userModel, HttpSession request){
        System.out.println("error");
        GlobalDataCart.cart.clear();
//        User userLoggedIn = userDAO.findUserByEmail(userModel.getEmail());
        String username = (String) request.getAttribute("userLoggedIn");
        System.out.println("username is "+username);
        return "login";
    }

    @GetMapping("/register")
    public String registerGetPage(){
        System.out.println("error");
        return "register";
    }

    @PostMapping("/register")
    public String registerPostPage(@ModelAttribute("user") User userModel, HttpServletRequest request, HttpSession session, Model model, @AuthenticationPrincipal User currentUser) throws ServletException {
        String passcode = userModel.getPassword();
        userModel.setPassword((passwordEncoder.encode(passcode)));
        List<Role> rolesList = new ArrayList<>();
        rolesList.add(roleDAO.findById(2));
        userModel.setRoles(rolesList);
        userDAO.save(userModel);
//        String userLoggedInEmail = userModel.getEmail();
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username = user.getEmail();
//        model.addAttribute("username", username);
//        User user1 = (User) userDAO.findUserByEmail(currentUser.getEmail());
//        model.addAttribute("currentUser", user1);
////
//        request.login(userModel.getEmail(), passcode);
//
////        if (request != null) {
//
//        String username1 = (String) request.getAttribute("username");
//        System.out.println("username is "+username1+" in register");
//        System.out.println("currentUser "+user1+" in register");

//        System.out.println("userLoggedIn is "+userLoggedIn);
        return "redirect:/";
    }

}
