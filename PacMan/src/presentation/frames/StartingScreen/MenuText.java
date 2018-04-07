package presentation.frames.StartingScreen;

import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class MenuText {
    private String text;
    private Font standardFont;
    private Point2D position;
    private Rectangle2D bounds;
    private Rectangle2D textBounds;
    private int screenHeightMultiplier;

    public MenuText(String text, Font standardFont, int screenHeightMultiplier, Point2D fakePosition) {
        this.text = text;
        this.standardFont = standardFont;
        this.screenHeightMultiplier = screenHeightMultiplier;
        this.position = fakePosition;
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        textBounds = standardFont.getStringBounds(text, frc);
    }

    public void draw(Graphics2D g2d){
        g2d.setFont(standardFont);
        g2d.setColor(java.awt.Color.yellow);
        g2d.drawString(text,(int)position.getX(),(int)position.getY());
    }

    public void debugDraw(Graphics2D g2d){
        g2d.setFont(standardFont);
        g2d.setColor(java.awt.Color.yellow);
        g2d.drawString(text,(int)position.getX(),(int)position.getY());
        g2d.draw(bounds);
    }

    public void update(int width, int height, int screenHeightDivider) {
        Point2D point2D = new Point2D.Double(width/2 -  textBounds.getWidth()/2,height/screenHeightDivider * screenHeightMultiplier);
        position = point2D;
        bounds = new Rectangle2D.Double(position.getX(), position.getY() - textBounds.getHeight()/2, textBounds.getWidth(), textBounds.getHeight()/2);
    }

    public Rectangle2D getBounds() {
        return bounds;
    }
}
