package com.learn.store.Service.impl;

import com.learn.store.Dto.User.RoleDto;
import com.learn.store.Models.ERole;
import com.learn.store.Models.Role;
import com.learn.store.Repository.RoleRepository;
import com.learn.store.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleDto create(RoleDto roleDto) throws Exception {
        Role role = new Role();
        ERole name = null;

        if(roleDto.getName().equalsIgnoreCase("admin")){
            role.setName(ERole.ROLE_ADMIN);
            name = ERole.ROLE_ADMIN;
        }else if(roleDto.getName().equalsIgnoreCase("customer")){
            role.setName(ERole.ROLE_CUSTOMER);
            name = ERole.ROLE_CUSTOMER;
        }else {
            throw new Exception(roleDto.getName()+"is unknown");
        }

        Optional<Role> roleOptional = roleRepository.findByname(name);
        if(roleOptional.isPresent()){
            throw new Exception(roleDto.getName()+" Has Duplicate in Database");
        }

        Role roleSaved = roleRepository.save(role);
        RoleDto newRoleDto = new RoleDto();
        newRoleDto.setId(roleSaved.getId());
        newRoleDto.setName(roleSaved.getName().name());

        return newRoleDto;
    }
}
