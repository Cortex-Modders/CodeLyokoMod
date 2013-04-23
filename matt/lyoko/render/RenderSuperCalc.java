package matt.lyoko.render;

import matt.lyoko.entities.TileEntitySuperCalc;
import matt.lyoko.model.ModelSuperCalc;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

public class RenderSuperCalc extends TileEntitySpecialRenderer
{
	//Model file
	private ModelSuperCalc model;
	
	public RenderSuperCalc()
	{
		//initialization of Model File
		model = new ModelSuperCalc();
	}
								//your TileEntity
	public void renderAModelAt(TileEntitySuperCalc tile, double d, double d1, double d2, float f)
	{
		int i = 0;
		
		if(tile.worldObj != null) 
		{
			i = (tile.worldObj.getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord)); 
		}
		
						//directory of the model's texture file
		bindTextureByName("/matt/lyoko/terrain/SuperCalculator.png");
		
		GL11.glPushMatrix(); 
		GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
		GL11.glRotatef(0, 0.0F, 1.0F, 0.0F); 
		GL11.glScalef(1.0F, -1F, -1F); 
		model.renderModel(0.0625F); 
		GL11.glPopMatrix(); 
	}
	
	public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
	{
						//your TileEntity
		renderAModelAt((TileEntitySuperCalc) tileentity, d, d1, d2, f);
	}
}