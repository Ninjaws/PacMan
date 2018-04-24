package presentation.frames.startingscreen;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class MenuText {
    /**
     * The text that is written on the panel.
     */
    private String text;

    /**
     * The standardFont is the standardPacManFont that will be drawn on the screen.
     */
    private Font standardFont;

    /**
     * The position attribute is the position of the menutext on the panel.
     */
    private Point2D position;

    /**
     * The bounds attribute represents the bounds of a menutext so it can be clickable.
     */
    private Rectangle2D bounds;

    /**
     * The textBounds is the bounds of the font. This regulates so the menutext can be set in the middle of the screen.
     */
    private Rectangle2D textBounds;

    /**
     *  The screenHeightMultiplier regulates the height of the menutext.
     */
    private int screenHeightMultiplier;

    /**
     * The MenuText constructor regulates the menutext which will be placed on the panel.
     * @param text is the text that will be shown.
     * @param standardFont is the standard pacman font.
     * @param screenHeightMultiplier is the place on the screen.
     */
    public MenuText(String text, Font standardFont, int screenHeightMultiplier) {
        this.text = text;
        this.standardFont = standardFont;
        this.screenHeightMultiplier = screenHeightMultiplier;
        this.position = new Point2D.Double(0,0);
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        textBounds = standardFont.getStringBounds(text, frc);
    }

    /**
     * draws a MenuText on screen.
     * @param g2d is gotten from the paintcomponent which draws on the panel.
     */
    public void draw(Graphics2D g2d){
        g2d.setFont(standardFont);
        g2d.setColor(java.awt.Color.yellow);
        g2d.drawString(text,(int)position.getX(),(int)position.getY());
    }

    /**
     * Debug draws a MenuText, bounds are now on the panel.
     * @param g2d is gotten from the paintcomponent which draws on the panel.
     */
    public void debugDraw(Graphics2D g2d){
        g2d.setFont(standardFont);
        g2d.setColor(java.awt.Color.yellow);
        g2d.drawString(text,(int)position.getX(),(int)position.getY());
        g2d.draw(bounds);
    }

    /**
     *
     * @param width is the screen width so the MenuText can be placed in the middle.
     * @param height is the screen heigt so the MenuText can be placed in the right place.
     * @param screenHeightDivider is needed for calculation for the height.
     */
    public void update(int width, int height, int screenHeightDivider) {
        Point2D point2D = new Point2D.Double(width/2 -  textBounds.getWidth()/2,height/screenHeightDivider * screenHeightMultiplier);
        position = point2D;
        bounds = new Rectangle2D.Double(position.getX(), position.getY() - textBounds.getHeight()/2, textBounds.getWidth(), textBounds.getHeight()/2);
    }

    /**
     * gets the bounds of a MenuText
     * @return the bounds of a MenuText.
     */
    public Rectangle2D getBounds() {
        return bounds;
    }

    /**
     * gets the text of a MenuText
     * @return the text which is displayed on the screen.
     */
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
