package com.example.SpringJPA.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
@Data
@NoArgsConstructor
public class ProductDTO {
    private Integer id;
    private String name;
    private Integer stockQuantity;
    private Integer price;
    private String image;
    private String formattedPrice;

    public ProductDTO(Integer id, String name, Integer stockQuantity, Integer price, String image) {
        this.id = id;
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.image = image;
    }

    public void updateFormattedPrice() {
        DecimalFormat decimalFormat = new DecimalFormat("#,###,###", new DecimalFormatSymbols(Locale.getDefault()));
        formattedPrice = decimalFormat.format(this.price) + " đ";
    }
}
