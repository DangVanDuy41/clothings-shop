package com.example.SpringJPA.Service;

import com.example.SpringJPA.DTO.ProductDTO;
import com.example.SpringJPA.entity.Category;
import com.example.SpringJPA.entity.Product;
import com.example.SpringJPA.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public List<Product> getListProduct(){
        return  productRepository.findAll();
    }
    public Page<Product> listProductNewestFirst(Pageable pageable) {

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
    public  Page<Product> products(Category category,Pageable pageable){
        return productRepository.findByCategory(pageable,category);
    }
    public List<ProductDTO> productDTOList (List<Product> list){
        List<ProductDTO> productDTOList =new ArrayList<>();
        list.forEach(p->{
            ProductDTO productDTO = new ProductDTO(p.getId(),p.getName(),p.getStockQuantity(),p.getPrice(),p.getImage());
            productDTO.updateFormattedPrice();
            productDTOList.add(productDTO);

        });
        return productDTOList;
    }
}
