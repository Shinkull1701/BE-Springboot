package com.example.demo2.Service;

import com.example.demo2.Repository.UserRepository;
import com.example.demo2.exception.AppException;
import com.example.demo2.exception.ErrorCode;
import com.example.demo2.request.UserCreationRequest;
import com.example.demo2.model.User;
import com.example.demo2.request.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request){
        User user = new User();
        if(userRepository.existsUserByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXITED);

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        return userRepository.save(user);
    };
    public User updateUser(Long userId, UserUpdateRequest request){
        User user= getUser(userId);
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        return userRepository.save(user);
    };
    public List<User> getUser(){
        return userRepository.findAll();
    }
    public User getUser(Long userId){
        return  userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found!"));
    }
    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }

}
