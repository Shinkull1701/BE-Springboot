package com.example.demo2.mapper;

import com.example.demo2.Entity.User;
import com.example.demo2.dto.request.UserCreationRequest;
import com.example.demo2.dto.request.UserUpdateRequest;
import com.example.demo2.dto.response.UserRespone;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")

public interface UserMapper {

    User toUser(UserCreationRequest request);
    UserRespone toUserRespone(User user);
    void UpdateUser(@MappingTarget User user, UserUpdateRequest request);


}
