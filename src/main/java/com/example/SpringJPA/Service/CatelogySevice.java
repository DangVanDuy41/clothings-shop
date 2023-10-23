package com.example.SpringJPA.Service;

import com.example.SpringJPA.entity.Category;
import com.example.SpringJPA.entity.Product;
import com.example.SpringJPA.repository.CatelogyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CatelogySevice {
    @Autowired
    private CatelogyRepository catelogyRepository;

    public List<Category> listCatelogy(){
        return  catelogyRepository.findAll();
    }
    public Category getCatelogyById(Integer id){
        return  catelogyRepository.getReferenceById(id);
    }

}
