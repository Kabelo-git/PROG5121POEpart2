package com.mycompany.mainprogram;

import javax.swing.JOptionPane;

public class MainProgram {
    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog("What is your first name?");
        String surname = JOptionPane.showInputDialog("What is your surname?");

        String userId = "";
        while (true) {
            userId = JOptionPane.showInputDialog("Choose a user ID (must include '_' and be ≤ 5 characters):");
            AccountCreator user = new AccountCreator(userId, "", "");
            if (user.validateUserId()) {
                JOptionPane.showMessageDialog(null, "User ID accepted.");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid user ID. Try again.");
            }
        }

        String secureKey = "";
        while (true) {
            secureKey = JOptionPane.showInputDialog("Create a secure key:\n- 8+ characters\n- 1 uppercase\n- 1 number\n- 1 special symbol");
            AccountCreator user = new AccountCreator(userId, secureKey, "");
            if (user.validateSecureKey()) {
                JOptionPane.showMessageDialog(null, "Secure key accepted.");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Secure key doesn't meet criteria. Try again.");
            }
        }

        String mobile = "";
        while (true) {
            mobile = JOptionPane.showInputDialog("Enter your phone number starting with +27:");
            AccountCreator user = new AccountCreator(userId, secureKey, mobile);
            if (user.validateMobileNumber()) {
                JOptionPane.showMessageDialog(null, "Mobile number verified.");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid phone format. Try again.");
            }
        }

        // Proceed to login
        JOptionPane.showMessageDialog(null, "Now, log in with your credentials.");

        String loginUser = JOptionPane.showInputDialog("User ID:");
        String loginKey = JOptionPane.showInputDialog("Secure Key:");

        Authenticator loginSystem = new Authenticator(userId, secureKey, name, surname);
        boolean accessGranted = loginSystem.authenticate(loginUser, loginKey);

        JOptionPane.showMessageDialog(null, loginSystem.getAuthMessage(accessGranted));

        if (accessGranted) {
            JOptionPane.showMessageDialog(null, "Welcome to QUICKCHAT");

            // Ask recipient number
            String recipient = JOptionPane.showInputDialog("Enter the recipient's mobile number (start with +27):");

            if (!recipient.matches("\\+27\\d{9}")) {
                JOptionPane.showMessageDialog(null, "Invalid recipient number format.");
                return;
            }

            // Ask how many messages to send
            String messageCountStr = JOptionPane.showInputDialog("How many messages do you want to send?");
            int messageCount = 0;

            try {
                messageCount = Integer.parseInt(messageCountStr);
                if (messageCount <= 0) {
                    JOptionPane.showMessageDialog(null, "You must send at least one message.");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                return;
            }

            // Loop through message inputs
            for (int i = 1; i <= messageCount; i++) {
                String messageContent = JOptionPane.showInputDialog("Enter message " + i + " (max 250 characters):");
                Message msg = new Message(recipient, messageContent);

                if (!msg.checkMessageLength()) {
                    JOptionPane.showMessageDialog(null, "Message is too long. Skipping this message.");
                    continue;
                }

                msg.displayMessageDetails();
                msg.storeMessageToJSON();
            }

            JOptionPane.showMessageDialog(null, messageCount + " message(s) sent.");
        }
    }
}
