package com.cms.main;

import java.beans.EventHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

/**
 *
 * @author Aamir
 */
public class CMSApp extends Application {

    Stage frame;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        frame = primaryStage;
        frame.setTitle("Contact Management System");
        Group layout1 = new Group();
        Scene loginScene = new Scene(layout1, 400, 300);
        ///Text mouse_point = new Text(0, 10, "MouseX:     MouseY:     ScreenX:     ScreenY: ");
        //Text SceneText = new Text(0, 20, "Width:     Height: ");
        Text userText = new Text(50, 100, "Username");
        TextField userTextField = new TextField();
        userTextField.setLayoutX(120);
        userTextField.setLayoutY(85);
        Text passwordText = new Text(50, 130, "Password");
        PasswordField passwordField = new PasswordField();
        passwordField.setLayoutX(120);
        passwordField.setLayoutY(115);
        Button loginButton = new Button("Login");
        loginButton.setLayoutX(224);
        loginButton.setLayoutY(145);
        Text msg = new Text(230, 190, "");
        /*
         loginScene.setOnMouseMoved(e -> {
         mouse_point.setText("MouseX: " + e.getX() + "   MouseY: " + e.getY() + "    ScreenX: " + e.getScreenX() + " ScreenY: " + e.getScreenY());
         SceneText.setText("Width: " + e.getSceneX() + "  Height: " + e.getSceneY());
         });
         */
        loginButton.setOnAction(e -> {
            if(userTextField.getText().equals("admin") && passwordField.getText().equals("admin")) {
                msg.setText("Success");
            }
            else {
                msg.setText("Failed");
            }
        });

        layout1.getChildren().addAll(passwordText, userText, passwordField, userTextField, loginButton, msg);
        frame.setScene(loginScene);
        frame.show();

    }

}
