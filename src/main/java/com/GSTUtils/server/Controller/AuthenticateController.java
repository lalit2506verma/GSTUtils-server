package com.GSTUtils.server.Controller;

import com.GSTUtils.server.Model.JWTRequest;
import com.GSTUtils.server.Service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/login")
    public String generateToken(@RequestBody JWTRequest jwtRequest) {
        System.out.println("Controller hit");
        System.out.println(jwtRequest.getUsername());
        System.out.println(jwtRequest.getPassword());
        try {
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

        } catch (UsernameNotFoundException e) {

            throw new UsernameNotFoundException("Username not found");
        }

        // user is authenticated -> return JWT token
        return jwtService.getToken(jwtRequest.getUsername());
    }

    // method to authenticate the username and password
    private void authenticate(String username, String password){
        try{
            Authentication authentication =
                    authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (BadCredentialsException e){
            throw new BadCredentialsException("Bad Credentials");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
