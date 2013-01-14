package matt.lyoko;

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
        texture = "/matt/lyoko/mob/tank.png";
        moveSpeed = 1.1F;
    }

    public int getMaxHealth()
    {
        return 70;
    }
    
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, int par5, int par6, Block block)
    {
    	block.dropBlockAsItem(par1World, par2, par3, par4, par5, par6);
    }
    
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
    {
    	par1EntityPlayer.setDead();
    	//this.lyokoexplode();
    }
    
    public void onEntityDeath(Entity entity)
    {
    	//this.lyokoexplode();
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
