package com.learn.store.Filter;

import com.learn.store.Service.impl.JpaUserDetailService;
import com.learn.store.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JpaUserDetailService jpaUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // get the jwt token from request header
        String bearerToken = request.getHeader("Authorization");
        String username = null;
        String token = null;

        if(bearerToken != null && bearerToken.startsWith("Bearer ")){

            token = bearerToken.substring(7);

            try {
                username = jwtUtil.extractUsername(token);
                UserDetails userDetails = jpaUserDetailService.loadUserByUsername(username);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(upat);
                } else {
                    System.out.println("Invalid Token");
                }

            }catch (Exception e){
                e.printStackTrace();

            }
        }else {
            System.out.println("Invalid Bearer Token Format");
        }
        filterChain.doFilter(request,response);
    }
}
