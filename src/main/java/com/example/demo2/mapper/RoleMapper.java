package com.example.demo2.mapper;

import com.example.demo2.dto.request.PermissionRequest;
import com.example.demo2.dto.request.RoleRequest;
import com.example.demo2.dto.response.PermissionResponse;
import com.example.demo2.dto.response.RoleResponse;
import com.example.demo2.entity.Permission;
import com.example.demo2.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")

public interface RoleMapper {
    @Mapping(target = "permissions",ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse (Role role);

}
