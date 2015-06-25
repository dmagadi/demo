/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Aamir
 */
class LoginSettingsScreenController implements Initializable {

    @FXML
    TextField textField;
    @FXML
    TextField userField;
    @FXML
    TextField passField;
    private LoginScreenController loginSceneController;
    @FXML
    Text message;
    @FXML
    public void onSaveButtonClick() {
        save();
    }

    @FXML
    public void onSetDefaultClick() {
        textField.setText("localhost:3306");
        userField.setText("root");
        passField.setText("12345");
        save();
    }
    @FXML
    public void onExitButtonClick() {
        exit();
    }
    private Scene loginScene;

    protected void setReturnScene(Scene scene) {
        this.loginScene = scene;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    protected void passController(LoginScreenController controller) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.loginSceneController = controller;
    }

    private void save() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        loginSceneController.setServerIP(textField.getText());
        loginSceneController.setServerUser(userField.getText());
        loginSceneController.setServerPassword(passField.getText());
        message.setFill(Color.GREEN);
        message.setText("Saved");
    }

    private void exit() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        message.setText("");
        Stage window = (Stage) textField.getScene().getWindow();
        window.setScene(loginScene);
    }

}
