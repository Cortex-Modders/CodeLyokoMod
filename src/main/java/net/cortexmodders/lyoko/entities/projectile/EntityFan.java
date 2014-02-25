/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.entities.projectile;

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
