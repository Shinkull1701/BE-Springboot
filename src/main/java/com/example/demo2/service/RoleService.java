package com.example.demo2.service;

import com.example.demo2.dto.request.RoleRequest;
import com.example.demo2.dto.response.RoleResponse;
import com.example.demo2.mapper.RoleMapper;
import com.example.demo2.repository.PermissionRepository;
import com.example.demo2.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {

    RoleMapper roleMapper;
    PermissionRepository permissionRepository;
    RoleRepository  roleRepository;

    public RoleResponse create(RoleRequest request){
        var role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        role= roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }
    public List<RoleResponse>getAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }
    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
