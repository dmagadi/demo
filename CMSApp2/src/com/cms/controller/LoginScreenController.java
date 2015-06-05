/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.controller;

import com.cms.scenes.Accessor;
import data.UserBO;
import data.model.UserData;
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

        UserBO userBO = new UserBO();
        UserData user = userBO.login(userInput.getText(), passInput.getText());
        if (user != null) {
            Stage window = (Stage) userInput.getScene().getWindow();
           // Parent mainSceneLayout = FXMLLoader.load(new Accessor().getURL("MainScreenLayout.fxml"));
            
            FXMLLoader loader = new FXMLLoader(new Accessor().getURL("MainScreenLayout.fxml"));
            
            // Parent mainSceneLayout = FXMLLoader.load(new Accessor().getURL("MainScreenLayout.fxml"));
            Scene mainScene = new Scene((Parent) loader.load(), 854, 480);
            
            MainScreenController controller = loader.<MainScreenController>getController();
            
            controller.setCurrentUser(user);
            
            
            

            window.setScene(mainScene);
        } else {
            passInput.setText("");
            messageText.setText("Failed to log in");
        }

    }

}
