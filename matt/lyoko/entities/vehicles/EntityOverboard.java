package matt.lyoko.entities.vehicles;

import matt.lyoko.entities.mobs.EntityLyoko;
import net.minecraft.world.World;

public class EntityOverboard extends EntityLyoko
{
	public EntityOverboard(World world)
	{
		super(world);
        texture = "/matt/lyoko/mob/overboard.png";
	}
	
	@Override
	public int getMaxHealth()
	{
		return 200;
	}
}
