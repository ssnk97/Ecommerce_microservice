package com.cjss.coordinatorservice;

import com.cjss.coordinatorservice.controller.AccountRestControllerTest;

public class GetLoginToken {
    private static String encryptedToken;

    public static String getEncryptedToken() {
        new AccountRestControllerTest().login();
        return encryptedToken;
    }

    public static void setEncryptedToken(String encryptedToken) {
        GetLoginToken.encryptedToken = encryptedToken;
    }
}
