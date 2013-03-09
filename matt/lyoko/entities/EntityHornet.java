package matt.lyoko.entities;

import net.minecraft.src.*;
import net.minecraft.world.World;

public class EntityHornet extends EntityLyoko
{
    public EntityHornet(World par1World)
    {
        super(par1World);
        texture = "/matt/lyoko/mob/hornet.png";
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