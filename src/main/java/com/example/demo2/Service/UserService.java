package com.example.demo2.Service;

import com.example.demo2.Repository.UserRepository;
import com.example.demo2.dto.response.UserRespone;
import com.example.demo2.exception.AppException;
import com.example.demo2.exception.ErrorCode;
import com.example.demo2.mapper.UserMapper;
import com.example.demo2.dto.request.UserCreationRequest;
import com.example.demo2.Entity.User;
import com.example.demo2.dto.request.UserUpdateRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    public User createUser(UserCreationRequest request){

        if(userRepository.existsUserByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXITED);
        User user = userMapper.toUser(request);
        return userRepository.save(user);
    };
    public UserRespone updateUser(Long userId, UserUpdateRequest request){

        User user = userRepository.findById(userId).
                orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.UpdateUser(user,request);

        return userMapper.toUserRespone(userRepository.save(user));
    };
    public List<User> getUser(){
        return userRepository.findAll();
    }

    public UserRespone getUser(Long userId){
        return  userMapper.toUserRespone(userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found!")));
    }
    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }

}
