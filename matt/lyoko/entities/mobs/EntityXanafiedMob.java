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

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityXanafiedMob extends Specter
{
	public EntityLivingBase infectedMob;
	
	/**
	 * DO NOT USE THIS CONSTRUCTOR
	 */
	@Deprecated
	public EntityXanafiedMob(World world)
	{
		super(world);
	}
	
	public EntityXanafiedMob(World world, EntityLivingBase infectedMob)
	{
		this(world);
		this.infectedMob = infectedMob;
		if(infectedMob != null)
			this.setSize(infectedMob.width, infectedMob.height);
		else
			this.setSize(1.0F, 1.0F);
	}
	
	public EntityXanafiedMob(World world, double x, double y, double z, EntityLivingBase infectedMob)
	{
		this(world, infectedMob);
		this.setPosition(x, y, z);
	}
	
	@Override
    public void writeEntityToNBT(NBTTagCompound tag)
    {
    	super.writeEntityToNBT(tag);
    	if(infectedMob != null)
    	{
    		tag.setInteger("mobId", infectedMob.entityId);
    	}
    }
    
    @Override
    public void readEntityFromNBT(NBTTagCompound tag)
    {
    	super.readEntityFromNBT(tag);
    	infectedMob = (EntityLivingBase) worldObj.getEntityByID(tag.getInteger("mobId"));
    }
    
    @Override
    public void onDeath(DamageSource damageSource)
    {
    	if(!worldObj.isRemote && infectedMob != null)
    	{
    		infectedMob.isDead = false;
    		worldObj.spawnEntityInWorld(infectedMob);
    	}
    	super.onDeath(damageSource);
    }
}