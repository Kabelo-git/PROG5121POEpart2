/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainprogram;

/**
 *
 * @author RC_Student_lab
 */
import java.util.regex.*; // Used for pattern matching (W3Schools, 2024)

public class AccountCreator {
    private String userId;
    private String secureKey;
    private String mobile;

    public AccountCreator(String userId, String secureKey, String mobile) {
        this.userId = userId;
        this.secureKey = secureKey;
        this.mobile = mobile;
    }

    public boolean validateUserId() {
        return userId.contains("_") && userId.length() <= 5;
    }

    public boolean validateSecureKey() {
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$"; // Password complexity pattern
        return secureKey.matches(regex);
    }

    public boolean validateMobileNumber() {
        return mobile.matches("^\\+27\\d{9}$");
    }

    public String getUserId() {
        return userId;
    }

    public String getSecureKey() {
        return secureKey;
    }
}
