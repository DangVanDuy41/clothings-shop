package com.example.SpringJPA.DTO;

import com.example.SpringJPA.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ProductRequest {
    private Integer id;
    @NotBlank(message = "Tên không được bỏ trống !!")
    private String name;
    @NotNull(message = "Số lượng không được bỏ trống !!")
    private Integer stockQuantity;
    @NotNull(message = "Giá không được bỏ trống !!")
    private Integer price;

}
