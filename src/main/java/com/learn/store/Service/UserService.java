package com.learn.store.Service;

import com.learn.store.Dto.User.RoleDto;
import com.learn.store.Dto.User.UserDto;
import com.learn.store.Dto.User.UserGetDto;
import com.learn.store.Models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public UserDto create(UserDto userDto) throws Exception;

    List<UserDto> findAll();

    UserGetDto findByUsername(String username);

}
