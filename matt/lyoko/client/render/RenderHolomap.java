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

import matt.lyoko.entities.tileentity.TileEntityHolomap;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)

/**
 *  This renders a holomap. Called from holomap block.
 */
public class RenderHolomap {

    private float centerRotation = 0;
    public static final ResourceLocation texture = new ResourceLocation("lyoko", "textures/models/lyokocore.png");

    public RenderHolomap() {

    }

    public void render(TileEntityHolomap entity, double x, double y, double z, float partialTick) {
        this.renderSectors(entity, x, y, z, partialTick);
    }

    private void renderSectors(TileEntityHolomap entity, double x, double y, double z, float partialTick) {
        GL11.glPushMatrix();
        {
            GL11.glTranslatef(-1.5F, -2.2F, -1.5F);
            AxisAlignedBB box = AxisAlignedBB.getBoundingBox(0, 0, 0, 3, 3, 3);
            RenderUtil.renderWireframe(box);

            GL11.glPushMatrix();
            {
                RenderUtil.bindTexture(texture);
                GL11.glTranslatef(1.25F, 1.25F, 1.25F);
                GL11.glRotatef(centerRotation, 1, 1, 1);
                box = AxisAlignedBB.getBoundingBox(0, 0, 0, 0.5, 0.5, 0.5);
                RenderUtil.renderBoxWithUV(box, 0, 0, 0.5, 0.5);
                RenderUtil.renderWireframe(box);
            }
            GL11.glPopMatrix();

            GL11.glPushMatrix();
            {
                GL11.glRotatef(45, 1, 1, 1);
                RenderUtil.alphaOn();
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                GL11.glLineWidth(50);
                GL11.glColor4f(0.9F, 0.9F, 1F, 0.6F);
                GL11.glBegin(GL11.GL_LINES);
                float f = 1.40F;
                float f1 = 1.60F;
                for(float i = 0; i<=3; i+=0.15F) {
                    GL11.glVertex3f(f, i, 0);
                    GL11.glVertex3f(f1, i, 0);
                }
                for(float i = 0; i<=3; i+=0.15F) {
                    GL11.glVertex3f(f, i, 3);
                    GL11.glVertex3f(f1, i, 3);
                }
                for(float i = 0; i<=3; i+=0.15F) {
                    GL11.glVertex3f(f, 0, i);
                    GL11.glVertex3f(f1, 0, i);
                }
                for(float i = 0; i<=3; i+=0.15F) {
                    GL11.glVertex3f(f, 3, i);
                    GL11.glVertex3f(f1, 3, i);
                }
                GL11.glEnd();
                GL11.glRotatef(90F, 0F, 0F, 1F);
                GL11.glBegin(GL11.GL_LINES);
                for(float i = 0; i<=3; i+=0.15F) {
                    GL11.glVertex3f(f, -i, 0);
                    GL11.glVertex3f(f1, -i, 0);
                }
                for(float i = 0; i<=3; i+=0.15F) {
                    GL11.glVertex3f(f, -i, 3);
                    GL11.glVertex3f(f1, -i, 3);
                }
                for(float i = 0; i<=3; i+=0.15F) {
                    GL11.glVertex3f(f, 0, i);
                    GL11.glVertex3f(f1, 0, i);
                }
                for(float i = 0; i<=3; i+=0.15F) {
                    GL11.glVertex3f(f, -3, i);
                    GL11.glVertex3f(f1, -3, i);
                }
                GL11.glEnd();
                
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                RenderUtil.alphaOff();
            }
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();


        centerRotation += 0.8 * partialTick;
    }

    private void renderCarthage(TileEntityHolomap entity, double x, double y, double z, float partialTick) {
        GL11.glPushMatrix();
        {

        }
        GL11.glPopMatrix();
    }

    private void renderNetwork(TileEntityHolomap entity, double x, double y, double z, float partialTick) {
        GL11.glPushMatrix();
        {

        }
        GL11.glPopMatrix();
    }

}

