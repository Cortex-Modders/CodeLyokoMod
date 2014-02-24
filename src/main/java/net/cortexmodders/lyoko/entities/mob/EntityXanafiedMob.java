/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.entities.mob;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
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
        if (infectedMob != null)
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
        if (this.infectedMob != null && this.infectedMob.isDead)
            this.infectedMob.isDead = false;

        if (!this.worldObj.isRemote && this.worldObj.difficultySetting.equals(EnumDifficulty.PEACEFUL))
            if (this.infectedMob != null)
                this.worldObj.spawnEntityInWorld(this.infectedMob);

        super.onUpdate();
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag)
    {
        super.writeEntityToNBT(tag);
        NBTTagCompound infected = new NBTTagCompound();
        if (this.infectedMob != null)
        {
            tag.setString("className", this.infectedMob.getClass().getName());
            this.infectedMob.writeToNBT(infected);
            this.infectedMob.writeEntityToNBT(infected);
        }
        tag.setTag("infectedMob", infected);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag)
    {
        super.readEntityFromNBT(tag);
        try
        {
            this.infectedMob = (EntityLivingBase) Class.forName(tag.getString("className")).getConstructor(World.class).newInstance(this.worldObj);
            NBTTagCompound infected = tag.getCompoundTag("infectedMob");
            if (infected == null)
                infected = new NBTTagCompound();
            this.infectedMob.readFromNBT(infected);
            this.infectedMob.readEntityFromNBT(infected);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void onDeath(DamageSource damageSource)
    {
        if (!this.worldObj.isRemote && this.infectedMob != null)
        {
            this.infectedMob.isDead = false;
            this.worldObj.spawnEntityInWorld(this.infectedMob);
        }
        super.onDeath(damageSource);
    }
}
