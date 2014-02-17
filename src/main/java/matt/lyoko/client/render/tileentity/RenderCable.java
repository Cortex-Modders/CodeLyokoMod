/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.client.render.tileentity;

import matt.lyoko.blocks.BlockCable;
import matt.lyoko.blocks.BlockHolomap;
import matt.lyoko.blocks.BlockScanner;
import matt.lyoko.blocks.BlockSuperCalc;
import matt.lyoko.blocks.BlockSuperCalcConsole;
import matt.lyoko.client.model.tileentity.ModelCable;
import matt.lyoko.entities.tileentity.TileEntityCable;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class RenderCable extends TileEntitySpecialRenderer
{
    // Model file
    private ModelCable model;
    private static final ResourceLocation texture = new ResourceLocation("lyoko", "textures/models/cable.png");
    
    private boolean top = false;
    private boolean bottom = false;
    private boolean left = false;
    private boolean right = false;
    private boolean front = false;
    private boolean back = false;
    
    public RenderCable()
    {
        // initialization of Model File
        this.model = new ModelCable();
    }
    
    // your TileEntity
    public void renderAModelAt(TileEntityCable tile, double x, double y, double z, float delta)
    {
        if (tile.getWorldObj() != null)
            tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);
        
        // directory of the model's texture file
        this.bindTexture(texture);
        
        GL11.glPushMatrix();
        {
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
            GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(1.0F, -1F, -1F);
            this.configureSides(tile);
            this.model.renderModel(0.0625F, this.top, this.bottom, this.left, this.right, this.front, this.back);
        }
        GL11.glPopMatrix();
    }
    
    public void configureSides(TileEntityCable tile)
    {
        int x = tile.xCoord;
        int y = tile.yCoord;
        int z = tile.zCoord;
        this.top = this.configSide(tile.getWorldObj(), x, y + 1, z, 1);
        this.bottom = this.configSide(tile.getWorldObj(), x, y - 1, z, 0);
        this.left = this.configSide(tile.getWorldObj(), x + 1, y, z, 3);
        this.right = this.configSide(tile.getWorldObj(), x - 1, y, z, 2);
        this.front = this.configSide(tile.getWorldObj(), x, y, z + 1, 5);
        this.back = this.configSide(tile.getWorldObj(), x, y, z - 1, 4);
    }
    
    private boolean configSide(World world, int x, int y, int z, int side)
    {
        if (world.getBlock(x, y, z) instanceof BlockCable || world.getBlock(x, y, z) instanceof BlockSuperCalcConsole)
            return true;
        else if (world.getBlock(x, y, z) instanceof BlockSuperCalc && (side == 1 || side == 0))
            return true;
        else if (world.getBlock(x, y, z) instanceof BlockHolomap && side != 0)
            return true;
        else if (world.getBlock(x, y, z) instanceof BlockScanner && (side == 0 || side == 1))
            return true;
        return false;
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
        // your TileEntity
        this.renderAModelAt((TileEntityCable) tileentity, d, d1, d2, f);
    }
}
