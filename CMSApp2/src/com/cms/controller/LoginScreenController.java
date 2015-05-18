/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.controller;

import com.cms.scenes.Accessor;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Aamir
 */
public class LoginScreenController {

    @FXML
    TextField userInput;
    @FXML
    PasswordField passInput;

    @FXML
    Text messageText;

    @FXML
    public void onLoginButtonPressed() throws IOException {

        if (userInput.getText().equals("admin") && passInput.getText().equals("admin")) {
            Stage window = (Stage) userInput.getScene().getWindow();
            Parent mainSceneLayout = FXMLLoader.load(new Accessor().getURL("MainScreenLayout.fxml"));
            Scene mainScene = new Scene(mainSceneLayout, 854, 480);
            window.setScene(mainScene);
        } else {
            passInput.setText("");
            messageText.setText("Failed to log in");
        }

    }

}
