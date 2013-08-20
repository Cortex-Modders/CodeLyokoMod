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

public class EntityXanafiedMob extends EntitySpecter
{
	public EntityLivingBase infectedMob;
	
	/**
	 * DO NOT USE THIS CONSTRUCTOR
	 */
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
	public void onUpdate()
	{
		if(infectedMob != null && infectedMob.isDead)
			infectedMob.isDead = false;
		
		if(!worldObj.isRemote && worldObj.difficultySetting == 0)
		{
	    	if(infectedMob != null)
	    	{
	    		worldObj.spawnEntityInWorld(infectedMob);
	    	}
		}
		
		super.onUpdate();
	}
	
	@Override
    public void writeEntityToNBT(NBTTagCompound tag)
    {
    	super.writeEntityToNBT(tag);
    	NBTTagCompound infected = new NBTTagCompound();
    	if(infectedMob != null)
    	{
    		tag.setString("className", infectedMob.getClass().getName());
    		infectedMob.writeToNBT(infected);
    		infectedMob.writeEntityToNBT(infected);
    	}
    	tag.setCompoundTag("infectedMob", infected);
    }
    
    @Override
    public void readEntityFromNBT(NBTTagCompound tag)
    {
    	super.readEntityFromNBT(tag);
    	try
    	{
    		infectedMob = (EntityLivingBase) Class.forName(tag.getString("className")).getConstructor(World.class).newInstance(worldObj);
    		NBTTagCompound infected = tag.getCompoundTag("infectedMob");
    		if(infected == null)
    			infected = new NBTTagCompound();
    		infectedMob.readFromNBT(infected);
    		infectedMob.readEntityFromNBT(infected);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
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