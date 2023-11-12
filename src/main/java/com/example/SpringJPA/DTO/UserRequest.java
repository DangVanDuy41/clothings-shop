package com.example.SpringJPA.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Data
public class UserRequest {
    @NotNull(message = "ID khong duoc bo trong !")
    private Integer id;
    @Email(message = "Đây không phải là email !")
    @NotBlank(message = "Email không được bỏ trống !")
    private String email;
    @NotBlank( message = "Tên không được bỏ trống !")
    private String fullName;
    @NotBlank(message = "Địa chỉ không được bỏ trống !")
    private String address;
    @Pattern(regexp = "0\\d{9,10}", message = "Không phải số điện thoại hợp lệ")
    private String phoneNumber;
    private MultipartFile file;
    @NotBlank(message = "Mật khẩu không được bỏ trống")
    private String password;

}
