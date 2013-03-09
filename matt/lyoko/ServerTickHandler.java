package matt.lyoko;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ServerTickHandler implements ITickHandler
{
	public int lifePoints = 80;
	
	private void onPlayerTick(EntityPlayer player)
	{
	
	}
	
	private void onRenderTick(EntityPlayer player)
	{
		Minecraft mc = Minecraft.getMinecraft();
		FontRenderer font = mc.fontRenderer;
		int x = 30;
		int y = 30;
		int color = 10000;
		FMLClientHandler.instance().getClient().ingameGUI.drawString(font, Integer.toString(lifePoints), x, y, color);
	}
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
		if (type.equals(EnumSet.of(TickType.PLAYER)))
		{
				onPlayerTick((EntityPlayer)tickData[0]);
		}
		if (type.equals(EnumSet.of(TickType.RENDER)))
		{
				onRenderTick((EntityPlayer)tickData[1]);
		}
	}

	@Override
	public EnumSet<TickType> ticks() 
	{
		return EnumSet.of(TickType.PLAYER, TickType.SERVER, TickType.RENDER);
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLabel()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
