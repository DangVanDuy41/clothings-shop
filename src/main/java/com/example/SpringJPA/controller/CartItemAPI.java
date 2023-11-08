package com.example.SpringJPA.controller;

import com.example.SpringJPA.DTO.CartDTO;
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
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
    public CartDTO CartItem(@RequestParam("id") int idProduct, @RequestParam("quantity") int quantity){
        Product product = productService.getProductById(idProduct);
        product.updateFormattedPrice();
        User user = getUserAuthentication();
        CartItem cartItem = cartItemService.findByUserAndProduct(product,user);

        if(cartItem !=null){
            int newQuantity = cartItem.getQuantity();
            cartItem.setQuantity(newQuantity + quantity);
            cartItem.setSubtotal((long) (cartItem.getQuantity() * product.getPrice()));
            cartItemService.SaveCartItem(cartItem);
            return new CartDTO(product.getId(),cartItem.getQuantity(),product.getName(),product.getFormattedPrice(),product.getImage(),cartItem.getSubtotal());
        }else{
            CartItem newCartItem = new CartItem();
            newCartItem.setUser(user);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(quantity);
            newCartItem.setSubtotal( (long) quantity * product.getPrice());
            cartItemService.SaveCartItem(newCartItem);
            return new CartDTO(product.getId(),quantity,product.getName(),product.getFormattedPrice(),product.getImage(),  (long )quantity * product.getPrice());
        }


    }
    @GetMapping("/productIncart")
    public List<CartDTO> productLis(){
        User user = getUserAuthentication();
        List<Object[]> productList = cartItemService.productInCart(user.getId());

        List<CartDTO> cartDTOS = new ArrayList<>();
                productList.forEach(obj -> {

                    Integer idProduct = (Integer) obj[0];
                    Product product = productService.getProductById(idProduct);
                    product.updateFormattedPrice();
                    Integer quantity = (Integer) obj[4];
                    String nameProduct = (String) obj[1];
                    Integer price = (Integer) obj[2];
                    String image = (String ) obj[3];
                    Long subtotal = (Long) obj[5];
                    CartDTO cartDTO = new CartDTO(idProduct,quantity,nameProduct,product.getFormattedPrice(),image,subtotal);
                    cartDTOS.add(cartDTO);
                });
        return cartDTOS ;
    }
    @GetMapping("/totalPrice")
    public String totalPrice(){
        User user = getUserAuthentication();
        List<CartItem> cartItems = cartItemService.cartItems(user);
        double total=0;
        for( CartItem item : cartItems){
            total += item.getSubtotal();
        }

            DecimalFormat decimalFormat = new DecimalFormat("#,###,###", new DecimalFormatSymbols(Locale.getDefault()));


        return   decimalFormat.format(total) + " đ";
    }
}
