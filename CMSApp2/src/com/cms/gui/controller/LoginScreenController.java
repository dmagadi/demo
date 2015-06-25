/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.gui.controller;

import com.cms.gui.scenes.Accessor;
import data.UserBO;
import data.model.UserData;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Aamir
 */
public class LoginScreenController implements Initializable {

    File loginSettingsFile;
    String serverUser;

    public String getServerUser() {
        return serverUser;
    }

    public void setServerUser(String serverUser) {
        this.serverUser = serverUser;
    }

    public String getServerPassword() {
        return serverPassword;
    }

    public void setServerPassword(String serverPassword) {
        this.serverPassword = serverPassword;
    }

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }
    String serverPassword;
    String serverIP;
    @FXML
    TextField userInput;
    @FXML
    PasswordField passInput;

    @FXML
    Text messageText;
    @FXML
    private Button loginButton;

    @FXML
    public void onLoginButtonPressed() throws IOException, InterruptedException {

        UserBO userBO = new UserBO();
        UserData user = userBO.login(userInput.getText(), passInput.getText(), serverIP, serverUser, serverPassword);
        if (user != null) {
            Stage window = (Stage) userInput.getScene().getWindow();
            // Parent mainSceneLayout = FXMLLoader.load(new Accessor().getURL("MainScreenLayout.fxml"));

            FXMLLoader loader = new FXMLLoader(new Accessor().getURL("MainScreenLayout.fxml"));

            Scene mainScene = new Scene((Parent) loader.load(), 854, 480);

            MainScreenController controller = loader.<MainScreenController>getController();

            controller.setCurrentUser(user);
            controller.enableUserMenu();
            window.setScene(mainScene);
        } else {
            passInput.setText("");
            messageText.setVisible(true);
        }

    }

    @FXML
    public void onLoginSettingsButtonClick() throws IOException, InterruptedException {

        Stage window = (Stage) userInput.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(new Accessor().getURL("LoginSettingsScreenLayout.fxml"));
        Scene loginSettingsScene = new Scene((Parent) loader.load(), 854, 480);
        LoginSettingsScreenController controller = loader.<LoginSettingsScreenController>getController();
        controller.setReturnScene(loginButton.getScene());
        controller.passController(this);
        window.setScene(loginSettingsScene);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        loginSettingsFile = new File("LoginSettings.txt");
    }

}
