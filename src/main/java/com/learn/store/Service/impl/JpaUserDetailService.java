package com.learn.store.Service.impl;

import com.learn.store.Dto.User.JpaUserDetails;
import com.learn.store.Models.User;
import com.learn.store.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =this.userRepository.findByUsername(username  ).orElseThrow(()->
                new UsernameNotFoundException(username + "is not found"));
        return new JpaUserDetails(user);
    }
}
