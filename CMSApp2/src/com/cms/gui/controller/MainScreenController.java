/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.gui.controller;

import com.cms.gui.scenes.Accessor;
import data.model.UserData;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Aamir
 */
public class MainScreenController implements Initializable {

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
    private Menu userMenu;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void enableUserMenu() {
        //System.out.println(getCurrentUser());
        if (getCurrentUser().getIsAdmin()) {
            userMenu.setVisible(true);
        }
    }

    @FXML
    private void onAddUserMenuItemAction(ActionEvent event) throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add User");
        Parent addUserSceneParent = FXMLLoader.load(new Accessor().getURL("AddUserScreenLayout.fxml"));
        Scene scene = new Scene(addUserSceneParent, 600, 400);
        window.setScene(scene);
        window.showAndWait();
    }

    @FXML
    private void onEditUserMenuItemAction(ActionEvent event) throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Edit User");
        Parent editUserSceneParent = FXMLLoader.load(new Accessor().getURL("EditUserScreenLayout.fxml"));
        Scene scene = new Scene(editUserSceneParent, 600, 400);
        window.setScene(scene);
        window.showAndWait();
    }

    @FXML
    private void onDeleteUserMenuItemAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Delete User");
        Parent deleteUserSceneParent = FXMLLoader.load(new Accessor().getURL("DeleteUserScreenLayout.fxml"));
        Scene scene = new Scene(deleteUserSceneParent, 600, 400);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void onAddContactMenuItem(ActionEvent event) throws IOException {

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
