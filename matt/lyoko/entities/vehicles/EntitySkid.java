package matt.lyoko.entities.vehicles;

import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.mobs.*;
import net.minecraft.src.*;
import net.minecraft.world.World;

public class EntitySkid extends EntityVehicle
{
    public EntitySkid(World world)
    {
        super(world);
        texture = "/mods/lyoko/textures/models/skid.png";
        this.setSize(2.0F, 6.9375F);
        this.setDroppedItem(CodeLyoko.Skid);
    }
    
    public EntitySkid(World world, double x, double y, double z)
    {
        super(world, x, y, z);
        texture = "/mods/lyoko/textures/models/skid.png";
        this.setSize(2.0F, 6.9375F);
        this.setDroppedItem(CodeLyoko.Skid);
    }
    
    public String getTexture()
	{
		return this.texture;
	}
    
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
        return false;
    }
}
