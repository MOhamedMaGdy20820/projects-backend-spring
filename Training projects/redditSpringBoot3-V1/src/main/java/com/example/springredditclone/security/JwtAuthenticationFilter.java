package com.example.springredditclone.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserDetailsService userDetailsService ;

    @Override
    protected void doFilterInternal(HttpServletRequest  request,
                                    HttpServletResponse response,
                                    FilterChain filterChain ) throws ServletException, IOException {

        String token = getJwt(request) ;

        if(token == null) {
            filterChain.doFilter(request,response);
            return;
        }

        String userName = jwtProvider.validateJwt(token) ;

        User userDetails =(User) userDetailsService.loadUserByUsername(userName);

        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken( userDetails , null , userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request,response);
    }

    private String getJwt(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");

        if(!StringUtils.isEmpty(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7) ;
        }

        return null ;
    }

}