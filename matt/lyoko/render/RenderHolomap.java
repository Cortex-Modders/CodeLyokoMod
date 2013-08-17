/**
 * Code Lyoko Mod for Minecraft v@VERSION
 *
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 * 
 */

package matt.lyoko.render;

import net.minecraft.util.AxisAlignedBB;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)

/**
 *  This renders a holomap. Called from holomap block.
 */
public class RenderHolomap {

    
    
    public RenderHolomap() {
        
    }
    
    public void render(double x, double y, double z, float partialTick) {
        GL11.glPushMatrix();
        {
            GL11.glTranslatef(-1.5F, -2.2F, -1.5F);
            AxisAlignedBB box = AxisAlignedBB.getBoundingBox(0, 0, 0, 3, 3, 3);
            RenderUtil.renderWireframe(box);
            
            // render center box
            box = AxisAlignedBB.getBoundingBox(0, 0, 0, 1.5, 1.5, 1.5);
            RenderUtil.renderBox(box);
            
        }
        GL11.glPopMatrix();
    }
    
    
    
    
    
}

