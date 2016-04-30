package hu.progtech.setcardgame.ui;

import hu.progtech.setcardgame.bl.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;


/**
 * Created by marianna on 2016.04.09..
 */
public class DrawCard {

    private Card card;

    private GraphicsContext gc;

    private double heightOfCanvas;

    private double widthOfCanvas;

    private double heightOfObject;

    private double widthOfObject;

    public DrawCard(Card card, GraphicsContext gc) {
        this.card = card;
        this.gc = gc;
        heightOfCanvas = gc.getCanvas().getHeight();
        widthOfCanvas = gc.getCanvas().getWidth();
        heightOfObject = heightOfCanvas/6;
        widthOfObject = heightOfObject;

    }

    public DrawCard(GraphicsContext gc) {
        this.gc = gc;
        heightOfCanvas = gc.getCanvas().getHeight();
        widthOfCanvas = gc.getCanvas().getWidth();
        heightOfObject = heightOfCanvas/6;
        widthOfObject = heightOfObject;

    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public void draw()
    {

        gc.clearRect(0, 0, widthOfCanvas, heightOfCanvas);
        gc.setLineWidth(5);
        gc.setStroke(Color.BLACK);
        gc.strokeRoundRect(0,0,widthOfCanvas,heightOfCanvas,10,10);
        if(card!=null) {
            initColor();
            initNumber();
        }else{
            gc.setFill(Color.DARKTURQUOISE);
            gc.fillRoundRect(0,0,widthOfCanvas,heightOfCanvas,10,10);
        }

    }

    private void initColor() {
        switch (card.getColor()) {
            case 0:
                gc.setStroke(Color.BLUE);
                gc.setFill(Color.BLUE);
                break;
            case 1:
                gc.setStroke(Color.RED);
                gc.setFill(Color.RED);
                break;
            case 2:
                gc.setStroke(Color.GREENYELLOW);
                gc.setFill(Color.GREENYELLOW);
                break;
            default: break;
        }
    }

    private void initNumber() {
        switch (card.getNumber()) {
            case 0:
                initShape(1);
                break;
            case 1:
                initShape(2);
                break;
            case 2:
                initShape(3);
                break;
            default: break;
        }
    }

    private void initShape(int n) {
            switch (card.getShape()) {
                case 0:
                    if(n==1) {
                        drawCircle((widthOfCanvas-widthOfObject)/2,(heightOfCanvas-heightOfObject)/2,widthOfObject,heightOfObject);
                    }else if(n==2) {
                        drawCircle((widthOfCanvas-widthOfObject)/2,heightOfCanvas*1/8,widthOfObject,heightOfObject);
                        drawCircle((widthOfCanvas-widthOfObject)/2,heightOfCanvas*5/8,widthOfObject,heightOfObject);
                    }else {
                        drawCircle((widthOfCanvas-widthOfObject)/2,heightOfCanvas*1/9,widthOfObject,heightOfObject);
                        drawCircle((widthOfCanvas-widthOfObject)/2,heightOfCanvas*4/9,widthOfObject,heightOfObject);
                        drawCircle((widthOfCanvas-widthOfObject)/2,heightOfCanvas*7/9,widthOfObject,heightOfObject);
                    }
                    break;
                case 1:
                    if(n==1) {
                        drawRectangle((widthOfCanvas - widthOfObject) / 2, (heightOfCanvas - heightOfObject) / 2, widthOfObject, heightOfObject);
                    }else if(n==2) {
                        drawRectangle((widthOfCanvas - widthOfObject) / 2, heightOfCanvas * 1 / 8, widthOfObject, heightOfObject);
                        drawRectangle((widthOfCanvas - widthOfObject) / 2, heightOfCanvas * 5 / 8, widthOfObject, heightOfObject);
                    }else {
                        drawRectangle((widthOfCanvas - widthOfObject) / 2, heightOfCanvas * 1 / 9, widthOfObject, heightOfObject);
                        drawRectangle((widthOfCanvas - widthOfObject) / 2, heightOfCanvas * 4 / 9, widthOfObject, heightOfObject);
                        drawRectangle((widthOfCanvas - widthOfObject) / 2, heightOfCanvas * 7 / 9, widthOfObject, heightOfObject);
                    }
                    break;
                case 2:
                    if(n==1) {
                        drawTriangle((widthOfCanvas - widthOfObject) / 2, (heightOfCanvas - heightOfObject) / 2, widthOfObject, heightOfObject);
                    }else if(n==2) {
                        drawTriangle((widthOfCanvas - widthOfObject) / 2, heightOfCanvas * 1 / 8, widthOfObject, heightOfObject);
                        drawTriangle((widthOfCanvas - widthOfObject) / 2, heightOfCanvas * 5 / 8, widthOfObject, heightOfObject);
                    }else {
                        drawTriangle((widthOfCanvas - widthOfObject) / 2, heightOfCanvas * 1 / 9, widthOfObject, heightOfObject);
                        drawTriangle((widthOfCanvas - widthOfObject) / 2, heightOfCanvas * 4 / 9, widthOfObject, heightOfObject);
                        drawTriangle((widthOfCanvas - widthOfObject) / 2, heightOfCanvas * 7 / 9, widthOfObject, heightOfObject);
                    }
                    break;
                default: break;
            }
    }

    private void drawCircle(double x, double y, double w, double h) {
        switch (card.getShading()) {
            case 0:
                gc.strokeOval(x, y, w, h);
                break;
            case 1:
                gc.strokeOval(x,y,w,h);
                gc.strokeLine(x+w/2,y,x+w/2,y+h);
                break;
            case 2:
                gc.fillOval(x, y, w, h);
                break;
            default: break;
        }
    }

    private void drawRectangle(double x, double y, double w, double h) {
        switch (card.getShading()) {
            case 0:
                gc.strokeRect(x, y, w, h);
                break;
            case 1:
                gc.strokeRect(x, y, w, h);
                gc.strokeLine(x+w/2,y,x+w/2,y+h);
                break;
            case 2:
                gc.fillRect(x, y, w, h);
                break;
            default: break;
        }
    }

    private void drawTriangle(double x, double y, double w, double h) {
        double[] xPoints = {x+w/2,x+w,x};
        double[] yPoints = {y,y+h,y+h};

        switch (card.getShading()) {
            case 0:
                gc.strokePolygon(xPoints,yPoints,3);
                break;
            case 1:
                gc.strokePolygon(xPoints,yPoints,3);
                gc.strokeLine(x+w/2,y,x+w/2,y+h);
                break;
            case 2:
                gc.fillPolygon(xPoints,yPoints,3);
                break;
            default: break;
        }
    }

}
