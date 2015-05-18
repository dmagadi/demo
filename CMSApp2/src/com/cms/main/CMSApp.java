package com.cms.main;

import com.cms.scenes.Accessor;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        frame = primaryStage;
        frame.setTitle("Contact Management System");
        Parent root = FXMLLoader.load(new Accessor().getURL("LoginScreenLayout.fxml"));
        Scene loginScene = new Scene(root, 854, 480);
        frame.setScene(loginScene);
        frame.show();
    }

}
