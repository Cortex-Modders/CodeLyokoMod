/*
 * Code Lyoko Mod for Minecraft v@VERSION
 *
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 * 
 */

package matt.lyoko.client.render.tileentity;

import matt.lyoko.CodeLyoko;
import matt.lyoko.blocks.ModBlocks;
import matt.lyoko.client.model.tileentity.ModelCable;
import matt.lyoko.entities.tileentity.TileEntityCable;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class RenderCable extends TileEntitySpecialRenderer
{
	//Model file
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
		//initialization of Model File
		model = new ModelCable();
	}
								//your TileEntity
	public void renderAModelAt(TileEntityCable tile, double d, double d1, double d2, float f)
	{
		int i = 0;
		
		if(tile.worldObj != null) 
		{
			i = (tile.worldObj.getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord)); 
		}
		
		//directory of the model's texture file
		this.func_110628_a(texture);
		
		GL11.glPushMatrix(); 
		GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
		GL11.glRotatef(0, 0.0F, 1.0F, 0.0F); 
		GL11.glScalef(1.0F, -1F, -1F);
		configureSides(tile);
		model.renderModel(0.0625F, top, bottom, left, right, front, back); 
		GL11.glPopMatrix();
	}
	
	public void configureSides(TileEntityCable tile)
	{
		int x = tile.xCoord;
		int y = tile.yCoord;
		int z = tile.zCoord;
		top = configSide(tile.worldObj, x, y + 1, z);
		bottom = configSide(tile.worldObj, x, y - 1, z);
		left = configSide(tile.worldObj, x + 1, y, z);
		right = configSide(tile.worldObj, x - 1, y, z);
		front = configSide(tile.worldObj, x, y, z + 1);
		back = configSide(tile.worldObj, x, y, z - 1);
	}
	
	private boolean configSide(World world, int x, int y, int z)
	{
		if(world.getBlockId(x, y, z) == ModBlocks.Cable.blockID || world.getBlockId(x, y, z) == ModBlocks.SuperCalc.blockID
				|| world.getBlockId(x, y, z) == ModBlocks.Scanner.blockID
				|| world.getBlockId(x, y, z) == ModBlocks.SuperCalcConsole.blockID
				|| world.getBlockId(x, y, z) == ModBlocks.Holomap.blockID)
		{
			return true;
		}
		return false;
	}
	
	public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
	{
						//your TileEntity
		renderAModelAt((TileEntityCable) tileentity, d, d1, d2, f);
	}
}