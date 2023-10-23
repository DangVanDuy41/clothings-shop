package com.example.SpringJPA.controller;

import com.example.SpringJPA.Service.CatelogySevice;
import com.example.SpringJPA.Service.ProductService;
import com.example.SpringJPA.entity.Category;
import com.example.SpringJPA.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Trangchu")
public class ProductAPI {
    @Autowired
   private ProductService productService;
    private CatelogySevice catelogySevice;
    @GetMapping("/newProduct")
    public List<Product> listProducts(@RequestParam(value = "page", defaultValue = "0") int page) {
           Pageable pageable = PageRequest.of(page, 4); // 5 là kích thước trang
           Page<Product> productPage = productService.listProductNewestFirst(pageable);
            productPage.forEach(product -> {
            product.updateFormattedPrice();
        });
           return productPage.getContent();
    }
    @GetMapping("/pageNewProduct")
    public int getPageNewProduct() {
        Pageable pageable = PageRequest.of(0, 4);
        Page<Product> productPage = productService.listProductNewestFirst(pageable);
        return productPage.getTotalPages();
    }
    @GetMapping("/AllProduct/{page}")
    public List<Product> listProductsAll(@PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 12);
        Page<Product> productPage= productService.listProductNewestFirst(pageable);
        productPage.forEach(product -> {
            product.updateFormattedPrice();
        });
        return productPage.getContent();
    }
}
