package matt.lyoko.world.portals;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class LyokoDesertTeleporter extends Teleporter
{
	public LyokoDesertTeleporter(WorldServer par1WorldServer)
	{
		super(par1WorldServer);
	}
	
	@Override
	public void placeInPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
	{
		
	}
	
	public boolean placeInExistingPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
	{
		return true;
	}
}