/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.gui.controller;

import data.UserBO;
import data.model.UserData;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Aamir
 */
public class DeleteUserScreenController implements Initializable {

    private UserBO userBO = new UserBO();
    @FXML
    private TableView<UserData> userTableView;
    @FXML
    private TableColumn<UserData, Integer> idColumn;
    @FXML
    private TableColumn<UserData, String> userColumn;
    @FXML
    private TableColumn<UserData, String> passColumn;
    @FXML
    private TableColumn<UserData, String> firstNameColumn;
    @FXML
    private TableColumn<UserData, String> lastNameColumn;
    @FXML
    private TableColumn<UserData, Timestamp> createdTSColumn;
    @FXML
    private TableColumn<UserData, Timestamp> modifiedTSColumn;
    @FXML
    private TableColumn<UserData, Boolean> isAdminColumn;
    @FXML
    private Button refreshButton;
    @FXML
    private Button deleteUserButton;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idColumn.setCellValueFactory(new PropertyValueFactory<UserData, Integer>("id"));
        userColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("userName"));
        passColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("password"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<UserData, String>("lastName"));
        createdTSColumn.setCellValueFactory(new PropertyValueFactory<UserData, Timestamp>("createdTS"));
        modifiedTSColumn.setCellValueFactory(new PropertyValueFactory<UserData, Timestamp>("modifiedTS"));
        isAdminColumn.setCellValueFactory(new PropertyValueFactory<UserData, Boolean>("isAdmin"));
        userTableView.setItems(userBO.getUsers());
    }

    @FXML
    private void onRefreshButtonPressed(ActionEvent event) {
        userTableView.setItems(userBO.getUsers());
    }

    @FXML
    private void onDeleteUserButtonPressed(ActionEvent event) {
        UserData selectedUser = userTableView.getSelectionModel().getSelectedItem();
        userTableView.getItems().remove(selectedUser);
        userBO.deleteUser(selectedUser);
    }

}
