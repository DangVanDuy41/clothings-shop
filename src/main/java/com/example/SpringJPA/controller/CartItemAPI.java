package com.example.SpringJPA.controller;

import com.example.SpringJPA.DTO.ProductDTO;
import com.example.SpringJPA.Service.CartItemService;
import com.example.SpringJPA.Service.ProductService;
import com.example.SpringJPA.Service.UserService;
import com.example.SpringJPA.entity.CartItem;
import com.example.SpringJPA.entity.Product;
import com.example.SpringJPA.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/CartItem")
public class CartItemAPI {
    @Autowired
    private CartItemService cartItemService;
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
    @GetMapping("/myCart")
    public String CartItem(@RequestParam("id") int idProduct,@RequestParam("quantity") int quantity){
        Product product = productService.getProductById(idProduct);
        User user = getUserAuthentication();
        CartItem cartItem = cartItemService.findByUserAndProduct(product,user);
        if(cartItem !=null){
            int newQuantity = cartItem.getQuantity();
            cartItem.setQuantity(newQuantity + quantity);
            cartItem.setSubtotal((double) (newQuantity * product.getPrice()));
            cartItemService.SaveCartItem(cartItem);
        }else{
            CartItem newCartItem = new CartItem();
            newCartItem.setUser(user);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(quantity);
            newCartItem.setSubtotal( (double) quantity * product.getPrice());
            cartItemService.SaveCartItem(newCartItem);
        }

        return  "Them thanh cong !!";
    }
    @GetMapping("/productIncart")
    public List<ProductDTO> productLis(){
        User user = getUserAuthentication();
        List<CartItem> cartItems = user.getCartItemSet();
        List<Product> productList = new ArrayList<>();
        cartItems.forEach(cart ->{
            productList.add(cart.getProduct());
        });
        return productService.productDTOList(productList);
    }
}
