/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.client.render;

/**
 * 
 * Animates tile entity textures.
 * 
 * @author Jake
 * 
 */
public class TileAnimator
{

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

    public TileAnimator(float parFrameWidth, float parFrameHeight, int parNumFramesX, int parNumFramesY, float parSpeed)
    {
        this.frameWidth = parFrameWidth;
        this.frameHeight = parFrameHeight;
        this.numFramesX = parNumFramesX;
        this.numFramesY = parNumFramesY;
        this.totalFrames = this.numFramesX * this.numFramesY;
        this.speed = parSpeed;
        this.baseX = 1.0F / this.numFramesX;
        this.baseY = 1.0F / this.numFramesY;
    }

    public float[] getTopLeft()
    {
        float[] value = new float[2];
        value[0] = this.xPos + this.baseX;
        value[1] = this.yPos + this.baseY;
        return value;
    }

    public float[] getTopRight()
    {
        float[] value = new float[2];
        value[0] = this.xPos;
        value[1] = this.yPos + this.baseY;
        return value;
    }

    public float[] getBottomLeft()
    {
        float[] value = new float[2];
        value[0] = this.xPos + this.baseX;
        value[1] = this.yPos;
        return value;
    }

    public float[] getBottomRight()
    {
        float[] value = new float[2];
        value[0] = this.xPos;
        value[1] = this.yPos;
        return value;
    }

    public void animate()
    {
        if (this.index >= this.totalFrames / this.speed)
        {
            this.index = 0;
            this.xPos = 0;
            this.yPos = 0;
        } else if (this.xPos + this.frameWidth > this.numFramesX)
        {
            this.xPos = 0;
            this.yPos = this.currentFrame * this.baseY * this.frameHeight;
        }

        this.currentFrame = (float) Math.ceil(this.index * this.speed);
        this.xPos = this.currentFrame * this.baseX * this.frameWidth;

        this.index++;
    }

}
