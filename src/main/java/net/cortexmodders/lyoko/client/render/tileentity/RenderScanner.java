/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.client.render.tileentity;

import net.cortexmodders.lyoko.blocks.BlockScanner;
import net.cortexmodders.lyoko.client.model.tileentity.ModelScanner;
import net.cortexmodders.lyoko.client.model.tileentity.ModelScannerMiddle;
import net.cortexmodders.lyoko.entities.tileentity.TileEntityScanner;
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
public class RenderScanner extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
{

    private ModelScanner model = new ModelScanner();
    
    private ModelScannerMiddle modelMid = new ModelScannerMiddle();
    private static final ResourceLocation texture = new ResourceLocation("lyoko", "textures/models/scanner.png");
    private final int renderId;

    public RenderScanner(int id)
    {
        this.renderId = id;
    }

    /**
     * Renders the TileEntity for the chest at a position.
     */
    public void doRender(TileEntityScanner entity, double x, double y, double z, float tick)
    {

        int i = entity.getBlockMetadata();

        // Binds the texture
        this.bindTexture(texture);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, (float) z);
        GL11.glTranslatef(0.5F, 0F, 0.5F);
        // Use this or else model renders upside-down.
        GL11.glRotatef(180, 0F, 0F, 1F);

        short rotate = 0;

        if (i == 0)
            rotate = 0;

        if (i == 1)
            rotate = 90;

        if (i == 2)
            rotate = 180;

        if (i == 3)
            rotate = -90;

        GL11.glRotatef(rotate, 0F, 1F, 0F);

        // placement in multi block
        int p = BlockScanner.getPositionInMultiBlock(entity.getWorldObj(), entity.xCoord, entity.yCoord, entity.zCoord);
        /*
        // if top
        if (p == 4)
        {
            GL11.glRotatef(180F, 1F, 0F, 0F);
            GL11.glRotatef(180F, 0F, 1F, 0F);
            GL11.glTranslatef(0F, 1F, 0F);
        }
        
        // if in middle or not at all
        if (p >= 1 & p <= 3 || p == -1)
        {

            this.modelMid.doorL.rotateAngleY = (float) Math.toRadians(entity.doorRotationYaw);
            this.modelMid.doorL.rotationPointX = entity.doorPosX;
            this.modelMid.doorL.rotationPointZ = entity.doorPosZ;

            this.modelMid.doorR.rotateAngleY = -(float) Math.toRadians(entity.doorRotationYaw + 180);
            this.modelMid.doorR.rotationPointX = -entity.doorPosX;
            this.modelMid.doorR.rotationPointZ = entity.doorPosZ;

            this.modelMid.render(null, (float) x, (float) y, (float) z, 0.0F, 0.0F, 0.0625F);
        }
        */
        
        /*
        // if bottom or top
        if (p == 0 || p == 4)
        {*/

            this.model.doorL.rotateAngleY = (float) Math.toRadians(entity.doorRotationYaw);
            this.model.doorL.rotationPointX = entity.doorPosX;
            this.model.doorL.rotationPointZ = entity.doorPosZ;

            this.model.doorR.rotateAngleY = -(float) Math.toRadians(entity.doorRotationYaw + 180);
            this.model.doorR.rotationPointX = -entity.doorPosX;
            this.model.doorR.rotationPointZ = entity.doorPosZ;

            this.model.render(null, (float) x, (float) y, (float) z, p, 0.0F, 0.0625F);
        

        GL11.glPopMatrix();
    }

    @Override
    //renderTileEntityAt
    public void renderTileEntityAt(TileEntity entity, double par2, double par4, double par6, float par8)
    {
        this.doRender((TileEntityScanner) entity, par2, par4, par6, par8);
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        float scale = 0.875F;

        GL11.glPushMatrix();
        // Binds the texture
        this.bindTexture(texture);

        GL11.glRotatef(180, 0F, 1F, 0F);
        GL11.glTranslatef(0F, 0.5F, 0F);
        GL11.glScalef(scale, scale, scale);
        this.modelMid.render(null, 0F, 0F, 0F, 0.0F, 0.0F, 0.0625F);

        GL11.glPopMatrix();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId)
    {
        return true;
    }

    @Override
    public int getRenderId()
    {
        return this.renderId;
    }
}
