package hu.progtech.setcardgame.ui;

import hu.progtech.setcardgame.bl.Card;
import hu.progtech.setcardgame.bl.Deck;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * Created by marianna on 2016.04.16..
 */
public class ControllerDeck {

    @FXML
    public void handleCard(ActionEvent actionEvent) {
        System.out.println(((Button) actionEvent.getSource()).getId());
    }


    @FXML
    Canvas sampleCanvas;

    @FXML
    protected void drawClicked(MouseEvent me) {
        GraphicsContext gc = sampleCanvas.getGraphicsContext2D();

        DrawCard drawCard = new DrawCard(gc);
        Deck deck = new Deck();
        for(Card card : deck.getDeck()) {
            drawCard.setCard(card);
        }
        drawCard.draw();

    }

}
