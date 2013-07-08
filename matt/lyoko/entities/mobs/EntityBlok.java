package matt.lyoko.entities.mobs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.src.*;
import net.minecraft.world.World;
import matt.lyoko.entities.*;
import matt.lyoko.entities.projectile.EntityLaser;

public class EntityBlok extends EntityLyoko implements IRangedAttackMob
{
	private float MOB_SPEED = 0.3F;
	
    public EntityBlok(World par1World)
    {
        super(par1World);
        this.setSize(0.9375F, 1.5F);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAILaserAttack(this, MOB_SPEED, 1, 80));
        this.tasks.addTask(3, new EntityAIWander(this, MOB_SPEED));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }
    
    @Override
    protected void func_110147_ax()
    {
        super.func_110147_ax();
        // Max Health - default 20.0D - min 0.0D - max Double.MAX_VALUE
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0D);
        // Follow Range - default 32.0D - min 0.0D - max 2048.0D
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(32.0D);
        // Knockback Resistance - default 0.0D - min 0.0D - max 1.0D
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(0.0D);
        // Movement Speed - default 0.699D - min 0.0D - max Double.MAX_VALUE
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(MOB_SPEED);
        // Attack Damage - default 2.0D - min 0.0D - max Doubt.MAX_VALUE
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0D);
    }
    
    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return null;//"mob.skeleton.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return null;//"mob.skeleton.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return null;//"mob.skeleton.death";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        //this.worldObj.playSoundAtEntity(this, "mob.skeleton.step", 0.15F, 1.0F);
    }

    public int getAttackStrength(Entity par1Entity)
    {
    	return 10;
    }
    
    public void func_82196_d(EntityLiving par1EntityLiving)
    {
        EntityLaser var2 = new EntityLaser(this.worldObj, this, par1EntityLiving, 1.6F, 12.0F);
        //this.worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(var2);
    }

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase entitylivingbase, float f) {
		// TODO Auto-generated method stub
		
	}

}