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

public class AccountCreatorTest {

    @Test
    public void testValidUserId() {
        AccountCreator user = new AccountCreator("abc_1", "Test@123", "+27834567890");
        assertTrue(user.validateUserId(), "Username should be valid (contains underscore and ≤ 5 chars)");
    }

    @Test
    public void testInvalidUserId() {
        AccountCreator user = new AccountCreator("invalidName", "Test@123", "+27834567890");
        assertFalse(user.validateUserId(), "Username should be invalid (too long or no underscore)");
    }

    @Test
    public void testValidSecureKey() {
        AccountCreator user = new AccountCreator("abc_1", "Ch&&sec@ke99!", "+27834567890");
        assertTrue(user.validateSecureKey(), "Password should meet complexity requirements");
    }

    @Test
    public void testInvalidSecureKey() {
        AccountCreator user = new AccountCreator("abc_1", "password", "+27834567890");
        assertFalse(user.validateSecureKey(), "Password should fail complexity rules");
    }

    @Test
    public void testValidMobile() {
        AccountCreator user = new AccountCreator("abc_1", "Test@123", "+27838968976");
        assertTrue(user.validateMobileNumber(), "Cell number should start with +27 and be 12 characters");
    }

    @Test
    public void testInvalidMobile() {
        AccountCreator user = new AccountCreator("abc_1", "Test@123", "08966553");
        assertFalse(user.validateMobileNumber(), "Invalid phone format should be rejected");
    }
}

