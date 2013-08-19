/*
 * Code Lyoko Mod for Minecraft v@VERSION
 *
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 * 
 */

package matt.lyoko.entities.mobs;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class Specter extends EntityMob
{
	public Specter(World world)
	{
		super(world);
	}
	
	public Specter(World world, double x, double y, double z)
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