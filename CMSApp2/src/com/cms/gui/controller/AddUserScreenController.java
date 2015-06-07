/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.gui.controller;

import data.UserBO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onApplyButtonPressed(ActionEvent event) {
        message1.setText("");
        message2.setText("");
        if (!passInput.getText().equals(confirmPassInput.getText())) {
            isValidUser = false;
            message1.setText("Password does not match");
        } else if (passInput.getText().isEmpty() || confirmPassInput.getText().isEmpty() || firstNameInput.getText().isEmpty() || lastNameInput.getText().isEmpty() || userInput.getText().isEmpty()) {
            message2.setText("One or more fields are empty");
            isValidUser = false;
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
            UserBO userBO = new UserBO();
            userBO.addUser(userInput.getText(), passInput.getText(), firstNameInput.getText(), lastNameInput.getText(), adminValue);

        }

    }

}
