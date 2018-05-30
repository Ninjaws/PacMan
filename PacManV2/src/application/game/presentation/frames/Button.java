package application.game.presentation.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public abstract class Button {
    private Point2D position;
    private int width;
    private int height;
    private String context;
    private RoundRectangle2D roundRectangle2D;
    private Rectangle2D textBox;

    public Button(Point2D position, int width, int height, String context) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.context = context;
        AffineTransform tx = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(tx,true,true);
        textBox = new JLabel().getFont().getStringBounds(context, frc);
        roundRectangle2D = new RoundRectangle2D.Double(position.getX(),position.getY(),width,height,20,20);
    }

    public abstract void action();

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.getHSBColor((float)240/360,1f,0.5f));
        g2d.fill(roundRectangle2D);
        g2d.setColor(Color.getHSBColor((float)240/360,1f,0.60f));
        g2d.draw(roundRectangle2D);
        g2d.setColor(Color.white);
        g2d.drawString(context,(int)(position.getX() + width/2 - textBox.getWidth()/2) -10, (int)position.getY() + height/2 + 10);
    }

    public void update(Point2D position) {
        this.position = position;
        AffineTransform tx = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(tx,true,true);
        textBox = new JLabel().getFont().getStringBounds(context, frc);
        roundRectangle2D = new RoundRectangle2D.Double(position.getX(),position.getY(),width,height,20,20);
    }

    public RoundRectangle2D getRoundRectangle2D() {
        return roundRectangle2D;
    }
}
