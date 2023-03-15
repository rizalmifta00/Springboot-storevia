package com.learn.store.Controller;

import com.learn.store.Dto.ResponseDto;
import com.learn.store.Dto.User.JwtResponseDto;
import com.learn.store.Dto.User.LoginDto;
import com.learn.store.Dto.User.UserDto;
import com.learn.store.Dto.User.UserGetDto;
import com.learn.store.Service.UserService;
import com.learn.store.Service.impl.JpaUserDetailService;
import com.learn.store.Util.JwtUtil;
import com.learn.store.Util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JpaUserDetailService jpaUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto){
        try{
            UserDto user = userService.create(userDto);
            return new ResponseEntity<UserDto>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<ResponseDto<JwtResponseDto>> login(@RequestBody LoginDto loginDto){
        UserGetDto userGetDto = userService.findByUsername(loginDto.getUsername());
        ResponseDto<JwtResponseDto> responseDto = new ResponseDto<>();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));
        UserDetails userDetails = jpaUserDetailService.loadUserByUsername(loginDto.getUsername());
        String jwtToken = jwtUtil.generateToken(userDetails);

        JwtResponseDto jwtResponse = new JwtResponseDto(jwtToken, userGetDto.getUsername(),userGetDto.getId());
        responseDto.setSuccess(true);
        responseDto.setStatus("200");
        responseDto.setMessage("Token for Login");
        responseDto.setData(jwtResponse);

        return ResponseEntity.ok(responseDto);

    }



    @GetMapping("/all")
    public ResponseEntity<ResponseDto<List<UserDto>>> findAll(){
        try{
            ResponseDto<List<UserDto>> response =
                    ResponseUtil.responseDtoSucces(userService.findAll(),"succes get data");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            ResponseDto<List<UserDto>> response =
                    ResponseUtil.responseDtoFailed(null, e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            return  new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
