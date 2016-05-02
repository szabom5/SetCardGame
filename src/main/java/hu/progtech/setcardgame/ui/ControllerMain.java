package hu.progtech.setcardgame.ui;

import hu.progtech.setcardgame.bl.Card;
import hu.progtech.setcardgame.bl.Deck;
import hu.progtech.setcardgame.bl.Score;
import hu.progtech.setcardgame.bl.SetOfCards;
import hu.progtech.setcardgame.dao.XMLHandler;
import hu.progtech.setcardgame.dao.XMLHandlerDOM;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import org.apache.commons.lang.time.StopWatch;

import java.net.URL;
import java.util.*;

/**
 * Created by marianna on 2016.04.15..
 */
public class ControllerMain implements Initializable{

    private XMLHandler h;

    private int numberOfSetsFound;

    private int numberOfHintUsed;

    private Canvas canvas;

    private Deck deck;

    private SetOfCards setOfCards;

    private List<Card> cardsDisplayed;

    private DrawCard drawCard;

    private long lCounter = 0;

    private Timer timer = null;

    private TimerTask timerTask = null;

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

    private String formatTimer(long time) {
        long hours, minutes,seconds,milliseconds;
        milliseconds = time % 100;
        time/=100;
        seconds = time % 60;
        time/=60;
        minutes = time % 60;
        time/=60;
        hours = time;
        return hours+":"+(minutes<10?"0"+minutes:minutes)+":"+(seconds<10?"0"+seconds:seconds)+"."+(milliseconds<10?"0"+milliseconds:milliseconds);
    }

    private void startTimer() {
        timer = new Timer();
        timerTask = new TimerTask() {

            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        lTime.setText(formatTimer(lCounter));
                        lCounter++;
                    }
                });
            }
        };
        timer.schedule(timerTask, 10, 10); //millisec mennyi idonkent frissitse
    }

    private void stopTimer() {
        if(timer != null) timer.cancel();
        if(timerTask != null) timerTask.cancel();
        lCounter = 0;
        lTime.setText(formatTimer(lCounter));
    }

    private void pauseTimer() {
        if(timer != null) timer.cancel();
        if(timerTask != null) timerTask.cancel();
        lTime.setText(formatTimer(lCounter));
    }

    private void refresh() {
        GraphicsContext gc;
        drawCard = new DrawCard(canvas0.getGraphicsContext2D());

        if(cardsDisplayed!=null && !cardsDisplayed.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    gc = ((Canvas) gridPaneDeck.getChildren().get(i * 4 + j)).getGraphicsContext2D();
                    drawCard.setGc(gc);
                    drawCard.setCard(cardsDisplayed.get(i * 4 + j));
                    drawCard.draw();
                }
            }
        }
    }

    @FXML
    public void handleNewGame(ActionEvent actionEvent) {
        tRestart.setDisable(false);
        tSave.setDisable(false);
        tPause.setDisable(false);
        tHint.setDisable(false);
        gridPaneDeck.setVisible(true);

        numberOfHintUsed = 0;
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

        numberOfSetsFound = 0;
        lNumOfSets.setText( Integer.toString(numberOfSetsFound));

        stopTimer();
        startTimer();

        lMsg.setText("");
        lMsg.setTextFill(Color.DARKRED);

    }

    @FXML
    public void handleRestart(ActionEvent actionEvent) {
        tRestart.setDisable(false);
        tSave.setDisable(false);
        tPause.setDisable(false);
        tHint.setDisable(false);
        gridPaneDeck.setVisible(true);

        numberOfHintUsed = 0;
        cardsDisplayed.clear();
        setOfCards.getCardSet().clear();
        GraphicsContext gc;
        drawCard = new DrawCard(canvas0.getGraphicsContext2D());
        deck.resetNextCard();

        for(int i = 0; i< 3;i++) {
            for(int j = 0;j<4;j++) {
                cardsDisplayed.add(deck.getNextCard());
                gc = ((Canvas) gridPaneDeck.getChildren().get(i*4+j)).getGraphicsContext2D();
                drawCard.setGc(gc);
                drawCard.setCard(cardsDisplayed.get(cardsDisplayed.size()-1));
                drawCard.draw();
            }
        }

        numberOfSetsFound = 0;
        lNumOfSets.setText( Integer.toString(numberOfSetsFound));

        stopTimer();
        startTimer();

        lMsg.setText("");
        lMsg.setTextFill(Color.DARKRED);
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

        pauseTimer();

        Double points = numberOfSetsFound*1000.0-numberOfHintUsed*90.0-lCounter/1000;
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Save your score!");
        dialog.setHeaderText("Your new score: "+points);
        dialog.setContentText("Please enter your name:");

        Score score = new Score();

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            score.setName(result.get());
        }
        score.setScore(points);

        h.writeScore(score);

        stopTimer();
    }

    @FXML
    public void handleLeaderBoard(ActionEvent actionEvent) {
        List<Score> list = h.readHighScoreTable();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Leader Board");
        alert.setHeaderText("High Scores");
        String fullLeaderBoard = "";
        for(Score s : list) {
            fullLeaderBoard = fullLeaderBoard.concat(s.toString()+"\n");
        }
        alert.setContentText(fullLeaderBoard);
        alert.showAndWait();
    }

    @FXML
    public void handleResume(ActionEvent actionEvent) {
        tPause.setDisable(false);
        tResume.setDisable(true);

        gridPaneDeck.setVisible(true);
        startTimer();
    }

    @FXML
    public void handlePause(ActionEvent actionEvent) {
        tResume.setDisable(false);
        tPause.setDisable(true);

        gridPaneDeck.setVisible(false);
        pauseTimer();
    }


    @FXML
    protected void drawClicked(MouseEvent me) {
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
                    numberOfSetsFound++;
                    lNumOfSets.setText( Integer.toString(numberOfSetsFound));
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
        if(!checkAvailableSet()) {
            pauseTimer();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("***** Congratulations! *****");
            alert.setHeaderText(null);
            alert.setContentText("You have found all sets available in this deck!\n" +
                    "Please save your score!");
            alert.showAndWait();
        }
    }
    private boolean checkAvailableSet() {
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
                        return true;
                    } else {
                        setOfCards.getCardSet().clear();
                    }
                }
            }

        }
        return false;
    }

   @FXML
   private void handleHint() {
       numberOfHintUsed++;
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

    private void setGridWidthProperties(double width) {
        for(int i = 0; i< 12;i++) {
            ((Canvas) gridPaneDeck.getChildren().get(i)).setWidth(width / 5);

        }

    }

    private void setGridHeightProperties(double height) {
        for(int i = 0; i< 12;i++) {
            ((Canvas) gridPaneDeck.getChildren().get(i)).setHeight(height / 4);
        }

    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        h = new XMLHandlerDOM();

        gridPaneDeck.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                setGridWidthProperties(newSceneWidth.doubleValue());
                refresh();
            }
        });
        gridPaneDeck.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                setGridHeightProperties(newSceneHeight.doubleValue());
                refresh();
            }
        });
    }

}

