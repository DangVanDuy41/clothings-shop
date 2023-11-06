package com.example.SpringJPA.Service;

import com.example.SpringJPA.entity.CartItem;
import com.example.SpringJPA.entity.Product;
import com.example.SpringJPA.entity.User;
import com.example.SpringJPA.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private   UserService userService;
    @Autowired
    private   ProductService productService;


    public void SaveCartItem( CartItem cartItem){
       this.cartItemRepository.save(cartItem);
    }

    public CartItem findByUserAndProduct(Product product, User user){
        return cartItemRepository.findByProductAndUser(product,user);
    }

}
