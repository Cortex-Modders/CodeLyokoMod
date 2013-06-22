package matt.lyoko.lib;

import net.minecraft.entity.player.EntityPlayer;

public class UniqueArmorGenerator
{
	public static long getUniquePlayerIdentifier(EntityPlayer player)
	{
		long identifier = 1;
		if(player != null)
		{
			String username = player.username;
			for(int i = 0; i < username.length(); i++)
			{
				identifier *= Integer.valueOf(username.substring(i, i + 1));
			}
			identifier += player.worldObj.getSeed();
		}
		return identifier;
	}
}