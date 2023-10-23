package com.example.SpringJPA.repository;

import com.example.SpringJPA.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatelogyRepository  extends JpaRepository<Category, Integer> {

}
