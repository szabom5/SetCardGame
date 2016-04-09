package hu.progtech.setcardgame.ui;

import hu.progtech.setcardgame.bl.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Start");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println(new Deck().toString());
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 1000, 500);

        primaryStage.setTitle("SetCardGame");
        primaryStage.setScene(scene);

        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}