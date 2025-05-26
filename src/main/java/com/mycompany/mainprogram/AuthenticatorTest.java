/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainprogram;

/**
 *
 * @author RC_Student_lab
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AuthenticatorTest {

    @Test
    public void testSuccessfulLogin() {
        Authenticator auth = new Authenticator("kyl_1", "Ch&&sec@ke99!", "Kyle", "Smith");
        assertTrue(auth.authenticate("kyl_1", "Ch&&sec@ke99!"), "Login should succeed with correct credentials");
    }

    @Test
    public void testFailedLoginWrongPassword() {
        Authenticator auth = new Authenticator("kyl_1", "Ch&&sec@ke99!", "Kyle", "Smith");
        assertFalse(auth.authenticate("kyl_1", "wrongpass"), "Login should fail with incorrect password");
    }

    @Test
    public void testFailedLoginWrongUsername() {
        Authenticator auth = new Authenticator("kyl_1", "Ch&&sec@ke99!", "Kyle", "Smith");
        assertFalse(auth.authenticate("wrong_user", "Ch&&sec@ke99!"), "Login should fail with incorrect username");
    }

    @Test
    public void testLoginMessageSuccess() {
        Authenticator auth = new Authenticator("kyl_1", "Ch&&sec@ke99!", "Kyle", "Smith");
        String message = auth.getAuthMessage(true);
        assertEquals("Greetings Kyle Smith, welcome back!", message);
    }

    @Test
    public void testLoginMessageFailure() {
        Authenticator auth = new Authenticator("kyl_1", "Ch&&sec@ke99!", "Kyle", "Smith");
        String message = auth.getAuthMessage(false);
        assertEquals("Login failed. Incorrect user ID or secure key.", message);
    }
}
