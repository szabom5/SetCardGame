package hu.progtech.setcardgame.ui;

import hu.progtech.setcardgame.dao.XMLHandlerDOM;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent rootparent = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));

        Scene scene = new Scene(rootparent, 1000, 500);

        primaryStage.setTitle("SetCardGame");
        primaryStage.setScene(scene);

        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
        XMLHandlerDOM h = new XMLHandlerDOM();
        System.out.println(h.ReadHighScoreTable().toString());
    }
}