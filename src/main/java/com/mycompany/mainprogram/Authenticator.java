/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainprogram;

/**
 *
 * @author RC_Student_lab
 */
public class Authenticator {
    private String savedUserId;
    private String savedSecureKey;
    private String givenName;
    private String surname;

    public Authenticator(String savedUserId, String savedSecureKey, String givenName, String surname) {
        this.savedUserId = savedUserId;
        this.savedSecureKey = savedSecureKey;
        this.givenName = givenName;
        this.surname = surname;
    }

    public boolean authenticate(String inputUserId, String inputSecureKey) {
        return savedUserId.equals(inputUserId) && savedSecureKey.equals(inputSecureKey);
    }

    public String getAuthMessage(boolean status) {
        if (status) {
            return "Greetings " + givenName + " " + surname + ", welcome back!";
        } else {
            return "Login failed. Incorrect user ID or secure key.";
        }
    }
}
