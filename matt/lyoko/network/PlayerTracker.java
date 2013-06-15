package matt.lyoko.network;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import matt.lyoko.CodeLyoko;
import matt.lyoko.lib.DimensionIds;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPlayerTracker;

public class PlayerTracker implements IPlayerTracker
{
	@Override
	public void onPlayerLogin(EntityPlayer player)
	{
		if(!player.getEntityData().hasKey("lifePoints"))
		{
			player.getEntityData().newTag((byte)1, "lifePoints");
			player.getEntityData().setByte("lifePoints", (byte)100);
		}
	}
	
	@Override
	public void onPlayerLogout(EntityPlayer player)
	{
		
	}
	
	@Override
	public void onPlayerChangedDimension(EntityPlayer player)
	{
		if(!CodeLyoko.playerInLyoko(player) && player.getEntityData().hasKey("lifePoints"));
		{
			player.getEntityData().setByte("lifePoints", (byte)100);
		}
	}
	
	@Override
	public void onPlayerRespawn(EntityPlayer player)
	{
		
	}
}