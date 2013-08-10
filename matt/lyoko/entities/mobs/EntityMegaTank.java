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

import matt.lyoko.CodeLyoko;
import matt.lyoko.lib.PlayerInformation;
import net.minecraft.block.Block;
import net.minecraft.src.*;
import net.minecraft.world.World;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.*;

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
    	if(!entp.capabilities.isCreativeMode && CodeLyoko.entityInLyoko(entp));
    	{
    		PlayerInformation pi = PlayerInformation.forPlayer(entp);
    		pi.setLifePoints(0);
    	}
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
}
