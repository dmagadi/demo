/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package add.records;

import add.records.gui.scenes.Accessor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private static void handleProperties() {

        String userHomeFolder = System.getProperty("user.home");

        new File(userHomeFolder + "/addrecords").mkdirs();
        String propertyFile = userHomeFolder + "/addrecords/addrecords.properties";
        if (!new File(propertyFile).exists()) {
            try {
                //create file
                new File(userHomeFolder + "/addrecords/addrecords.properties").createNewFile();

                Properties prop = new Properties();
                OutputStream output = null;

                try {

                    output = new FileOutputStream(propertyFile);

                    // set the properties value
                    prop.setProperty("database", "localhost");
                    prop.setProperty("dbuser", "root");
                    prop.setProperty("dbpassword", "");

                    // save properties to project root folder
                    prop.store(output, null);

                } catch (IOException io) {
                    throw new RuntimeException(io);
                } finally {
                    if (output != null) {
                        try {
                            output.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }

            } catch (IOException ex) {
                Logger.getLogger(AddRecords.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
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

        handleProperties();
        launch(args);
    }

}
