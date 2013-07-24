package matt.lyoko.entities.mobs;

import matt.lyoko.lib.PlayerInformation;
import net.minecraft.block.Block;
import net.minecraft.src.*;
import net.minecraft.world.World;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;

public class EntityMegaTank extends EntityLyoko
{
	private float MOB_SPEED = 1.5F;
	
    public EntityMegaTank(World par1World)
    {
        super(par1World);
        this.setSize(1.25F, 1.25F);
    }
    
    @Override
    protected void func_110147_ax()
    {
        super.func_110147_ax();
        // Max Health - default 20.0D - min 0.0D - max Double.MAX_VALUE
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(150.0D);
        // Follow Range - default 32.0D - min 0.0D - max 2048.0D
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(32.0D);
        // Knockback Resistance - default 0.0D - min 0.0D - max 1.0D
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(0.0D);
        // Movement Speed - default 0.699D - min 0.0D - max Double.MAX_VALUE
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(MOB_SPEED);
        // Attack Damage - default 2.0D - min 0.0D - max Doubt.MAX_VALUE
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(attackStrength);
    }
    
    public void onCollideWithPlayer(EntityPlayer entp)
    {
    	if(!entp.capabilities.isCreativeMode)
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
