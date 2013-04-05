package matt.lyoko.render;

import org.lwjgl.opengl.GL11;

import matt.lyoko.model.ModelGlove;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ItemRenderGlove implements IItemRenderer
{
	protected ModelGlove gloveModel;
	
	public ItemRenderGlove()
	{
		gloveModel = new ModelGlove();
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		switch(type)
		{
			case EQUIPPED: return true;
			default: return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		switch(type)
		{
			case EQUIPPED:
			{
				GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture("/mods/lyoko/textures/models/glove.png");
				
				GL11.glRotatef(-55f, 0f, 0f, 1f);
				GL11.glRotatef(90f, 0f, 1f, 0f);
				GL11.glRotatef(90f, 1f, 0f, 0f);
				GL11.glRotatef(5f, 0f, 1f, 0f);
				GL11.glTranslatef(-0.12f, 0.03f, -0.9f);
				float scale = 1.55f;
				GL11.glScalef(scale, scale, scale);
				
				gloveModel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
				GL11.glPopMatrix();
			}
			default:
				break;
		}
	}
}