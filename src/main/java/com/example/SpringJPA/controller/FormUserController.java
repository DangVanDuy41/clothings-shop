package com.example.SpringJPA.controller;

import com.example.SpringJPA.DTO.UserRequest;
import com.example.SpringJPA.Service.UserService;
import com.example.SpringJPA.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/FormUser")
public class FormUserController {

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
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/formdangnhap")
    public String dangnhap(Model model){
        return "dangnhap";
    }
    @GetMapping("/")
    public String trangchu(){
        return "index";
    }

    @GetMapping("/formdangky")
    public String dangky(Model model){
        User user= new User();
        model.addAttribute("userRequest",user);
        model.addAttribute("title","Sign Up");
        return "dangky";
    }
    @GetMapping("/formupdate")
    public String update(Model model){
        User user= getUserAuthentication();
        model.addAttribute("userRequest",user);
        model.addAttribute("title","Update User");

        return "dangky";
    }
    @PostMapping("/registerUser")
    public String register(@Valid @ModelAttribute UserRequest userRequest, BindingResult result, @RequestParam("file") MultipartFile file, Model model) throws IOException {
        User user = new User();
        user.setId(userRequest.getId());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setFullName(userRequest.getFullName());
        user.setAddress(userRequest.getAddress());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setRoles("ROLE_USER");
        user.uploadImage(file);
        if (result.hasErrors()) {
            model.addAttribute("messageRegister",false);
            return "dangky";
        }
            userService.saveUser(user);
        model.addAttribute("messageRegister",true);
            return "dangky";
    }
}
