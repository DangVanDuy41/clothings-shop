package com.example.SpringJPA.controller;

import com.example.SpringJPA.Service.CatelogySevice;
import com.example.SpringJPA.Service.ProductService;
import com.example.SpringJPA.entity.Category;
import com.example.SpringJPA.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/Trangchu")
public class CategogyAPI {
    @Autowired
    private CatelogySevice catelogySevice;
    @Autowired
    private ProductService productService;

    @GetMapping("/category/{id}")
    public List<Product> categoryList(@PathVariable Integer id){
        Category category = catelogySevice.getCatelogyById(id);
        category.getProductList().forEach(product -> {

            product.updateFormattedPrice();
        });
        Collections.reverse( category.getProductList());
        return  category.getProductList();
    }
    @GetMapping("/category/listProduct")
   public  List<Product> productListAll(){
        List<Product> list = productService.getListProduct();
        list.forEach(product -> {
            product.updateFormattedPrice();
        });
        Collections.reverse(list);
        return list ;
   }
    @GetMapping("/category/thapdencao/{id}")
    public  List<Product> thapdencao(@PathVariable Integer id){
        Category category = catelogySevice.getCatelogyById(id);
        List<Product> list =category.getProductList();
        list.forEach(product -> {
            product.updateFormattedPrice();
        });
        Collections.sort(list, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getPrice() - o2.getPrice();
            }
        });

        return list ;
    }
    @GetMapping("/category/caodenthap/{id}")
    public  List<Product> caodenthap(@PathVariable Integer id){
        Category category = catelogySevice.getCatelogyById(id);
        List<Product> list =category.getProductList();
        list.forEach(product -> {
            product.updateFormattedPrice();
        });
        Collections.sort(list,(o1, o2) -> {
           return o2.getPrice() - o1.getPrice();
        });

        return list ;
    }
    @GetMapping("/category/caodenthap")
    public  List<Product> caodenthap(){

        List<Product> list = productService.getListProduct();
        list.forEach(product -> {
            product.updateFormattedPrice();
        });
        Collections.sort(list,(o1, o2) -> {
            return o2.getPrice() - o1.getPrice();
        });
        return list ;
    }
    @GetMapping("/category/thapdencao")
    public  List<Product> thapdencao(){

        List<Product> list = productService.getListProduct();
        list.forEach(product -> {
            product.updateFormattedPrice();
        });
        Collections.sort(list,(o1, o2) -> {
            return o1.getPrice() - o2.getPrice();
        });
        return list ;
    }
}
