
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MyApp extends Application {

    Stage window;
    int i = -1;
    Text text1;
    Text text2;
    String[] strings = {"PIKACHU", "BUBBY", "FAZZY"};

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        Group root = new Group();
        window.setTitle("Java App");
        text1 = new Text("Hi!");
        Button btn = new Button();
        btn.setText("Click");
        text2 = new Text();
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeText();
            }
        });
        root.getChildren().addAll(text1, btn, text2);
        Scene scene = new Scene(root, 300, 200);
        window.setScene(scene);

    }

    private void changeText() {
        if (i == 2) {
            i = 0;
            text2.setText(strings[i]);
        } else {
            i++;
            text2.setText(strings[i]);
        }
    }

}
