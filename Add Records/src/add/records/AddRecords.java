/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package add.records;

import add.records.gui.scenes.Accessor;
import add.records.util.Config;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Aamir
 */
public class AddRecords extends Application {

    
    Stage frame;
    
    
    

    @Override
    public void start(Stage primaryStage) throws IOException {
        frame = primaryStage;
        frame.setTitle("Add Records");
        frame.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(final WindowEvent event) {
                event.consume();
                closeWindow();
            }
        }
        );
        Parent root = FXMLLoader.load(new Accessor().getURL("LoginScreenLayout.fxml"));
        Scene loginScene = new Scene(root, 854, 480);
        frame.setScene(loginScene);
        frame.show();
    }

    private void closeWindow() {

        frame.close();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Config.initProperties("AddRecords");
        launch(args);
    }

}
