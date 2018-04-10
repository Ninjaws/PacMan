package business;

import java.awt.image.BufferedImage;
import java.util.Map;

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

    // A map with animations and the row that contains them.
    private Map<Animation, Integer> animations;

    public SpriteSheet(BufferedImage spriteSheet, Map<Animation, Integer> animations, int spriteWidth, int spriteHeight, int delayMillis) {

        this.spriteSheet = spriteSheet;
        this.animations = animations;

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

        if (!animations.containsKey(currentAnimation))
            return;

        currentRow = animations.get(currentAnimation);

        //To prevent a nullpointer when it hasn't yet moved and it wants to draw an image
        currentImage = spriteSheet.getSubimage(currentCol * spriteWidth, currentRow * spriteHeight, spriteWidth, spriteHeight);
    }

    public BufferedImage getCurrentImage() {
        return currentImage;
    }

    public enum Animation {
        NONE, MOVE_UP, MOVE_LEFT, MOVE_DOWN, MOVE_RIGHT
    }
}
