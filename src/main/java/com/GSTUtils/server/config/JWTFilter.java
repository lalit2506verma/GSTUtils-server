package com.GSTUtils.server.config;

import com.GSTUtils.server.Service.Impl.MyUserDetailsService;
import com.GSTUtils.server.Service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // extracting authentication header from the HttpServletRequest
        // Authentication -> Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.KMUFsIDTnFmyG3nMiGM6H9FNFUROf3wh7SmqJp-QV30

        String authHeader = request.getHeader("Authorization");
        // removed Bearer from the token
        String token = null;
        String username = null;
        System.out.println(authHeader);

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }

        // check username is not null and it is not already authenticated previously
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);
            System.out.println(userDetails.getUsername());

            // validating the token
            if(jwtService.validateToken(token, userDetails)){
                System.out.println("token validated");
                UsernamePasswordAuthenticationToken authtoken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // setting token in the securityContextHolder chain
                SecurityContextHolder.getContext().setAuthentication(authtoken);
            }
            else{
                System.out.println("Not validated");
            }
        }

        // proceeding with other filters - normal flow
        filterChain.doFilter(request, response);

    }
}
