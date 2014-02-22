/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.entities.vehicles;

import net.cortexmodders.lyoko.items.ModItems;
import net.minecraft.world.World;

public class EntityOverboard extends EntityVehicle
{
    public EntityOverboard(World world)
    {
        super(world);
        this.setSize(1.125F, 0.375F);
        this.setDroppedItem(ModItems.overboard);
    }

    public EntityOverboard(World world, double x, double y, double z)
    {
        super(world, x, y, z);
        this.setSize(1.125F, 0.375F);
        this.setDroppedItem(ModItems.overboard);
    }

    /**
     * Returns the maximum health of the entity.
     */
    public int getMaxHealth()
    {
        return 1000;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return null;
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return null;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return null;
    }
}
