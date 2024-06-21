package org.example.todolistappproject;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("WelcomePage.fxml")));
        primaryStage.setTitle("Taskly");
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/org/example/todolistappproject/img/icons8-to-do-list-48.png"))));
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.setMaxHeight(800);
        primaryStage.setMaxWidth(1000);
        root.getStylesheets()
                .add(Objects.requireNonNull(getClass()
                                .getResource("/styles.css"))
                        .toExternalForm());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

