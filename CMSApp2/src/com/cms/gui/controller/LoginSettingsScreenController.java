/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.gui.controller;

import data.file.ReadFileHandler;
import data.file.WriteToFileHandler;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    WriteToFileHandler writeHandler = new WriteToFileHandler();
    File file = new File("LoginSettings");
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
        try {
            save();
        } catch (IOException ex) {
            Logger.getLogger(LoginSettingsScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void onSetDefaultClick() {
        setDefault();

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
        if (file.exists()) {
            String[] items = new ReadFileHandler().readFile();
            textField.setText(items[0]);
            userField.setText(items[1]);
            passField.setText(items[2]);
        } else {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(LoginSettingsScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
            setDefault();

        }
    }

    protected void passController(LoginScreenController controller) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.loginSceneController = controller;
    }

    private void save() throws IOException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        loginSceneController.setServerIP(textField.getText());
        loginSceneController.setServerUser(userField.getText());
        loginSceneController.setServerPassword(passField.getText());
        if (!file.exists()) {
            file.createNewFile();
        }
        writeHandler.writeToFile(textField.getText(), userField.getText(), passField.getText());
        message.setFill(Color.GREEN);
        message.setText("Saved");
    }

    private void exit() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        message.setText("");
        Stage window = (Stage) textField.getScene().getWindow();
        window.setScene(loginScene);
    }

    private void setDefault() {
        textField.setText("localhost:3306");
        userField.setText("root");
        passField.setText("12345");

        try {
            save();
        } catch (IOException ex) {
            Logger.getLogger(LoginSettingsScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
