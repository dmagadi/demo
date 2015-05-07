package com.cms.main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;

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
        BorderPane root = new BorderPane();
        GridPane loginGridPane = new GridPane();
        Scene loginScene = new Scene(root, 427, 240);
        //loginGridPane.setGridLinesVisible(true);
        loginGridPane.setPadding(new Insets(10));
        loginGridPane.setVgap(5);
        loginGridPane.setHgap(5);

        //Takes up root space
        FlowPane rootTop = new FlowPane();
        rootTop.setPrefHeight(50);
        FlowPane rootBottom = new FlowPane();
        rootBottom.setPrefHeight(50);
        FlowPane rootLeft = new FlowPane();
        rootLeft.setPrefWidth(50);
        FlowPane rootRight = new FlowPane();
        rootRight.setPrefWidth(50);
        root.setTop(rootTop);
        root.setBottom(rootBottom);
        root.setLeft(rootLeft);
        root.setRight(rootRight);
        
        Text topText = new Text("Contact Management System");
        String topStyle = "-fx-background-color: lightgray;";
        rootTop.setStyle(topStyle);
        Font font = new Font("Dialog", 30);
        topText.setFont(font);
        rootTop.getChildren().add(topText);
        
        Text userText = new Text("Username");

        TextField userTextField = new TextField();
        userTextField.setPromptText("Username");

        Text passwordText = new Text("Password");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");

        Text msg = new Text(230, 190, "");

        GridPane.setConstraints(userText, 0, 0);
        GridPane.setConstraints(passwordText, 0, 1);
        GridPane.setConstraints(userTextField, 1, 0);
        GridPane.setConstraints(passwordField, 1, 1);
        GridPane.setConstraints(loginButton, 1, 3);
        GridPane.setConstraints(msg, 1, 4);

        for (Node x : loginGridPane.getChildren()) {
            GridPane.setHalignment(x, HPos.CENTER);
            GridPane.setValignment(x, VPos.CENTER);
            GridPane.setHgrow(x, Priority.ALWAYS);
        }

        Group group = new Group();
        Scene scene = new Scene(group, 427, 240);
        Text text = new Text(20, 20, "Success");
        group.getChildren().addAll(text);
        
        
        loginButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (userTextField.getText().equals("admin") && passwordField.getText().equals("admin")) {
                    frame.setScene(scene);
                } else {
                    msg.setText("Failed");
                }
            }
        });
        root.setCenter(loginGridPane);

        loginGridPane.getChildren().addAll(passwordText, userText, passwordField, userTextField, loginButton, msg);
        frame.setScene(loginScene);
        frame.show();

    }

}
