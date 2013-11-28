/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.entities.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityLaserArrow extends EntityLyokoRanged
{
    public EntityLaserArrow(World world, EntityLivingBase entLiving, float f)
    {
        super(world, entLiving, f);
    }

    public EntityLaserArrow(World world)
    {
        super(world);
    }
}