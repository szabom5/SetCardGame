package hu.progtech.setcardgame.ui;

import hu.progtech.setcardgame.bl.Card;
import hu.progtech.setcardgame.bl.Deck;
import hu.progtech.setcardgame.bl.Score;
import hu.progtech.setcardgame.bl.SetOfCards;
import hu.progtech.setcardgame.dao.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.*;

/**
 * Created by marianna on 2016.04.15..
 */
public class ControllerMain implements Initializable{

    private DeckDao deckDao;

    private ScoreDao scoreDao;

    private int numberOfSetsFound;

    private int numberOfHintUsed;

    private Canvas canvas;

    private Deck deck;

    private SetOfCards setOfCards;

    private SetOfCards availableSets;

    private List<Card> cardsDisplayed;

    private DrawCard drawCard;

    private long lCounter = 0;

    private Timer timer = null;

    private TimerTask timerTask = null;

    private TableView<Score> table = new TableView<Score>();

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
                Platform.runLater(() -> {
                    lTime.setText(formatTimer(lCounter));
                    lCounter++;
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
        availableSets = new SetOfCards();
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rules");
        alert.setHeaderText("How to play the Set Card Game");
        alert.setContentText("Rules");

        alert.showAndWait();
    }


    @FXML
    public void handleSave(ActionEvent actionEvent) {
        tRestart.setDisable(true);
        tSave.setDisable(true);
        tPause.setDisable(true);
        tResume.setDisable(true);
        tHint.setDisable(true);
        //gridPaneDeck.setVisible(false);

        pauseTimer();

        Score score = new Score();
        score.setNumberOfSetsFound(numberOfSetsFound);
        score.setNumberOfHintsUsed(numberOfHintUsed);
        score.setTimeUsed(lCounter);
        score.calculateScore();

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Save your score!");
        dialog.setHeaderText("Your new score: "+score.getScore());
        dialog.setContentText("Please enter your name:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            score.setName(result.get());
            scoreDao.writeScore(score);
        }

        stopTimer();
    }

    @FXML
    public void handleLeaderBoard(ActionEvent actionEvent) {
        stopTimer();
        tRestart.setDisable(true);
        tSave.setDisable(true);
        tPause.setDisable(true);
        tResume.setDisable(true);
        tHint.setDisable(true);

        List<Score> list = scoreDao.readHighScoreTable();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Leader Board");
        alert.setHeaderText("High scores");
        alert.getDialogPane().setContent(table);
        alert.getDialogPane().setMinWidth(410);

        list.sort(Comparator.comparing(Score::getScore));
        table.getItems().setAll(list);
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

        Card currentCard = cardsDisplayed.get(gridPaneDeck.getRowIndex(canvas)*4+gridPaneDeck.getColumnIndex(canvas));

        if(setOfCards.getCardSet().contains(currentCard)) {
            setOfCards.removeFromSetOfCards(currentCard);
            redrawCard(currentCard);
        } else {
            if(setOfCards.getCardSet().size()<3) {
                setOfCards.addCardtoSet(currentCard);
                drawBorder(canvas,Color.CORAL);
            }
            if(setOfCards.getCardSet().size()==3) {
                if(setOfCards.isSet()) {
                    clearSet();
                    changeNewSet();
                    numberOfSetsFound++;
                    lNumOfSets.setText( Integer.toString(numberOfSetsFound));
                }else {
                    clearSet();
                    setOfCards.getCardSet().clear();
                }
            }
        }
    }

    private void redrawCard(Card card) {
        GraphicsContext gc = ((Canvas) gridPaneDeck.getChildren().get(cardsDisplayed.indexOf(card))).getGraphicsContext2D();
        drawCard.setGc(gc);
        drawCard.setCard(card);
        drawCard.draw();
    }

    private void clearSet() {
        for(Card c : setOfCards.getCardSet()) {
            GraphicsContext gc = ((Canvas) gridPaneDeck.getChildren().get(cardsDisplayed.indexOf(c))).getGraphicsContext2D();
            drawCard.setGc(gc);
            drawCard.setCard(c);
            drawCard.draw();
        }
    }


    private void drawBorderForHint() {
        for(Card card: setOfCards.getCardSet()) {
            canvas = (Canvas)gridPaneDeck.getChildren().get(cardsDisplayed.indexOf(card));
            drawBorder(canvas,Color.DEEPPINK);
        }
    }

    private void drawBorder(Canvas c, Paint paint) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(10);
        gc.setStroke(paint);
        gc.strokeRoundRect(0, 0, c.getWidth(), c.getHeight(),50,50);
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
        availableSets.getCardSet().clear();
        availableSets=deck.hint(cardsDisplayed);
        return !(availableSets.getCardSet().isEmpty());
    }

    @FXML
    private void handleHint() {
        numberOfHintUsed++;
        clearSet();
        setOfCards.getCardSet().clear();

        setOfCards = deck.hint(cardsDisplayed);

        if(setOfCards.getCardSet().isEmpty()){
            lMsg.setText("No sets available!");
        }else{
            drawBorderForHint();
        }
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
        deckDao = new DeckDaoDOM("listOfDecks.xml");
        scoreDao = new ScoreDaoDOM("leaderBoard.xml");

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setMinWidth(200);
        nameCol.setSortable(true);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        TableColumn pointsCol = new TableColumn("Points");
        pointsCol.setMinWidth(200);
        pointsCol.setSortable(true);
        pointsCol.setStyle( "-fx-alignment: CENTER-RIGHT;");
        pointsCol.setCellValueFactory(
                new PropertyValueFactory<>("score"));

        table.setItems(FXCollections.observableArrayList());
        table.getColumns().addAll(nameCol, pointsCol);

        gridPaneDeck.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            setGridWidthProperties(newSceneWidth.doubleValue());
            refresh();
        });
        gridPaneDeck.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            setGridHeightProperties(newSceneHeight.doubleValue());
            refresh();
        });
    }

}
