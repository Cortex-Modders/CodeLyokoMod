package matt.lyoko.entities.vehicles;

import matt.lyoko.entities.mobs.EntityLyoko;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.world.World;

public class EntityOverboard extends EntityLyoko
{
	public EntityOverboard(World par1World)
	{
		super(par1World);
		texture = "/matt/lyoko/mob/overboard.png";
        this.setSize(1.125F, 0.375F);
        this.ignoreFrustumCheck = true;
        this.moveSpeed = 0.0F;
	}
	
	/**
	 * Returns the maximum health of the entity.
	 */
	public int getMaxHealth()
	{
		return 1000;
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
    
    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean canBePushed()
    {
        return true;
    }
}