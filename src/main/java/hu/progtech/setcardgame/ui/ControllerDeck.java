package hu.progtech.setcardgame.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created by marianna on 2016.04.16..
 */
public class ControllerDeck {
    @FXML
    public void handleCard(ActionEvent actionEvent) {
        System.out.println(((Button) actionEvent.getSource()).getId());
    }
}
