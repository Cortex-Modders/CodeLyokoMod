/*
package matt.lyoko;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderSuperCalc extends TileEntitySpecialRenderer
{

	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
	{
		modelSuperCalc = new ModelSuperCalc();
	}
	
	public void renderModelSuperCalc(TileEntitySuperCalc te, double d1, double d2, double d3, float f)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d1 + 0.5F, (float) d2 + 0.5F, (float) d3 + 0.5F);
		bindTextureByName("/matt/lyoko/terrain/SuperCalculator.png");
		GL11.glPushMatrix();
		modelSuperCalc.renderModel(0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	public void renderTileEntityAtTileEntity(TileEntity te, double d1, double d2, double d3, float f)
	{
		renderModelSuperCalc((TileEntitySuperCalc)te, d1, d2, d3, f);
	}
	
	private ModelSuperCalc modelSuperCalc;
	
}
*/