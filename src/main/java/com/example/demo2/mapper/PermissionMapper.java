package com.example.demo2.mapper;

import com.example.demo2.dto.request.PermissionRequest;
import com.example.demo2.dto.response.PermissionResponse;
import com.example.demo2.entity.Permission;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")

public interface PermissionMapper {

    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse (Permission permission);

}
