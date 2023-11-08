package com.example.SpringJPA.repository;

import com.example.SpringJPA.entity.CartItem;
import com.example.SpringJPA.entity.Product;
import com.example.SpringJPA.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
        List<CartItem> findByUser(User user);
        CartItem findByProductAndUser(Product product, User user);
        @Query(value = "SELECT p.id, p.name, p.price, p.image, c.quantity, c. subtotal\n" +
                "FROM User as u\n" +
                "INNER JOIN cart_item as c\n" +
                "ON c.user_id = u.id\n" +
                "INNER JOIN product as p\n" +
                "ON p.id = c.product_id\n" +
                "WHERE u.id = ?1\n"+
                "order by c.id DESC\n"+
                "LIMIT 2\n", nativeQuery = true)
        List<Object[]> getProductIncart(int id);
}
