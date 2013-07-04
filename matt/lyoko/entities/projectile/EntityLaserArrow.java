package matt.lyoko.entities.projectile;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityLaserArrow extends EntityLyokoRanged
{
	public EntityLaserArrow(World world, EntityLivingBase entLiving, float f) {
		super(world, entLiving, f);
	}
}