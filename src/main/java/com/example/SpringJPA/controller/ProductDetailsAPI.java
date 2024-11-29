package com.example.SpringJPA.controller;

import com.example.SpringJPA.DTO.ProductDTO;
import com.example.SpringJPA.Service.CatelogySevice;
import com.example.SpringJPA.Service.ProductService;
import com.example.SpringJPA.entity.Category;
import com.example.SpringJPA.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/ProductDetails")
public class ProductDetailsAPI {
    @Autowired
    private ProductService productService;
    @Autowired
    private CatelogySevice catelogySevice;
    @GetMapping("/product/{id}")
    public List<ProductDTO> productList(@PathVariable int id){

        Product product = productService.getProductById(id);
        Category category = product.getCategory();

        Pageable pageable = PageRequest.of(0,4);
        Page<Product> page = productService.products(category,pageable);
        List<ProductDTO> productList = productService.productDTOList(page.getContent()) ;

        return productList ;

    }
}
