package hu.progtech.setcardgame.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends Application {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    @Override
    public void start(Stage primaryStage) throws Exception{

        final Parent rootParent = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));

        Scene scene = new Scene(rootParent, 750, 600);

        primaryStage.setOnCloseRequest(event -> Platform.exit());

        primaryStage.setTitle("SetCardGame");
        primaryStage.setScene(scene);

        logger.info("The application started!");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}