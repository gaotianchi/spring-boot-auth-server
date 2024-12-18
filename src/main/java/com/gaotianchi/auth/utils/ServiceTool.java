package com.gaotianchi.auth.utils;

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
}
