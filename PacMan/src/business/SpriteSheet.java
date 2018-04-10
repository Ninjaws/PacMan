package business;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage spriteSheet;
    private BufferedImage currentImage;

    private int currentRow;
    private int currentCol;

    private int spriteWidth;
    private int spriteHeight;

    private int delayMillis;
    private long startTime;
    private long endTime;
    private long deltaTime;

    public SpriteSheet(BufferedImage spriteSheet, int spriteWidth, int spriteHeight, int delayMillis) {

        this.spriteSheet = spriteSheet;

        this.currentRow = 0;
        this.currentCol = 0;

        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;

        this.delayMillis = delayMillis;

        deltaTime = 0;
        startTime = System.currentTimeMillis();
    }

    public void update() {
        endTime = System.currentTimeMillis();
        deltaTime += (endTime - startTime);
        startTime = System.currentTimeMillis();

        if (deltaTime < delayMillis)
            return;

        deltaTime = 0;

        currentCol = (currentCol + 1) % (spriteSheet.getWidth() / spriteWidth);
        currentImage = spriteSheet.getSubimage(currentCol * spriteWidth, currentRow * spriteHeight, spriteWidth, spriteHeight);

    }

    public void setCurrentAnimation(Animation currentAnimation) {
        //Depends on the particular spritesheet, not yet modular
        switch (currentAnimation) {
            case MOVE_UP:
                currentRow = 2;
                break;
            case MOVE_LEFT:
                currentRow = 1;
                break;
            case MOVE_DOWN:
                currentRow = 3;
                break;
            case MOVE_RIGHT:
                currentRow = 0;
                break;
        }

        //To prevent a nullpointer when it hasn't yet moved and it wants to draw an image
        currentImage = spriteSheet.getSubimage(currentCol * spriteWidth, currentRow * spriteHeight, spriteWidth, spriteHeight);
    }

    public BufferedImage getCurrentImage() {
        return currentImage;
    }

    /**
     * The manual way of setting the current animation
     * @param currentRow The row (which shows an animation)
     */
    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }


    public enum Animation {
        NONE, MOVE_UP, MOVE_LEFT, MOVE_DOWN, MOVE_RIGHT
    }
}
