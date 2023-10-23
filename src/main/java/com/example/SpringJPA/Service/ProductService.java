package com.example.SpringJPA.Service;

import com.example.SpringJPA.entity.Category;
import com.example.SpringJPA.entity.Product;
import com.example.SpringJPA.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public List<Product> getListProduct(){
        return  productRepository.findAll();
    }
    public Page<Product> listProductNewestFirst(Pageable pageable) {
        // Sử dụng Sort để sắp xếp theo trường ngày cập nhật (suppose "updateDate" is the field name)
        Sort sort = Sort.by(Sort.Order.desc("id"));
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return productRepository.findAll(pageable);
    }
    public void saveProduct(Product product){
         this.productRepository.save(product);
    }

    public Product getProductById(Integer id) {
        return  productRepository.getReferenceById(id);
    }
    public void deleteProduct(Product product){
        this.productRepository.delete(product);
    }

}
