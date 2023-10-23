package com.example.SpringJPA.controller;

import com.example.SpringJPA.DTO.ProductRequest;
import com.example.SpringJPA.Service.CatelogySevice;
import com.example.SpringJPA.Service.ProductService;
import com.example.SpringJPA.entity.Category;
import com.example.SpringJPA.entity.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CatelogySevice catelogySevice;
    @PostMapping("/saveProduct")
    public String productSave(@Valid  @ModelAttribute("product")  ProductRequest productRequest,BindingResult result, @RequestParam("file") MultipartFile file, @RequestParam("catelogyId") Integer id,Model  model) throws IOException {
        Product product = new Product();
        product.setId(productRequest.getId());
        product.setName(productRequest.getName());
        Integer integerObject = Integer.valueOf(productRequest.getPrice());
        int intValue = integerObject.intValue();
        product.setPrice(intValue);
        product.setStockQuantity(productRequest.getStockQuantity());
        product.uploadImage(file);
        if (result.hasErrors()) {
            model.addAttribute("titleManager","THÊM SẢN PHẨM");
            return "AddProduct";
        }
        Category category = catelogySevice.getCatelogyById(id);
        product.setCategory(category);
        productService.saveProduct(product);
        return "redirect:/manager/productManager";
    }
    @GetMapping("/newProduct")
    public String productSave(Model model){

        model.addAttribute("product",new Product());
        List<Category> categoryList = catelogySevice.listCatelogy();
        model.addAttribute("catelogyList",categoryList);
        model.addAttribute("titleManager","THÊM SẢN PHẨM");
        return "AddProduct";
    }
    @GetMapping("/productManager")
    public String manager(Model model){
        List<Product> listProduct = productService.getListProduct();
        for(Product a : listProduct){
            a.updateFormattedPrice();
        }
        model.addAttribute("listProduct",listProduct);
        return "productManager";
    }
    @GetMapping("/deleteProduct/{id}")
    public String productDelete(@PathVariable Integer id){
        Product product = productService.getProductById(id);
        product.deleteImage();
        productService.deleteProduct(product);
        return "redirect:/manager/productManager";
    }
    @GetMapping("/updateProduct/{id}")
    public String productSave(@PathVariable Integer id,Model model){
        Product product = productService.getProductById(id);
        List<Category> categoryList = catelogySevice.listCatelogy();
        model.addAttribute("catelogyList",categoryList);
        model.addAttribute("product",product);
        model.addAttribute("titleManager","UPDATE SẢN PHẨM");
        return "AddProduct";
    }
}
