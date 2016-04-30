package hu.progtech.setcardgame.ui;

import hu.progtech.setcardgame.bl.Card;
import hu.progtech.setcardgame.bl.Deck;
import hu.progtech.setcardgame.bl.SetOfCards;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import org.apache.commons.lang.time.StopWatch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marianna on 2016.04.15..
 */
public class ControllerMain {

    private int numberOfSets;

    private StopWatch stopWatch;

    private Canvas canvas;

    private Deck deck;

    private SetOfCards setOfCards;

    private List<Card> cardsDisplayed;

    private DrawCard drawCard;

    @FXML
    private GridPane gridPaneDeck;

    @FXML
    private Canvas canvas0;

    @FXML
    private Button tRestart;

    @FXML
    private Button tPause;

    @FXML
    private Button tResume;

    @FXML
    private Button tSave;

    @FXML
    private Button tHint;

    @FXML
    private Label lNumOfSets;

    @FXML
    private Label lTime;

    @FXML
    private Label lMsg;

    @FXML
    public void handleNewGame(ActionEvent actionEvent) {

        tRestart.setDisable(false);
        tSave.setDisable(false);
        tPause.setDisable(false);
        tHint.setDisable(false);
        gridPaneDeck.setVisible(true);

        deck = new Deck();
        cardsDisplayed = new ArrayList<>();
        setOfCards = new SetOfCards();
        GraphicsContext gc;

        drawCard = new DrawCard(canvas0.getGraphicsContext2D());

        for(int i = 0; i< 3;i++) {
            for(int j = 0;j<4;j++) {
                cardsDisplayed.add(deck.getNextCard());
                gc = ((Canvas) gridPaneDeck.getChildren().get(i*4+j)).getGraphicsContext2D();
                drawCard.setGc(gc);
                drawCard.setCard(cardsDisplayed.get(cardsDisplayed.size()-1));
                drawCard.draw();
            }
        }

        numberOfSets = 0;
        lNumOfSets.setText( Integer.toString(numberOfSets));

        stopWatch = new StopWatch();
        stopWatch.start();

        lTime.setText(stopWatch.toString());
        lMsg.setText("");
        lMsg.setTextFill(Color.DARKRED);

    }

    @FXML
    public void handleRestart(ActionEvent actionEvent) {
        stopWatch.reset();
        stopWatch.start();
        //TODO
    }

    @FXML
    public void handleRules(ActionEvent actionEvent) {
    }

    @FXML
    public void handleUndo(ActionEvent actionEvent) {
    }

    @FXML
    public void handleSave(ActionEvent actionEvent) {
        tRestart.setDisable(true);
        tSave.setDisable(true);
        tPause.setDisable(true);
        tResume.setDisable(true);
        gridPaneDeck.setVisible(false);
        stopWatch.stop();
    }

    @FXML
    public void handleLeaderBoard(ActionEvent actionEvent) {
    }

    @FXML
    public void handleResume(ActionEvent actionEvent) {
        tPause.setDisable(false);
        tResume.setDisable(true);

        gridPaneDeck.setVisible(true);
        stopWatch.resume();
    }

    @FXML
    public void handlePause(ActionEvent actionEvent) {
        tResume.setDisable(false);
        tPause.setDisable(true);

        gridPaneDeck.setVisible(false);
        stopWatch.suspend();
    }


    @FXML
    protected void drawClicked(MouseEvent me) {
        System.out.println(setOfCards.toString());

        canvas = (Canvas) me.getSource();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(10);

        Card currentCard = cardsDisplayed.get(gridPaneDeck.getRowIndex(canvas)*4+gridPaneDeck.getColumnIndex(canvas));

        if(setOfCards.getCardSet().contains(currentCard)) {
            setOfCards.removeFromSetOfCards(currentCard);
            gc.setStroke(Color.WHITE);
            gc.strokeRoundRect(0,0,canvas.getWidth(),canvas.getHeight(),10,10);
            gc.setLineWidth(5);
            gc.setStroke(Color.BLACK);
            gc.strokeRoundRect(0,0,canvas.getWidth(),canvas.getHeight(),10,10);
        } else {
            gc.setStroke(Color.CORAL);
            gc.strokeRoundRect(0,0,canvas.getWidth(),canvas.getHeight(),10,10);
            setOfCards.addCardtoSet(currentCard);
            if(setOfCards.getCardSet().size()==3) {
                if(setOfCards.isSet()) {
                    clearBorder();
                    changeNewSet();
                    numberOfSets++;
                    lNumOfSets.setText( Integer.toString(numberOfSets));
                }else {
                    clearBorder();
                    setOfCards.getCardSet().clear();
                }
            }

        }

    }

    private void clearBorder() {
        if(!setOfCards.getCardSet().isEmpty()) {
            for (Card card : setOfCards.getCardSet()) {
                canvas = (Canvas) gridPaneDeck.getChildren().get(cardsDisplayed.indexOf(card));
                GraphicsContext gc = (canvas).getGraphicsContext2D();
                gc.setLineWidth(10);
                gc.setStroke(Color.WHITE);
                gc.strokeRoundRect(0, 0, canvas.getWidth(), canvas.getHeight(), 10, 10);

                gc.setLineWidth(5);
                gc.setStroke(Color.BLACK);
                gc.strokeRoundRect(0, 0, canvas.getWidth(), canvas.getHeight(), 10, 10);
            }
        }
    }

    private void drawBorderForHint() {
        for(Card card: setOfCards.getCardSet()) {
            canvas = (Canvas)gridPaneDeck.getChildren().get(cardsDisplayed.indexOf(card));
            GraphicsContext gc = (canvas).getGraphicsContext2D();
            gc.setLineWidth(10);
            gc.setStroke(Color.DEEPPINK);
            gc.strokeRoundRect(0,0,canvas.getWidth(),canvas.getHeight(),10,10);
        }
    }

    private void changeNewSet() {
        Card newCard;
        for(Card card: setOfCards.getCardSet()) {
            GraphicsContext gc = ((Canvas) gridPaneDeck.getChildren().get(cardsDisplayed.indexOf(card))).getGraphicsContext2D();
            newCard = deck.getNextCard();
            drawCard = new DrawCard(newCard,gc);
            drawCard.draw();
            cardsDisplayed.set(cardsDisplayed.indexOf(card),newCard);
        }
        setOfCards.getCardSet().clear();
    }

   @FXML
   private void handleHint() {
       GraphicsContext gc;
       clearBorder();
       setOfCards.getCardSet().clear();
           for (int i = 0; i < 10; i++) {
               for (int z = i + 1; z < 11; z++) {
                   for (int u = z + 1; u < 12; u++) {
                       if (cardsDisplayed.get(i) == null || cardsDisplayed.get(z) == null || cardsDisplayed.get(u) == null)
                       {
                           continue;
                       }
                       setOfCards.getCardSet().add(cardsDisplayed.get(i));
                       setOfCards.getCardSet().add(cardsDisplayed.get(z));
                       setOfCards.getCardSet().add(cardsDisplayed.get(u));

                       if (setOfCards.isSet()) {
                           System.out.println(i + " " + z + " " + u);
                           drawBorderForHint();
                           return;
                       } else {
                           setOfCards.getCardSet().clear();

                       }
                   }
               }

           }
           lMsg.setText("No sets available!");
   }
}

