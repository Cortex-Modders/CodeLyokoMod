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

import matt.lyoko.client.model.tileentity.ModelSuperCalcConsole;
import matt.lyoko.entities.tileentity.TileEntitySuperCalcConsole;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSuperCalcConsole extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
{
	private ModelSuperCalcConsole model = new ModelSuperCalcConsole();
	private static final ResourceLocation texture = new ResourceLocation("lyoko", "textures/models/supercalcconsole.png");
	private final int renderId;

	public RenderSuperCalcConsole(int id)
	{
		renderId = id;
	}

	/**
	 * Renders the TileEntity for the chest at a position.
	 */
	public void doRender(TileEntitySuperCalcConsole entity, double x, double y, double z, float tick)
	{

		//Binds the texture
		this.func_110628_a(texture);
		
		GL11.glPushMatrix();
		{
			GL11.glTranslatef((float) x, (float) y, (float) z);
			GL11.glTranslatef(0.5F, 0F, 0.5F);
			
			// Use this or else model renders upside-down.
			GL11.glRotatef(180, 0F, 0F, 1F);

			short rotate = 0;
			int i = entity.getBlockMetadata();
			switch(i) {
			case 0: rotate = 0; break;
			case 1: rotate = 90; break;
			case 2: rotate = 180; break;
			case 3: rotate = -90; break;
			}

			GL11.glRotatef(rotate, 0F, 1F, 0F);
			
			model.render(entity, 0F, 0F, 0F, 0.0F, 0.0F, 0.0625F);
		}
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity entity, double par2, double par4, double par6, float par8) {
		if(entity instanceof TileEntitySuperCalcConsole)
			this.doRender((TileEntitySuperCalcConsole) entity, par2, par4, par6, par8);
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		float scale = 0.875F;

		GL11.glPushMatrix();
		//Binds the texture
		this.func_110628_a(texture);

		GL11.glRotatef(180, 0F, 1F, 0F);
		GL11.glTranslatef(0F, 0.5F, 0F);
		GL11.glScalef(scale, scale, scale);
		model.render((TileEntity)null, 0F, 0F, 0F, 0.0F, 0.0F, 0.0625F);

		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return renderId;
	}
}