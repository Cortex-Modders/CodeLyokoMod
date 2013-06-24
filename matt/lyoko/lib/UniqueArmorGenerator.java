package matt.lyoko.lib;

import net.minecraft.entity.player.EntityPlayer;

public class UniqueArmorGenerator
{
	public static int[] getUniquePlayerIdentifier(EntityPlayer player)
	{
		int[] playerIdentifier = new int[7];
		int usernameLength = player.username.length();
		int i = Math.abs(usernameLength + 9 - (5 * usernameLength) / 2);
		playerIdentifier[0] = i % 5;
		i = Math.abs(usernameLength - 4 + (3 * usernameLength) / 2) + 42;
		playerIdentifier[1] = i % 5;
		i = Math.abs(usernameLength + (2 * usernameLength) / 5 * 4) - 91;
		playerIdentifier[2] = i % 5;
		i = Math.abs(usernameLength * 4);
		playerIdentifier[3] = i % 5;
		i = Math.abs(usernameLength * 4 + 32 - (4 / (usernameLength + 22)));
		playerIdentifier[4] = i % 5;
		i = Math.abs(usernameLength * usernameLength + usernameLength);
		playerIdentifier[5] = i % 5;
		playerIdentifier[6] = usernameLength % 2;
		for(int j = 0; j < 7; j++)
		{
			System.out.println(playerIdentifier[j]);
		}
		return playerIdentifier;
	}
}