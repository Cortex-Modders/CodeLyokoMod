package matt.lyoko.entities.mobs;

import net.minecraft.block.Block;
import net.minecraft.src.*;
import net.minecraft.world.World;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;

public class EntityMegaTank extends EntityLyoko
{
    public EntityMegaTank(World par1World)
    {
        super(par1World);
        texture = "/mods/lyoko/textures/models/tank.png";
        moveSpeed = 1.1F;
    }

    public int getMaxHealth()
    {
        return 150;
    }
    
    public void onCollideWithPlayer(EntityPlayer entp)
    {
    	if(!entp.capabilities.isCreativeMode)
    	{
    		entp.setDead();
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
