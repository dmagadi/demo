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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    /**
     *
     * @param bytes
     * @return
     */
    private static String createHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    /**
     * Initializes the controller class.
     *
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

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {

        }

        byte[] thedigest = md.digest(passInput.getText().getBytes());
        String passwordHash = createHexString(thedigest);
        System.out.println(passwordHash);

        UserBO userBO = new UserBO();
        if (userBO.login(userInput.getText(), passwordHash)) {
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
