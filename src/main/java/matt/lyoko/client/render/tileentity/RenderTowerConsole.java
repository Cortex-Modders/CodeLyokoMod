/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.client.render.tileentity;

import matt.lyoko.client.model.tileentity.ModelConsole;
import matt.lyoko.client.render.RenderUtil;
import matt.lyoko.entities.tileentity.TileEntityTowerConsole;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTowerConsole extends TileEntitySpecialRenderer
{

    // private ClientProxy proxy;
    private ModelConsole model = new ModelConsole();
    private static final ResourceLocation texture = new ResourceLocation("lyoko", "textures/models/ModelConsole.png");

    /**
     * Renders the TileEntity for the chest at a position.
     */
    public void render(TileEntityTowerConsole entity, double x, double y, double z, float scale)
    {

        this.bindTexture(texture);

        GL11.glPushMatrix();
        {
            RenderUtil.alphaOn();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
            GL11.glTranslatef((float) x, (float) y, (float) z);
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);

            short rotate = 0;
            int metadata = entity.getBlockMetadata();
            switch (metadata)
            {
                case 0:
                    rotate = 180;
                    break;
                case 1:
                    rotate = 90;
                    break;
                case 2:
                    rotate = 0;
                    break;
                case 3:
                    rotate = -90;
                    break;
            }

            GL11.glRotatef(rotate, 0F, 1F, 0F);

            this.model.render(entity, (float) x, (float) y, (float) z, 0.0F, 0.0F, 0.0625F);

            RenderUtil.alphaOff();
        }
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.render((TileEntityTowerConsole) par1TileEntity, par2, par4, par6, par8);
    }
}
