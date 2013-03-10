package matt.lyoko.entities.mobs;

import net.minecraft.src.*;
import net.minecraft.world.World;

public class EntityKrab extends EntityLyoko
{
    public EntityKrab(World par1World)
    {
        super(par1World);
        texture = "/matt/lyoko/mob/krab.png";
        attackStrength = 6;
        moveSpeed = 0.7F;
    }

    public int getMaxHealth()
    {
        return 50;
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
