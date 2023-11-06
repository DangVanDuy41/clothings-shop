package com.example.SpringJPA.repository;

import com.example.SpringJPA.entity.Category;
import com.example.SpringJPA.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CatelogyRepository  extends JpaRepository<Category, Integer> {
    @Query("SELECT c FROM Category c WHERE c.productList = :p")
        Category getCategory(@Param("p") Product product);
}
