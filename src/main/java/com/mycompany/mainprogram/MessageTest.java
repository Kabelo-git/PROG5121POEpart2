/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainprogram;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    void testRecipientNumberCorrectFormat() {
        Message msg = new Message("+27834567890", "Hello there!");
        assertTrue(msg.checkRecipientCell());
    }

    @Test
    void testRecipientNumberIncorrectFormat() {
        Message msg = new Message("0834567890", "Hello there!");
        assertFalse(msg.checkRecipientCell());
    }

    @Test
    void testMessageLengthValid() {
        Message msg = new Message("+27834567890", "Short message");
        assertTrue(msg.checkMessageLength());
    }

    @Test
    void testMessageLengthTooLong() {
        String longMessage = "a".repeat(260);
        Message msg = new Message("+27834567890", longMessage);
        assertFalse(msg.checkMessageLength());
    }

    @Test
    void testMessageIDLength() {
        Message msg = new Message("+27834567890", "Testing ID length");
        assertEquals(9, msg.generateMessageID().length());
    }

    @Test
    void testMessageHashFormat() {
        Message msg = new Message("+27834567890", "Hi Keegan, did you receive the payment?");
        String hash = msg.createMessageHash();
        assertTrue(hash.matches("\\d{2}:\\d+:[A-Z]+[A-Z]+"));
    }

    @Test
    void testMessageIDAndHashAreNotNull() {
        Message msg = new Message("+27834567890", "Test message");
        assertNotNull(msg.createMessageHash());
        assertNotNull(msg.generateMessageID());
    }

    @Test
    void testJSONStorageDoesNotThrow() {
        Message msg = new Message("+27834567890", "Storing to JSON test");
        assertDoesNotThrow(() -> msg.storeMessageToJSON());
    }
}
