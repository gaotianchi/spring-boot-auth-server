package com.gaotianchi.auth.utils;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author gaotianchi
 * @since 2024/12/16 16:20
 **/
public class ServiceTool {

    /**
     * Generate a random four-digit number
     *
     * @return verification code:  a random four-digit number
     * @author gaotianchi
     * @since 2024/12/16 11:28
     **/
    public static int generateVerificationCode() {
        return (int) (Math.random() * 9000) + 1000;
    }

    /**
     * Get the current logged-in username
     *
     * @return java.lang.String
     * @author gaotianchi
     * @since 2024/12/18 8:46
     **/
    public static String getCurrentLoggedInUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
