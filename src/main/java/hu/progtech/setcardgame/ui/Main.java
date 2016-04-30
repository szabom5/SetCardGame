package hu.progtech.setcardgame.ui;

import hu.progtech.setcardgame.bl.Deck;
import hu.progtech.setcardgame.dao.XMLHandler;
import hu.progtech.setcardgame.dao.XMLHandlerDOM;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

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
        XMLHandler h = new XMLHandlerDOM();
        System.out.println(h.readHighScoreTable().toString());
        List<Deck> list = new ArrayList<>();
        for(int i =0;i<20;i++) {
            list.add(new Deck());
        }
        //h.WritePreShuffeledDecks(list);

    }
}