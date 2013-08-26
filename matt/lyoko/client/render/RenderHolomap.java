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
        GL11.glPushMatrix();
        {
            GL11.glTranslatef(-1.5F, -2.2F, -1.5F);
            AxisAlignedBB box = AxisAlignedBB.getBoundingBox(0, 0, 0, 3, 3, 3);
            RenderUtil.renderWireframe(box);
            
            RenderUtil.bindTexture(texture);
            GL11.glTranslatef(1.25F, 1.25F, 1.25F);
            box = AxisAlignedBB.getBoundingBox(0, 0, 0, 0.5, 0.5, 0.5);
            RenderUtil.renderBoxWithUV(box, 0, 0, 0.5, 0.5);
            RenderUtil.renderWireframe(box);
        }
        GL11.glPopMatrix();
        
        
        centerRotation += 0.8 * partialTick;
    }
    
    
    
    
    
}

