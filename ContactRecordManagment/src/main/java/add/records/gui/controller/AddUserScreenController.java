/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package add.records.gui.controller;

import add.records.data.Email;
import add.records.data.PhoneNumber;
import data.UserBO;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Aamir
 */
public class AddUserScreenController implements Initializable {
    @FXML
    private PasswordField passInput;
    @FXML
    private PasswordField confirmPassInput;
    @FXML
    private TextField firstNameInput;
    @FXML
    private TextField lastNameInput;
    @FXML
    private TextField userInput;
    @FXML
    private Text message2;
    @FXML
    private Text message1;
    @FXML
    private CheckBox adminCheckBox;
    private Boolean isValidUser = false;
    @FXML
    private TextField phoneNumberInput;
    @FXML
    private ChoiceBox<String> phoneTypeMenu;
    @FXML
    private TextField emailInput;
    @FXML
    private ChoiceBox<String> emailTypeMenu;
    @FXML
    private GridPane gridPane;
    @FXML
    private TextField emailInput2;
    @FXML
    private ChoiceBox<String> emailTypeMenu2;
    @FXML
    private TextField emailInput3;
    @FXML
    private ChoiceBox<String> emailTypeMenu3;
    @FXML
    private TextField phoneNumberInput2;
    @FXML
    private TextField phoneNumberInput3;
    @FXML
    private ChoiceBox<String> phoneTypeMenu2;
    @FXML
    private ChoiceBox<String> phoneTypeMenu3;
    
    
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    /**
     * 
     * @param bytes
     * @return 
     */
    private static String createHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        createPhoneTypeMenu(phoneTypeMenu);
        createPhoneTypeMenu(phoneTypeMenu2);
        createPhoneTypeMenu(phoneTypeMenu3);
        createEmailTypeMenu(emailTypeMenu);
        createEmailTypeMenu(emailTypeMenu2);
        createEmailTypeMenu(emailTypeMenu3);
    }    

    @FXML
    private void onApplyButtonPressed(ActionEvent event) {
        
        message1.setText("");
        message2.setText("");
        if (passInput.getText().isEmpty() || confirmPassInput.getText().isEmpty() || firstNameInput.getText().isEmpty() || lastNameInput.getText().isEmpty() || userInput.getText().isEmpty() || phoneNumberInput.getText().isEmpty() || emailInput.getText().isEmpty()) {
            message2.setFill(Color.rgb(255, 0, 0));
            message2.setText("One or more fields are empty");
            isValidUser = false;
        } else if (!passInput.getText().equals(confirmPassInput.getText())) {
            isValidUser = false;
            message1.setText("Password does not match");
        } else {
            isValidUser = true;
        }
        if (isValidUser) {
            int adminValue;
            if (adminCheckBox.isSelected()) {
                adminValue = 1;
            } else {
                adminValue = 0;
            }
            
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException ex) {
               
            }
            
            ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
            phoneNumbers.add(new PhoneNumber(phoneNumberInput.getText(), phoneTypeMenu.getValue()));
            phoneNumbers.add(new PhoneNumber(phoneNumberInput2.getText(), phoneTypeMenu2.getValue()));
            phoneNumbers.add(new PhoneNumber(phoneNumberInput3.getText(), phoneTypeMenu3.getValue()));
            ArrayList<PhoneNumber> blankNumbers = new ArrayList<>();
            for (PhoneNumber phoneNumber : phoneNumbers) {
                if (phoneNumber.getNumber() == null || phoneNumber.getNumber().isEmpty()) {
                    blankNumbers.add(phoneNumber);
                }
            }
            phoneNumbers.removeAll(blankNumbers);
            
            ArrayList<Email> emails = new ArrayList<>();
            emails.add(new Email(emailInput.getText(), emailTypeMenu.getValue()));
            emails.add(new Email(emailInput2.getText(), emailTypeMenu2.getValue()));
            emails.add(new Email(emailInput3.getText(), emailTypeMenu3.getValue()));
            ArrayList<Email> blankEmails = new ArrayList<>();
            for (Email email : emails) {
                if (email.getEmail() == null || email.getEmail().isEmpty()) {
                    blankEmails.add(email);
                }
            }
            emails.removeAll(blankEmails);
            
            byte[] thedigest = md.digest(passInput.getText().getBytes());
            String passwordHash = createHexString(thedigest);
            
            UserBO userBO = new UserBO();
            Boolean userAdded = userBO.addUser(userInput.getText(), passwordHash, firstNameInput.getText(), lastNameInput.getText(), adminValue);
            Boolean contactAdded = userBO.addContact(firstNameInput.getText(), lastNameInput.getText(), phoneNumbers, emails);
            
            
            
            if (userAdded && contactAdded) {
                message2.setText("User added");
                message2.setFill(Color.rgb(0, 255, 0));
                passInput.clear();
                confirmPassInput.clear();
                userInput.clear();
                firstNameInput.clear();
                lastNameInput.clear();
                adminCheckBox.setSelected(false);
                phoneNumberInput.clear();
                phoneNumberInput2.clear();
                phoneNumberInput3.clear();
                emailInput.clear();
                emailInput2.clear();
                emailInput3.clear();
            } else {
                message2.setText("Unable to add user");
                message2.setFill(Color.rgb(255, 0, 0));
            }
        }

    }

    private void createPhoneTypeMenu(ChoiceBox<String> phoneTypeMenu) {
        phoneTypeMenu.getItems().addAll("HOME", "WORK", "CELL", "OTHER");
        phoneTypeMenu.setValue("HOME");
    }
    
    private void createEmailTypeMenu(ChoiceBox<String> emailTypeMenu) {
        emailTypeMenu.getItems().addAll("HOME", "WORK", "OTHER");
        emailTypeMenu.setValue("HOME");
    }

    
}
