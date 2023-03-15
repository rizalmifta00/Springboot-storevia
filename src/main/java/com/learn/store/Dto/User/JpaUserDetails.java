package com.learn.store.Dto.User;

import com.learn.store.Models.ERole;
import com.learn.store.Models.Role;
import com.learn.store.Models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class JpaUserDetails implements UserDetails {

    private String username;
    private String password;
    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLooked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;
    private Set<GrantedAuthority> authorityList;

    public JpaUserDetails(User user){
        this.username=user.getUsername();
        this.password = user.getPassword();
        this.isAccountNonExpired = true;
        this.isAccountNonLooked = true;
        this.isCredentialsNonExpired = true;
        this.isEnabled = true;
        this.authorityList = user.getRoles().stream()
                .map(Role::getName)
                .map((ERole role) -> new SimpleGrantedAuthority(role.toString()))
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLooked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
