package com.learn.store.Service.impl;

import com.learn.store.Dto.User.RoleDto;
import com.learn.store.Dto.User.UserDto;
import com.learn.store.Dto.User.UserGetDto;
import com.learn.store.Models.ERole;
import com.learn.store.Models.Role;
import com.learn.store.Models.User;
import com.learn.store.Repository.RoleRepository;
import com.learn.store.Repository.UserRepository;
import com.learn.store.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto create(UserDto userDto) throws Exception {
        ERole name ;
        if(userDto.getRoles().equalsIgnoreCase("admin")){
            name=ERole.ROLE_ADMIN;
        }else if(userDto.getRoles().equalsIgnoreCase("customer")){
            name=ERole.ROLE_CUSTOMER;
        }else {
            throw new Exception(userDto.getRoles()+ "is unknown. Only \"admin\" and \"customer\" are allowed");
        }
        Role role = roleRepository.findByname(name).orElseThrow(()->
                new Exception(name.name() + "cannot be found in database"));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        User user = modelMapper.map(userDto,User.class);
        user.setRoles(roles);
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user = userRepository.save(user);
        return userDto;

    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users){
            List<Role> roles = new ArrayList<>(user.getRoles());
            UserDto userDto = modelMapper.map(user,UserDto.class);
            userDto.setRoles(roles.get(0).getName().name());
            userDtos.add(userDto);


        }
        return userDtos;
    }

    @Override
    public UserGetDto findByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(()->
                new UsernameNotFoundException(username + " tidak ditemukan"));
        UserGetDto userGetDto = modelMapper.map(user, UserGetDto.class);
        return userGetDto;
    }
}
