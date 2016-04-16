package hu.progtech.setcardgame.dao;

import hu.progtech.setcardgame.bl.Card;
import hu.progtech.setcardgame.bl.Deck;
import javafx.util.Pair;
import jdk.nashorn.internal.objects.NativeArray;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
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

public class XMLHandlerDOM {
    /**
     * File to keep track of the highscore of the players.
     */
    File leaderBoard;
    /**
     * File to offer a pre-made series of shuffles to play.
     */
    File listOfDecks;

    /**
     * DocumentFactory for parsing the xml.
     */

    DocumentBuilderFactory dbFactory;

    /**
     * DocumentBuilder for parsing the xml.
     */

    DocumentBuilder dBuilder;

    /**
     * Constructs a XMLHandlerDOM and sets the files for the class to use.
     */

    public XMLHandlerDOM() {
        try {
            leaderBoard = new File("leaderBoard.xml");
            listOfDecks = new File("listOfDecks.xml");

            dbFactory = DocumentBuilderFactory.newInstance();

            dBuilder = dbFactory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes into the leaderBoard xml file the score of the latest game.
     *
     * @param score the score of the latest game.
     * @param player the player who played the latest game.
     */

    public void WriteScore(double score, String player) {
        try {
            Document doc = dBuilder.parse(leaderBoard);

            NodeList nList = doc.getElementsByTagName("player");

            Element rootElement = doc.getDocumentElement();

            if(positionOfPlayerInXML(player,doc)==-1) {
                Element playerElement = doc.createElement("player");

                Attr attr = doc.createAttribute("name");
                attr.setValue(player);
                playerElement.setAttributeNode(attr);

                rootElement.appendChild(playerElement);

                Element scoreElement = doc.createElement("score");
                scoreElement.appendChild(doc.createTextNode(String.valueOf(score)));

                playerElement.appendChild(scoreElement);

            }else {
                Element playerElement = (Element) nList.item(positionOfPlayerInXML(player,doc));
                Element scoreElement = doc.createElement("score");
                scoreElement.appendChild(doc.createTextNode( String.valueOf(score)));

                playerElement.appendChild(scoreElement);
            }

        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a list of player and score pairs, which will build the leaderboard up.
     * @return a list of player and score pairs.
     */

    public List<Pair<String,Double> > ReadHighScoreTable() {

        List<Pair<String,Double> > list = new ArrayList();

        try {
            Document doc = dBuilder.parse(leaderBoard);
            NodeList nList = doc.getElementsByTagName("player");

            for(int i = 0; i< nList.getLength();i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element playerElement = (Element) node;
                    String player = playerElement.getAttribute("name");
                    Pair<String,Double> pair;
                    NodeList scoreList = playerElement.getElementsByTagName("score");
                    for(int j = 0; j< scoreList.getLength();j++) {
                        Element score = (Element) scoreList.item(j);
                        pair = new Pair(player, score.getTextContent());
                        list.add(pair);
                    }
                }
            }


        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Returns the requested pre-shuffeled deck.
     * @param n the n-th deck in the list of pre-shuffeled decks.
     * @return the requested pre-shuffeled deck.
     */

    public Deck ReadNextDeck(int n) {

        List<Card> deck = new ArrayList<Card>();

        try {
            Document doc = dBuilder.parse(leaderBoard);
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
     * Returns the position of the player in the leaderBoard xml.
     * @param player the subject of the search.
     * @param doc the xml to search in.
     * @return the position of the player element, if not found returns -1.
     */
    public int positionOfPlayerInXML(String player, Document doc) {
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

}
