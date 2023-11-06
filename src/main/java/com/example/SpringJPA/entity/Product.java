package com.example.SpringJPA.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer stockQuantity;
    private Integer price;
    private String image;
    private String formattedPrice;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catelogy_id")
    private Category category;
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private Set<CartItem> cartItemSet = new HashSet<>();
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<OrderItem> orderItemSet = new HashSet<>();



    public Product(Integer id, String name, Integer stockQuantity, Integer price) {
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
    public void uploadImage(MultipartFile file) throws IOException {
        String path="SpringJPA/src/main/resources/static/image/imageProduct/";
        String originalFilename = file.getOriginalFilename();
        if(!originalFilename.isEmpty()){
            String randomID = UUID.randomUUID().toString();
            String Filename = randomID.concat(originalFilename.substring(originalFilename.lastIndexOf(".")));
            String filePath = path + Filename;
            File f = new File(path);
            if(!f.exists()){
                f.mkdir();
            }
            Files.copy(file.getInputStream(), Paths.get(filePath));
            this.image =Filename;
        }
    }
    public void deleteImage() {
        String path = "SpringJPA/src/main/resources/static/image/imageProduct/";
        if (this.image != null && !this.image.isEmpty()) {
            String filePath = path + this.image;
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
        }
    }

}
