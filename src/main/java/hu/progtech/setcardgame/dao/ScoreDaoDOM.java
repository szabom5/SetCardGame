package hu.progtech.setcardgame.dao;

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
 * Handling the XML operations for the leader board with DOM.
 */

public class ScoreDaoDOM implements ScoreDao {

    /**
     * {@link java.io.File} to keep track of the high score of the players.
     */
    private File leaderBoard;

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
     * Constructs a {@code ScoreDaoDOM} and sets the file for the class to use.
     * @param filename The name of the file
     */

    public ScoreDaoDOM(String filename) {
        try {
            leaderBoard = new File(filename);

            dbFactory = DocumentBuilderFactory.newInstance();

            dBuilder = dbFactory.newDocumentBuilder();

            createOrReplace();

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

    private void createOrReplace(){
        if(leaderBoard.exists()){
            try {
                doc = dBuilder.parse(leaderBoard);
                if(doc.getDocumentElement().getTagName().equals("leaderBoard")){
                    return;
                }else{
                    Element rootElement = doc.createElement("leaderBoard");
                    doc.appendChild(rootElement);

                    TransformerFactory tf = TransformerFactory.newInstance();
                    Transformer t = tf.newTransformer();

                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(leaderBoard);

                    t.transform(source, result);
                }
            } catch (SAXException | IOException | TransformerException e) {
                e.printStackTrace();
            }
        }else{
            try {
                doc = dBuilder.newDocument();

                Element rootElement = doc.createElement("leaderBoard");

                doc.appendChild(rootElement);

                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer t = tf.newTransformer();

                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(leaderBoard);

                t.transform(source, result);

            } catch (TransformerException e) {
                e.printStackTrace();
            }
        }
    }

}
