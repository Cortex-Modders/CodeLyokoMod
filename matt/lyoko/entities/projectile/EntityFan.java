package matt.lyoko.entities.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityFan extends EntityLyokoRanged
{
	public EntityFan(World world, EntityLivingBase entLiving, float f)
	{
		super(world, entLiving, f);
	}
	
	public EntityFan(World world)
	{
		super(world);
	}
}