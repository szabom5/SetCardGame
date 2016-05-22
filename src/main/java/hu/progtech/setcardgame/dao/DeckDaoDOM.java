package hu.progtech.setcardgame.dao;

import hu.progtech.setcardgame.bl.Card;
import hu.progtech.setcardgame.bl.Deck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handling the XML operations for the stored decks with DOM.
 */

public class DeckDaoDOM implements DeckDao {

    /**
     * {@link org.slf4j.Logger} to log about the data operations.
     */

    private static final Logger logger = LoggerFactory.getLogger(DeckDaoDOM.class);

    /**
     * {@link java.io.File} to offer a pre-made series of shuffles to play.
     */
    private File listOfDecks;

    /**
     * {@link javax.xml.parsers.DocumentBuilder} for parsing the xml.
     */

    private DocumentBuilder dBuilder;


    /**
     * Constructs a {@code DeckDaoDOM} and sets the file for the class to use.
     * @param filename The name of the file
     */

    public DeckDaoDOM(String filename) {
        try {
            listOfDecks = new File(filename);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            dBuilder = dbFactory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {
            logger.warn("Exception: "+e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck readNextDeck(int n) {

        List<Card> deck = new ArrayList<>();

        try {

            Document doc = dBuilder.parse(listOfDecks);
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
            logger.warn("Exception: "+e.getMessage());
        }

        return new Deck(deck);
    }
}


