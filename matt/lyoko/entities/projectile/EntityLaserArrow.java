package matt.lyoko.entities.projectile;

import matt.lyoko.entities.EntityLyokoRanged;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntityLaserArrow extends EntityLyokoRanged
{
	public EntityLaserArrow(World world, EntityLiving entLiving, float f) {
		super(world, entLiving, f);
	}
}