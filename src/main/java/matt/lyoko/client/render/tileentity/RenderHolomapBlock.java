/**
 * Code Lyoko Mod for Minecraft v@VERSION
 * 
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 * 
 */

package matt.lyoko.client.render.tileentity;

import matt.lyoko.client.model.tileentity.ModelHolomap;
import matt.lyoko.client.render.RenderHolomap;
import matt.lyoko.entities.tileentity.TileEntityHolomap;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHolomapBlock extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
{

    private ModelHolomap model = new ModelHolomap();
    private static final ResourceLocation texture = new ResourceLocation("lyoko", "textures/models/holomapedge.png");
    private final int renderId;

    private RenderHolomap holomapRenderer;

    public RenderHolomapBlock(int id)
    {
        this.renderId = id;
        this.holomapRenderer = new RenderHolomap();
    }

    /**
     * Renders the TileEntity for the chest at a position.
     */
    public void doRender(TileEntityHolomap entity, double x, double y, double z, float partialTick)
    {
        int i = entity.getBlockMetadata();

        // Binds the texture
        this.bindTexture(texture);
        GL11.glPushMatrix();
        {
            GL11.glTranslatef((float) x, (float) y, (float) z);
            GL11.glTranslatef(0.5F, 1.5F, 0.5F);
            // Use this or else model renders upside-down.
            GL11.glRotatef(180, 0F, 0F, 1F);

            short rotate = 0;

            if (i == 0 || i == 4)
                rotate = 0;

            if (i == 1 || i == 5)
                rotate = 270;

            if (i == 2 || i == 6)
                rotate = 180;

            if (i == 3 || i == 7)
                rotate = 90;

            GL11.glRotatef(rotate, 0F, 1F, 0F);

            this.model.render(entity, (float) x, (float) y, (float) z, 0.0F, 0.0F, 0.0625F);

            // is center block.
            if (i > 8)
                // this.func_110628_a(holomapRenderer.texture);
                this.holomapRenderer.render(entity, x, y, z, partialTick);
        }

        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity entity, double par2, double par4, double par6, float par8)
    {
        this.doRender((TileEntityHolomap) entity, par2, par4, par6, par8);
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        float scale = 0.875F;

        GL11.glPushMatrix();
        // Binds the texture
        this.bindTexture(texture);

        GL11.glRotatef(180, 0F, 1F, 0F);
        // Use this or else model renders upside-down.
        GL11.glRotatef(180, 0F, 0F, 1F);
        GL11.glTranslatef(0F, -1.2F, 0F);
        GL11.glScalef(scale, scale, scale);
        this.model.render((TileEntityHolomap) null, 0F, 0F, 0F, 0.0F, 0.0F, 0.0625F);

        GL11.glPopMatrix();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int i)
    {
        return true;
    }

    @Override
    public int getRenderId()
    {
        return this.renderId;
    }
}
