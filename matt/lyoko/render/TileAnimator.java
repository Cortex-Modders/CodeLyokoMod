package matt.lyoko.render;

/**
 * 
 * Animates tile entity textures.
 * 
 * @author Jake
 * 
 */
// @SuppressWarnings("unused")
public class TileAnimator {

    private float frameWidth;
    private float frameHeight;

    private float speed;
    private int numFramesX;
    private int numFramesY;
    private int totalFrames;
    private float currentFrame;
    private float xPos;
    private float yPos;
    private int index;

    /** Replacement for 1 */
    private float baseX;
    /** Replacement for 1 */
    private float baseY;

    public TileAnimator(float parFrameWidth, float parFrameHeight,
            int parNumFramesX, int parNumFramesY,
            float parSpeed) {
        frameWidth = parFrameWidth;
        frameHeight = parFrameHeight;
        numFramesX = parNumFramesX;
        numFramesY = parNumFramesY;
        totalFrames = numFramesX * numFramesY;
        speed = parSpeed;
        baseX = 1.0F / numFramesX;
        baseY = 1.0F / numFramesY;
    }

    public float[] getTopLeft() {
        float[] value = new float[2];
        value[0] = xPos;
        value[1] = yPos;
        return value;
    }

    public float[] getTopRight() {
        float[] value = new float[2];
        value[0] = xPos + baseX;
        value[1] = yPos;
        return value;
    }

    public float[] getBottomLeft() {
        float[] value = new float[2];
        value[0] = xPos;
        value[1] = yPos + baseY;
        return value;
    }

    public float[] getBottomRight() {
        float[] value = new float[2];
        value[0] = xPos + baseX;
        value[1] = yPos + baseY;
        return value;
    }

    public void animate() {
        if (index >= totalFrames/speed) {
            index = 0;
            xPos = 0;
            yPos = 0;
        } else if (xPos + frameWidth > numFramesX) {
            xPos = 0;
            yPos = currentFrame * baseY * frameHeight;
        }

        currentFrame = (float) Math.ceil(index * speed);
        xPos = currentFrame * baseX * frameWidth;
        System.out.println(index);
        
        index++;
    }

}
