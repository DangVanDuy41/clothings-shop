package com.example.SpringJPA.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Component
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String password;
    private String fullName;
    private String address;
    private String roles;
    private String phoneNumber;
    private String avatar;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<CartItem> cartItemSet = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<OrderUser> orderUserSet = new HashSet<>();

    public User(String email, String password, String fullName, String address, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.address = address;

        this.phoneNumber = phoneNumber;
    }
    public void uploadImage(MultipartFile file) throws IOException {
         String path="SpringJPA/src/main/resources/static/image/avatarUser/";
        String originalFilename = file.getOriginalFilename();
        if(!originalFilename.isEmpty()){

            String filePath = path + originalFilename;
            File f = new File(path);
            if(!f.exists()){
                f.mkdir();
            }
            Files.copy(file.getInputStream(), Paths.get(filePath));
            this.avatar =originalFilename;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", roles='" + roles + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +

                '}';
    }
}
