package com.example.SpringJPA.Service;

import com.example.SpringJPA.DTO.UserRequest;
import com.example.SpringJPA.entity.Product;
import com.example.SpringJPA.entity.User;
import com.example.SpringJPA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(int id){
        return  userRepository.findById(id).orElse(null);
    }
    public User getUserByEmail(String email){
        return  userRepository.findByEmail(email);
    }

    public List<User> getAlltUser(){
        return userRepository.findAll() ;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleUser(User user) {
        userRepository.delete(user);
    }


}
