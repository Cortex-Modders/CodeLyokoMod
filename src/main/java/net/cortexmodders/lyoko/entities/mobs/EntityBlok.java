/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.entities.mobs;

import net.minecraft.world.World;

public class EntityBlok extends EntityLyoko
{
    public EntityBlok(World par1World)
    {
        super(par1World, 0.3F);
        this.setSize(0.9375F, 1.5F);
    }

    /*
     * @Override
     * protected void applyEntityAttributes()
     * {
     * super.applyEntityAttributes();
     * // Max Health - default 20.0D - min 0.0D - max Double.MAX_VALUE
     * this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(
     * 100.0D);
     * // Follow Range - default 32.0D - min 0.0D - max 2048.0D
     * this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(
     * 32.0D);
     * // Knockback Resistance - default 0.0D - min 0.0D - max 1.0D
     * this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0D
     * );
     * // Movement Speed - default 0.699D - min 0.0D - max Double.MAX_VALUE
     * this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(
     * MOB_SPEED);
     * // Attack Damage - default 2.0D - min 0.0D - max Doubt.MAX_VALUE
     * this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D
     * );
     * }
     */

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound()
    {
        return null;// "mob.skeleton.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound()
    {
        return null;// "mob.skeleton.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected String getDeathSound()
    {
        return null;// "mob.skeleton.death";
    }

}
