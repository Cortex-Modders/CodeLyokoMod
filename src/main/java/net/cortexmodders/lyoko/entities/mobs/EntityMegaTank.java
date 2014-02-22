/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.entities.mobs;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.lib.PlayerInformation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityMegaTank extends EntityLyoko
{
    public EntityMegaTank(World par1World)
    {
        super(par1World, 40, 1.5F);
        this.setSize(1.25F, 1.25F);
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer entp)
    {
        super.onCollideWithPlayer(entp);
        if (!entp.capabilities.isCreativeMode && CodeLyoko.entityInLyoko(entp))
            ;
        {
            PlayerInformation pi = PlayerInformation.forPlayer(entp);
            pi.setLifePoints(0);
        }
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound()
    {
        return null;
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound()
    {
        return null;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected String getDeathSound()
    {
        return null;
    }
}
