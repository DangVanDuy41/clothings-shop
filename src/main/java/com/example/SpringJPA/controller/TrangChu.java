package com.example.SpringJPA.controller;


import com.example.SpringJPA.Service.CatelogySevice;
import com.example.SpringJPA.Service.ProductService;
import com.example.SpringJPA.Service.UserService;
import com.example.SpringJPA.entity.Category;
import com.example.SpringJPA.entity.Product;
import com.example.SpringJPA.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/Trangchu")
public class TrangChu{

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
   private CatelogySevice catelogySevice;


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

    public List<Product> listProduct(){
        return  productService.getListProduct();
    }
    @GetMapping
    public String trangchu(Model model){
       User user= getUserAuthentication();
       if (user!=null){
           model.addAttribute("userAuthFullName",user.getFullName());
           model.addAttribute("userAuthAvatar",user.getAvatar());
       }



        List<Category> catelogySeviceList = catelogySevice.listCatelogy();
        model.addAttribute("catelogyList",catelogySeviceList);
        return "index";
    }


}
