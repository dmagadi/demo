/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.controller;

import com.cms.scenes.Accessor;
import data.model.UserData;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Aamir
 */
public class MainScreenController {
    private UserData currentUser = null;

    public UserData getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserData currentUser) {
        this.currentUser = currentUser;
    }
    
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem addUserMenuItem;
    @FXML
    private MenuItem editUserMenuItem;
    @FXML
    private MenuItem deleteUserMenuItem;
    @FXML
    private MenuItem addContactMenuItem;
    @FXML
    private MenuItem editContactMenuItem;
    @FXML
    private MenuItem deleteContactMenuItem;
    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private void onAddUserMenuItemAction(ActionEvent event) {
    }

    @FXML
    private void onEditUserMenuItemAction(ActionEvent event) {
    }

    @FXML
    private void onDeleteUserMenuItemAction(ActionEvent event) {
    }

    @FXML
    private void onAddContactMenuItem(ActionEvent event) throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add Contact");
        Parent addContactBoxLayout = FXMLLoader.load(new Accessor().getURL("AddContactScreenLayout.fxml"));
        Scene scene = new Scene(addContactBoxLayout, 400, 200);
        window.setScene(scene);
        window.showAndWait();
    }

    @FXML
    private void onEditContactMenuItemAction(ActionEvent event) {
    }

    @FXML
    private void onDeleteContactMenuItemAction(ActionEvent event) {
    }

    @FXML
    private void onAboutMenuItemAction(ActionEvent event) {
    }
    
}
