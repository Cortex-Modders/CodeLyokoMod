/**
 * Code Lyoko Mod for Minecraft v@VERSION
 *
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 * 
 */

package matt.lyoko.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
/**
 * Some useful methods for rendering.
 * 
 * @author Jadar
 */
public class RenderUtil {

    public static void renderWireframe(AxisAlignedBB box) {
        //render wireframe
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);
        GL11.glLineWidth(2.0F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(false);
        
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawing(3);
        tessellator.addVertex(box.minX, box.minY, box.minZ);
        tessellator.addVertex(box.maxX, box.minY, box.minZ);
        tessellator.addVertex(box.maxX, box.minY, box.maxZ);
        tessellator.addVertex(box.minX, box.minY, box.maxZ);
        tessellator.addVertex(box.minX, box.minY, box.minZ);
        tessellator.draw();
        tessellator.startDrawing(3);
        tessellator.addVertex(box.minX, box.maxY, box.minZ);
        tessellator.addVertex(box.maxX, box.maxY, box.minZ);
        tessellator.addVertex(box.maxX, box.maxY, box.maxZ);
        tessellator.addVertex(box.minX, box.maxY, box.maxZ);
        tessellator.addVertex(box.minX, box.maxY, box.minZ);
        tessellator.draw();
        tessellator.startDrawing(1);
        tessellator.addVertex(box.minX, box.minY, box.minZ);
        tessellator.addVertex(box.minX, box.maxY, box.minZ);
        tessellator.addVertex(box.maxX, box.minY, box.minZ);
        tessellator.addVertex(box.maxX, box.maxY, box.minZ);
        tessellator.addVertex(box.maxX, box.minY, box.maxZ);
        tessellator.addVertex(box.maxX, box.maxY, box.maxZ);
        tessellator.addVertex(box.minX, box.minY, box.maxZ);
        tessellator.addVertex(box.minX, box.maxY, box.maxZ);
        tessellator.draw();
        
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }
    
    public static void renderBox(AxisAlignedBB box) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
 
        //bottom
        tessellator.addVertex(box.minX, box.maxY, box.maxZ);
        tessellator.addVertex(box.maxX, box.maxY, box.maxZ);
        tessellator.addVertex(box.maxX, box.maxY, box.minZ);
        tessellator.addVertex(box.minX, box.maxY, box.minZ);
        //top
        tessellator.addVertex(box.minX, box.minY, box.minZ);
        tessellator.addVertex(box.maxX, box.minY, box.minZ);
        tessellator.addVertex(box.maxX, box.minY, box.maxZ);
        tessellator.addVertex(box.minX, box.minY, box.maxZ);
        //front
        tessellator.addVertex(box.minX, box.maxY, box.minZ);
        tessellator.addVertex(box.maxX, box.maxY, box.minZ);
        tessellator.addVertex(box.maxX, box.minY, box.minZ);
        tessellator.addVertex(box.minX, box.minY, box.minZ);
        //back
        tessellator.addVertex(box.minX, box.minY, box.maxZ);
        tessellator.addVertex(box.maxX, box.minY, box.maxZ);
        tessellator.addVertex(box.maxX, box.maxY, box.maxZ);
        tessellator.addVertex(box.minX, box.maxY, box.maxZ);
        //left
        tessellator.addVertex(box.minX, box.minY, box.minZ);
        tessellator.addVertex(box.minX, box.minY, box.maxZ);
        tessellator.addVertex(box.minX, box.maxY, box.maxZ);
        tessellator.addVertex(box.minX, box.maxY, box.minZ);
        //right
        tessellator.addVertex(box.maxX, box.maxY, box.minZ);
        tessellator.addVertex(box.maxX, box.maxY, box.maxZ);
        tessellator.addVertex(box.maxX, box.minY, box.maxZ);
        tessellator.addVertex(box.maxX, box.minY, box.minZ);
        
        tessellator.draw();
    }
    
    /**
     * Renders a box with the same texture on each side.
     * 
     * @param box
     * @param minU
     * @param minV
     * @param maxU
     * @param maxV
     */
    public static void renderBoxWithUV(AxisAlignedBB box, double minU, double minV, double maxU, double maxV) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        //bottom
        tessellator.addVertexWithUV(box.minX, box.maxY, box.maxZ, minU, minV);
        tessellator.addVertexWithUV(box.maxX, box.maxY, box.maxZ, maxU, minV);
        tessellator.addVertexWithUV(box.maxX, box.maxY, box.minZ, maxU, maxV);
        tessellator.addVertexWithUV(box.minX, box.maxY, box.minZ, minU, maxV);
        //top
        tessellator.addVertexWithUV(box.minX, box.minY, box.minZ, minU, maxV);
        tessellator.addVertexWithUV(box.maxX, box.minY, box.minZ, maxU, maxV);
        tessellator.addVertexWithUV(box.maxX, box.minY, box.maxZ, maxU, minV);
        tessellator.addVertexWithUV(box.minX, box.minY, box.maxZ, minU, minV);
        //front
        tessellator.addVertexWithUV(box.minX, box.maxY, box.minZ, minU, maxV);
        tessellator.addVertexWithUV(box.maxX, box.maxY, box.minZ, maxU, maxV);
        tessellator.addVertexWithUV(box.maxX, box.minY, box.minZ, maxU, minV);
        tessellator.addVertexWithUV(box.minX, box.minY, box.minZ, minU, minV);
        //back
        tessellator.addVertexWithUV(box.minX, box.minY, box.maxZ, minU, minV);
        tessellator.addVertexWithUV(box.maxX, box.minY, box.maxZ, maxU, minV);
        tessellator.addVertexWithUV(box.maxX, box.maxY, box.maxZ, maxU, maxV);
        tessellator.addVertexWithUV(box.minX, box.maxY, box.maxZ, minU, maxV);
        //left
        tessellator.addVertexWithUV(box.minX, box.minY, box.minZ, minU, minV);
        tessellator.addVertexWithUV(box.minX, box.minY, box.maxZ, maxU, minV);
        tessellator.addVertexWithUV(box.minX, box.maxY, box.maxZ, maxU, maxV);
        tessellator.addVertexWithUV(box.minX, box.maxY, box.minZ, minU, maxV);
        //right
        tessellator.addVertexWithUV(box.maxX, box.maxY, box.minZ, minU, maxV);
        tessellator.addVertexWithUV(box.maxX, box.maxY, box.maxZ, maxU, maxV);
        tessellator.addVertexWithUV(box.maxX, box.minY, box.maxZ, maxU, minV);
        tessellator.addVertexWithUV(box.maxX, box.minY, box.minZ, minU, minV);
        
        tessellator.draw();
    }
    
    /**
     * Draws a rectangle that can be seen on both sides.
     */
    public static void drawRect(float minX, float minY, float maxX, float maxY, float minZ, float maxZ) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        //bottom
        tessellator.addVertex(maxX, maxY, minZ);
        tessellator.addVertex(minX, maxY, minZ);
        tessellator.addVertex(minX, minY, maxZ);
        tessellator.addVertex(maxX, minY, maxZ);
        //top
        tessellator.addVertex(maxX, maxY, minZ);
        tessellator.addVertex(maxX, minY, maxZ);
        tessellator.addVertex(minX, minY, maxZ);
        tessellator.addVertex(minX, maxY, minZ);
        
        tessellator.draw();
    }
    /**
     * Binds a texture. Used because I hate obfuscated method names.
     * 
     * @param texture
     */
    public static void bindTexture(ResourceLocation texture) {
        TextureManager texturemanager = Minecraft.getMinecraft().renderEngine;

        if (texturemanager != null)
        {
            texturemanager.func_110577_a(texture);
        }
    }
    
    /**
     * 
     * called for things with alpha. thank you MachineMuse. :D
     */
    public static void alphaOn() {
        GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT);
        GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
        if(Minecraft.isFancyGraphicsEnabled()) {
            GL11.glShadeModel(GL11.GL_SMOOTH);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        }
    }
    
    public static void alphaOff() {
        GL11.glPopAttrib();
        GL11.glPopAttrib();
    }
    
    //functions
    public static Vector2f textureToGLCoordinates(int textureWidth, int textureHeight) {
        return new Vector2f((1F / (float)textureWidth), (1F / (float)textureHeight));
    }

    public static float textureToGLCoordinates(int textureSize) {
        return textureToGLCoordinates(textureSize, textureSize).x;
    }
    
    public static float toGLCoordinate(float textureSize, float coord) {
        return textureSize * coord;
    }
    
    public static float toGLCoordinate1(float textureSize, float coord) {
        return 1 / textureSize * coord;
    }
}
