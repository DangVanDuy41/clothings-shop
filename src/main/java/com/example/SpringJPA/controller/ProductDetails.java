package com.example.SpringJPA.controller;

import com.example.SpringJPA.Service.ProductService;
import com.example.SpringJPA.Service.UserService;
import com.example.SpringJPA.entity.Product;
import com.example.SpringJPA.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping
public class ProductDetails{
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    public User getUserAuthentication(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email=  userDetails.getUsername();
            User user = userService.getUserByEmail(email);
            return user;
        }
        return null;
    }
    @GetMapping("/ProductDetails/{id}")
    public String getProductSave(@PathVariable Integer id, Model model){
        User user= getUserAuthentication();
        if (user!=null){
            model.addAttribute("userAuthFullName",user.getFullName());
            model.addAttribute("userAuthAvatar",user.getAvatar());
        }
        Product product = productService.getProductById(id);
        product.updateFormattedPrice();
        model.addAttribute("product",product);
        return "ProductDetails";
    }

}
