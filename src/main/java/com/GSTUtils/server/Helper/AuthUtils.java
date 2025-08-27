package com.GSTUtils.server.Helper;

import com.GSTUtils.server.Model.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtils {

    public static UserPrincipal getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserPrincipal) authentication.getPrincipal();
    }


    public static Long getCurrentUserId() {

        return getCurrentUser().getId();

    }
}
