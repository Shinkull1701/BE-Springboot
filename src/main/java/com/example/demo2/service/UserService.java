package com.example.demo2.service;

import com.example.demo2.enums.Role;
import com.example.demo2.repository.UserRepository;
import com.example.demo2.dto.response.UserRespone;
import com.example.demo2.exception.AppException;
import com.example.demo2.exception.ErrorCode;
import com.example.demo2.mapper.UserMapper;
import com.example.demo2.dto.request.UserCreationRequest;
import com.example.demo2.entity.User;
import com.example.demo2.dto.request.UserUpdateRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Builder
@Slf4j
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    public User createUser(UserCreationRequest request){

        if(userRepository.existsUserByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXITED);
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        HashSet<String> roles = new HashSet<>();
        //roles.add(Role.USER.name());

        //user.setRoles(roles);
        return userRepository.save(user);
    };
    public UserRespone updateUser(Long userId, UserUpdateRequest request){

        User user = userRepository.findById(userId).
                orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.UpdateUser(user,request);

        return userMapper.toUserRespone(userRepository.save(user));
    };
        @PreAuthorize("hasRole('ADMIN')")
        public List<UserRespone> getUsers() {
            log.info("In method getUsers");
        return userRepository.findAll().stream()
                .map(userMapper::toUserRespone).toList();
    }
    @PostAuthorize("returnObject.username==authentication.name")
    public UserRespone getUser(Long userId){
            log.info("In method getUserbyId");
        return  userMapper.toUserRespone(userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found!")));
    }
    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }

    public UserRespone getMyinfo(){
       var context = SecurityContextHolder.getContext();
       String name = context.getAuthentication().getName();
       User user = userRepository.findByUsername(name).orElseThrow(()
               -> new AppException(ErrorCode.USER_NOT_EXISTED));
       return  userMapper.toUserRespone(user);
    }
}
