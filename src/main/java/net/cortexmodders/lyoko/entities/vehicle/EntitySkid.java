/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.entities.vehicle;

import net.cortexmodders.lyoko.items.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntitySkid extends EntityVehicle
{
    /**
     * the nav skids are ordered like this: Front, Back, Left, Right
     */
    private boolean[] navSkid = {true, true, true, true};

    public EntitySkid(World world)
    {
        super(world);
        this.setSize(2.0F, 6.9375F);
        this.setDroppedItem(ModItems.skidbladnir);
    }

    public EntitySkid(World world, double x, double y, double z)
    {
        super(world, x, y, z);
        this.setSize(2.0F, 6.9375F);
        this.setDroppedItem(ModItems.skidbladnir);
    }

    public int getMaxHealth()
    {
        return 1000;
    }

    @Override
    public boolean shouldDismountInWater(Entity rider)
    {
        return !(rider instanceof EntityPlayer);
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

    /**
     * Returns true if this entity should push and be pushed by other entities
     * when colliding.
     */
    @Override
    public boolean canBePushed()
    {
        return false;
    }

    /**
     * @param skidFront
     * @param skidBack
     * @param skidLeft
     * @param skidRight
     */
    public void setNavSkid(boolean skidFront, boolean skidBack, boolean skidLeft, boolean skidRight)
    {
        this.navSkid[0] = skidFront;
        this.navSkid[1] = skidBack;
        this.navSkid[2] = skidLeft;
        this.navSkid[3] = skidRight;
    }

    /**
     * Sets the nav skid at the given slot to true or not. Maybe should be an
     * array of
     * nav skid entity instances instead, though.
     *
     * @param skid
     * @param arraySlot
     */
    public void setNavSkid(boolean skid, int arraySlot)
    {
        this.navSkid[arraySlot] = skid;
    }

    /**
     * Gets the whole array of nav skids.
     *
     * @return
     */
    public boolean[] getNavSkids()
    {
        return this.navSkid;
    }

    /**
     * Gets the specified nav skid from the array.
     *
     * @param arraySlot
     * @return
     */
    public boolean getNavSkid(int arraySlot)
    {
        return this.navSkid[arraySlot];
    }
}
