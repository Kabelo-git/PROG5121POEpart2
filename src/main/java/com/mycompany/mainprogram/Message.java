/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainprogram;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;

public class Message {
    private static int messageCounter = 0;
    private String recipient;
    private String message;
    private String messageID;
    private String messageHash;

    public Message(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
        messageCounter++;
    }

    public static int getMessageCounter() {
        return messageCounter;
    }

    public boolean checkRecipientCell() {
        return recipient.matches("\\+27\\d{9}");
    }

    public boolean checkMessageLength() {
        return message.length() <= 250;
    }

    public String generateMessageID() {
        Random rand = new Random();
        int num = 100000000 + rand.nextInt(900000000);
        return String.valueOf(num);
    }

    public String createMessageHash() {
        String[] words = message.split(" ");
        String first = words.length > 0 ? words[0].toUpperCase() : "";
        String last = words.length > 1 ? words[words.length - 1].toUpperCase() : first;
        return messageID.substring(0, 2) + ":" + messageCounter + ":" + first + last;
    }

    public void displayMessageDetails() {
        JOptionPane.showMessageDialog(null, "Message Sent:\n" +
            "Message ID: " + messageID + "\n" +
            "Message Hash: " + messageHash + "\n" +
            "Recipient: " + recipient + "\n" +
            "Message: " + message);
    }

    public void storeMessageToJSON() {
        File jsonFile = new File("messages.json");
        try {
            if (!jsonFile.exists()) {
                jsonFile.createNewFile();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Could not create file: " + ex.getMessage());
        }

        JSONObject obj = new JSONObject();
        obj.put("messageID", messageID);
        obj.put("messageHash", messageHash);
        obj.put("recipient", recipient);
        obj.put("message", message);

        try (FileWriter file = new FileWriter(jsonFile, true)) {
            file.write(obj.toJSONString() + "\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing to JSON: " + e.getMessage());
        }
    }
}


