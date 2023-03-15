package com.learn.store.Controller;

import com.learn.store.Dto.User.RoleDto;
import com.learn.store.Models.Role;
import com.learn.store.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @PostMapping("")
    public ResponseEntity<RoleDto> create(@RequestBody RoleDto roleDto){
        try{
            RoleDto role = roleService.create(roleDto);
            return new ResponseEntity<>(role, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
