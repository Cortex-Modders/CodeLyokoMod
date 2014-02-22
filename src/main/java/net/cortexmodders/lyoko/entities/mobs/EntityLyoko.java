/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.entities.mobs;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.entities.projectile.EntityLaser;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public abstract class EntityLyoko extends EntityMob implements IRangedAttackMob
{
    protected final int MAX_ATTACK_STRENGTH;// = 2;
    /** How much damage this mob's attacks deal */
    protected int attackStrength;// = MAX_ATTACK_STRENGTH;
    protected double mobSpeed = 0.5F;

    private EntityLyoko(World par1World, int attackStrength, boolean specialAttack, double speed)
    {
        super(par1World);
        this.experienceValue = 10;
        this.MAX_ATTACK_STRENGTH = attackStrength;
        this.mobSpeed = speed;
        this.attackStrength = attackStrength;
        this.tasks.addTask(1, new EntityAISwimming(this));
        if (!specialAttack)
            this.tasks.addTask(2, new EntityAIArrowAttack(this, (float) this.mobSpeed, 40, 20));
        this.tasks.addTask(3, new EntityAIWander(this, this.mobSpeed));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    public EntityLyoko(World world, int attackStrength, double speed)
    {
        this(world, attackStrength, true, speed);
    }

    public EntityLyoko(World world, double speed)
    {
        this(world, 2, false, speed);
    }

    @Override
    protected void applyEntityAttributes()
    {
        // Max Health - default 20.0D - min 0.0D - max Double.MAX_VALUE
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0D);
        // Follow Range - default 32.0D - min 0.0D - max 2048.0D
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
        // Knockback Resistance - default 0.0D - min 0.0D - max 1.0D
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0D);
        // Movement Speed - default 0.699D - min 0.0D - max Double.MAX_VALUE
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(this.mobSpeed);
        // Attack Damage - default 2.0D - min 0.0D - max Doubt.MAX_VALUE
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(this.attackStrength);
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    @Override
    public boolean isAIEnabled()
    {
        return true;
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

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (CodeLyoko.entityInLyoko(this))
            this.attackStrength = 0;
        else
            this.attackStrength = this.MAX_ATTACK_STRENGTH;

        if (!this.worldObj.isRemote && this.worldObj.difficultySetting.equals(EnumDifficulty.PEACEFUL))
            this.setDead();

        if (this.getHealth() <= 0)
        {
            this.setDead();
            if (this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"))
                this.worldObj.createExplosion((Entity) null, this.posX, this.posY, this.posZ, 2.0F, true);
        }
    }

    /**
     * Checks to make sure the light is not too bright where the mob is spawning
     */
    @Override
    protected boolean isValidLightLevel()
    {
        return true;
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase entitylivingbase, float f)
    {
        EntityLaser laser = new EntityLaser(this.worldObj, this, entitylivingbase, 1.6F, 2.0F);
        laser.motionY += 0.00000000000001D;
        // this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat()
        // * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(laser);
    }
}
