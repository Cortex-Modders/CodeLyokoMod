package matt.lyoko.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.src.*;
import net.minecraft.world.World;

public class EntityBlok extends EntityLyoko implements IRangedAttackMob
{
    public EntityBlok(World par1World)
    {
        super(par1World);
        this.texture = "/matt/lyoko/mob/blok.png";
        this.moveSpeed = 0.3F;
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAILaserAttack(this, this.moveSpeed, 1, 80));
        this.tasks.addTask(3, new EntityAIWander(this, this.moveSpeed));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
    }
    
    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
    }

    public int getMaxHealth()
    {
        return 100;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return null;//"mob.skeleton.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return null;//"mob.skeleton.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return null;//"mob.skeleton.death";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        //this.worldObj.playSoundAtEntity(this, "mob.skeleton.step", 0.15F, 1.0F);
    }

    public int getAttackStrength(Entity par1Entity)
    {
    	return 10;
    }
    
    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {
        return 0;//Item.arrow.shiftedIndex;
    }
    
    public void func_82196_d(EntityLiving par1EntityLiving)
    {
        EntityLaser var2 = new EntityLaser(this.worldObj, this, par1EntityLiving, 1.6F, 12.0F);
        //this.worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(var2);
    }
    
	@Override
	public void attackEntityWithRangedAttack(EntityLiving var1) {
		// TODO Auto-generated method stub
		
	}
}


/*
package net.minecraft.src.lyoko;

import net.minecraft.src.*;

public class EntityBlok extends EntityLyoko
{
    public EntityBlok(World par1World)
    {
        super(par1World);
        texture = "/lyoko/mob/blok.png";
        //attackStrength = 6;
        moveSpeed = 0.7F;
        this.tasks.addTask(4, new EntityAILaserAttack(this, this.moveSpeed, 1, 60));
        this.tasks.addTask(5, new EntityAIWander(this, this.moveSpeed));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
    }

    public int getMaxHealth()
    {
        return 50;
    }
    
    public boolean isAIEnabled()
    {
        return true;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
/*
    protected String getLivingSound()
    {
        return null;
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
/*
    protected String getHurtSound()
    {
        return null;
    }

    /**
     * Returns the sound this mob makes on death.
     */
/*
    protected String getDeathSound()
    {
        return null;
    }
}
*/