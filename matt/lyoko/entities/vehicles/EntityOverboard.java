package matt.lyoko.entities.vehicles;

import matt.lyoko.CodeLyoko;
import net.minecraft.world.World;

public class EntityOverboard extends EntityVehicle
{
	public EntityOverboard(World world)
	{
		super(world);
		texture = "/mods/lyoko/textures/models/overboard.png";
        this.setSize(1.125F, 0.375F);
        this.setDroppedItem(CodeLyoko.Overboard);
	}
	
	public EntityOverboard(World world, double x, double y, double z)
	{
		super(world, x, y, z);
		texture = "/mods/lyoko/textures/models/overboard.png";
        this.setSize(1.125F, 0.375F);
        this.setDroppedItem(CodeLyoko.Overboard);
	}

	public String getTexture()
	{
		return this.texture;
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
}
