package com.example.SpringJPA.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@Data
@NoArgsConstructor

public class CartDTO {
    private Integer productId;
    private Integer quantity;
    private String nameProduct;
    private String price;
    private String image;
    private Long subtotal;
    private double priceTotal;
    private  String formatPriceTotal;
    private Integer idCart;
    public CartDTO(Integer productId, Integer quantity, String nameProduct, String price, String image, Long subtotal) {
        this.productId = productId;
        this.quantity = quantity;
        this.nameProduct = nameProduct;
        this.price = price;
        this.image = image;
        this.subtotal = subtotal;
    }

    public CartDTO(Integer productId, Integer quantity, String nameProduct, String price, String image, Long subtotal, Integer idCart) {
        this.productId = productId;
        this.quantity = quantity;
        this.nameProduct = nameProduct;
        this.price = price;
        this.image = image;
        this.subtotal = subtotal;
        this.idCart = idCart;
    }

    public void updateFormattedPrice() {
        DecimalFormat decimalFormat = new DecimalFormat("#,###,###", new DecimalFormatSymbols(Locale.getDefault()));
        formatPriceTotal =decimalFormat.format(this.priceTotal) + " đ";
    }
}
