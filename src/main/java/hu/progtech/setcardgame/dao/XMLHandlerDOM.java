package hu.progtech.setcardgame.dao;

import hu.progtech.setcardgame.bl.Card;
import hu.progtech.setcardgame.bl.Deck;
import hu.progtech.setcardgame.bl.Score;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marianna on 2016.04.16..
 */

/**
 * Handling all xml operations for the game.
 */

public class XMLHandlerDOM implements  XMLHandler{

    /**
     * {@link java.io.File} to keep track of the high score of the players.
     */
    private File leaderBoard;
    /**
     * {@link java.io.File} to offer a pre-made series of shuffles to play.
     */
    private File listOfDecks;

    /**
     * {@link javax.xml.parsers.DocumentBuilderFactory} for parsing the xml.
     */

    private DocumentBuilderFactory dbFactory;

    /**
     * {@link javax.xml.parsers.DocumentBuilder} for parsing the xml.
     */

    private DocumentBuilder dBuilder;

    /**
     * {@link org.w3c.dom.Document} for parsing the xml.
     */
    private Document doc;

    /**
     * Constructs a {@code XMLHandlerDOM} and sets the files for the class to use.
     */

    public XMLHandlerDOM() {
        try {
            leaderBoard = new File("./src/main/resources/leaderBoard.xml");
            listOfDecks = new File("./src/main/resources/listOfDecks.xml");

            dbFactory = DocumentBuilderFactory.newInstance();

            dBuilder = dbFactory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void writeScore(Score score) {
        try {
            doc = dBuilder.parse(leaderBoard);

            Element rootElement = doc.getDocumentElement();

            Element scoreElement = doc.createElement("score");

            Attr attrName = doc.createAttribute("name");
            attrName.setValue(score.getName());
            scoreElement.setAttributeNode(attrName);

            Attr attrPoints = doc.createAttribute("points");
            attrPoints.setValue( String.valueOf(score.getScore()));
            scoreElement.setAttributeNode(attrPoints);

            rootElement.appendChild(scoreElement);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(leaderBoard);

            t.transform(source, result);

        } catch (SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Score> readHighScoreTable() {

        List<Score> res = new ArrayList<>();

        try {
            doc = dBuilder.parse(leaderBoard);
            NodeList nList = doc.getElementsByTagName("score");

            for(int i = 0; i< nList.getLength();i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element playerElement = (Element) node;
                    String player = playerElement.getAttribute("name");
                    double score = Double.parseDouble(playerElement.getAttribute("points"));

                    res.add(new Score(player,score));
                }
            }
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck readNextDeck(int n) {

        List<Card> deck = new ArrayList<>();

        try {
            doc = dBuilder.parse(listOfDecks);
            NodeList deckList = doc.getElementsByTagName("Deck");
            if (deckList.item(n).getNodeType() == Node.ELEMENT_NODE) {

                Element currentDeck = (Element) deckList.item(n);
                NodeList cardList = currentDeck.getElementsByTagName("Card");

                for (int i = 0; i < cardList.getLength(); i++) {
                    Node node = cardList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element cardElement = (Element) node;
                        int number = Integer.parseInt(cardElement.getAttribute("number"));
                        int shape = Integer.parseInt(cardElement.getAttribute("shape"));
                        int shading = Integer.parseInt(cardElement.getAttribute("shading"));
                        int color = Integer.parseInt(cardElement.getAttribute("color"));

                        Card c = new Card(number, shape, shading, color);
                        deck.add(c);
                    }
                }
            }


        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        return new Deck(deck);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int positionOfPlayerInXML(String player) {
        NodeList nList = doc.getElementsByTagName("player");
        for (int i = 0; i< nList.getLength();i++) {
            Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                if(element.getAttribute("name").equals(player)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writePreShuffeledDecks(List<Deck> list) {

        try {
            doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("ListOfDecks");

            for(int i = 0;i<list.size();i++) {
                Element deckElement = doc.createElement("Deck");
                deckElement.setAttribute("id",Integer.toString(i));
                for(Card card : list.get(i).getDeck()) {
                    Element cardElement = doc.createElement("Card");
                    cardElement.setAttribute("number",Integer.toString(card.getNumber()));
                    cardElement.setAttribute("shape",Integer.toString(card.getShape()));
                    cardElement.setAttribute("shading",Integer.toString(card.getShading()));
                    cardElement.setAttribute("color",Integer.toString(card.getColor()));
                    deckElement.appendChild(cardElement);
                }
                rootElement.appendChild(deckElement);
            }

            doc.appendChild(rootElement);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(listOfDecks);

            t.transform(source, result);

        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }


}
