/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.entities.mob;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class EntitySpecter extends EntityMob
{
    public EntitySpecter(World world)
    {
        super(world);
    }
    
    public EntitySpecter(World world, double x, double y, double z)
    {
        this(world);
        this.setPosition(x, y, z);
    }
    
    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
    }
    
    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
    }
}
