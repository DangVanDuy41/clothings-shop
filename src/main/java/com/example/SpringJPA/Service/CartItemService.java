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

import java.util.List;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private   UserService userService;
    @Autowired
    private   ProductService productService;

        public CartItem getCartItemById(int id){
           return cartItemRepository.getReferenceById(id);
        }
    public void SaveCartItem( CartItem cartItem){
       this.cartItemRepository.save(cartItem);
    }

    public CartItem findByUserAndProduct(Product product, User user){
        return cartItemRepository.findByProductAndUser(product,user);
    }
    public List<CartItem> cartItems(User user){
        return cartItemRepository.findByUser(user);
    }
    public List<Object[]> productInCart(int id){
        return cartItemRepository.getProductIncart(id);
    }

    public void deleteCartItem( CartItem cartItem){
        this.cartItemRepository.delete(cartItem);
    }
}
