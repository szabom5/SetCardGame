package hu.progtech.setcardgame.dao;

import hu.progtech.setcardgame.bl.Card;
import hu.progtech.setcardgame.bl.Deck;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by marianna on 2016.04.16..
 */

/**
 * Handling all xml operations for the game.
 */

public class XMLHandlerDOM implements  XMLHandler{

    /**
     * File to keep track of the highscore of the players.
     */
    private File leaderBoard;
    /**
     * File to offer a pre-made series of shuffles to play.
     */
    private File listOfDecks;

    /**
     * DocumentFactory for parsing the xml.
     */

    private DocumentBuilderFactory dbFactory;

    /**
     * DocumentBuilder for parsing the xml.
     */

    private DocumentBuilder dBuilder;

    /**
     * Document for parsing the xml.
     */
    private Document doc;

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
    @Override
    public void writeScore(double score, String player) {
        try {
            doc = dBuilder.parse(leaderBoard);

            NodeList nList = doc.getElementsByTagName("player");

            Element rootElement = doc.getDocumentElement();

            if(positionOfPlayerInXML(player)==-1) {
                Element playerElement = doc.createElement("player");

                Attr attr = doc.createAttribute("name");
                attr.setValue(player);
                playerElement.setAttributeNode(attr);

                rootElement.appendChild(playerElement);

                Element scoreElement = doc.createElement("score");
                scoreElement.appendChild(doc.createTextNode(String.valueOf(score)));

                playerElement.appendChild(scoreElement);

            }else {
                Element playerElement = (Element) nList.item(positionOfPlayerInXML(player));
                Element scoreElement = doc.createElement("score");
                scoreElement.appendChild(doc.createTextNode( String.valueOf(score)));

                playerElement.appendChild(scoreElement);

                rootElement.appendChild(playerElement);
            }

        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a list of player and score pairs, which will build the leaderboard up.
     * @return a list of player and score pairs.
     */
    @Override
    public Map<String,List<Double> > readHighScoreTable() {

        Map<String,List<Double> > map = new HashMap<>();

        try {
            doc = dBuilder.parse(leaderBoard);
            NodeList nList = doc.getElementsByTagName("player");

            for(int i = 0; i< nList.getLength();i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element playerElement = (Element) node;
                    String player = playerElement.getAttribute("name");
                    List<Double> list = new ArrayList();
                    NodeList scoreList = playerElement.getElementsByTagName("score");
                    for(int j = 0; j< scoreList.getLength();j++) {
                        Element score = (Element) scoreList.item(j);
                        list.add(Double.parseDouble(score.getTextContent()));
                    }
                    map.put(player,list);
                }
            }


        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     * Returns the requested pre-shuffeled deck.
     * @param n the n-th deck in the list of pre-shuffeled decks.
     * @return the requested pre-shuffeled deck.
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
     * Returns the position of the player in the sorted leaderBoard xml.
     *
     * @param player the subject of the search
     * @return the position of the player element, if not found returns -1.
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
     * Writes n pre-shuffeled Decks into the listOfDecks.xml file.
     *
     * @param list the list of decks to be written into the xml.
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
