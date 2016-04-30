package hu.progtech.setcardgame.ui;

import hu.progtech.setcardgame.bl.Card;
import hu.progtech.setcardgame.bl.Deck;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * Created by marianna on 2016.04.15..
 */
public class ControllerMain {

    @FXML
    GridPane gridPaneDeck;

    @FXML
    Canvas canvas0;

    @FXML
    public void handleNewGame(ActionEvent actionEvent) {
        Deck deck = new Deck();
        GraphicsContext gc;

        DrawCard drawCard = new DrawCard(canvas0.getGraphicsContext2D());

        for(int i = 0; i< 3;i++) {
            for(int j = 0;j<4;j++) {
                gc = ((Canvas) gridPaneDeck.getChildren().get(i*4+j)).getGraphicsContext2D();
                drawCard.setGc(gc);
                drawCard.setCard(deck.getDeck().get(i*4+j));
                drawCard.draw();
            }
        }
    }

    @FXML
    public void handleRestart(ActionEvent actionEvent) {
    }

    @FXML
    public void handleRules(ActionEvent actionEvent) {
    }

    @FXML
    public void handleUndo(ActionEvent actionEvent) {
    }

    @FXML
    public void handleSave(ActionEvent actionEvent) {
    }

    @FXML
    public void handleLeaderBoard(ActionEvent actionEvent) {
    }

    @FXML
    public void handleResume(ActionEvent actionEvent) {
    }

    @FXML
    public void handlePause(ActionEvent actionEvent) {
    }

    @FXML
    public void handleCard(ActionEvent actionEvent) {
        System.out.println(((Button) actionEvent.getSource()).getId());
    }

    Canvas canvas;

    @FXML
    protected void drawClicked(MouseEvent me) {
        canvas = (Canvas) me.getSource();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setLineWidth(10);
        gc.setStroke(Color.CORAL);
        gc.strokeRoundRect(0,0,canvas.getWidth(),canvas.getHeight(),10,10);


    }
}

