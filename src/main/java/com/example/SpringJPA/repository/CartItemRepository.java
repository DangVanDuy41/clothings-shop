package com.example.SpringJPA.repository;

import com.example.SpringJPA.entity.CartItem;
import com.example.SpringJPA.entity.Product;
import com.example.SpringJPA.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
        CartItem findByProductAndUser(Product product, User user);
}
