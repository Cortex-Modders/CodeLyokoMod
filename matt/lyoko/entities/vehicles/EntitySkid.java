package matt.lyoko.entities.vehicles;

import matt.lyoko.entities.mobs.*;
import net.minecraft.src.*;
import net.minecraft.world.World;

public class EntitySkid extends EntityLyoko
{
    public EntitySkid(World world)
    {
        super(world);
        texture = "/matt/lyoko/mob/skid.png";
        this.ignoreFrustumCheck = true;
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
}
