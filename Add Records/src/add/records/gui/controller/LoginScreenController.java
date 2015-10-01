/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package add.records.gui.controller;

import add.records.gui.scenes.Accessor;
import data.UserBO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
 * FXML Controller class
 *
 * @author Aamir
 */
public class LoginScreenController implements Initializable {
    @FXML
    private TextField userInput;
    @FXML
    private PasswordField passInput;
    @FXML
    private Button loginButton;
    @FXML
    private Text messageText;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    /**
     * 
     * @param event 
     */
    @FXML
    private void onLoginButtonPressed(ActionEvent event) throws IOException, InterruptedException {
        
        UserBO userBO = new UserBO();
        if (userBO.login(userInput.getText(), passInput.getText())) {
            Stage window = (Stage) userInput.getScene().getWindow();
            // Parent mainSceneLayout = FXMLLoader.load(new Accessor().getURL("MainScreenLayout.fxml"));

            FXMLLoader loader = new FXMLLoader(new Accessor().getURL("AddUserScreenLayout.fxml"));

            Scene mainScene = new Scene((Parent) loader.load(), 854, 480);

            AddUserScreenController controller = loader.<AddUserScreenController>getController();
            window.setScene(mainScene);
        } else {
            passInput.setText("");
            messageText.setVisible(true);
        }
        
    }
    
}
