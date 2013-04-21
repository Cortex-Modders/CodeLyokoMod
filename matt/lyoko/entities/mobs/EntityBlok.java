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

public class EntityBlok extends EntityLyoko implements IRangedAttackMob
{
    public EntityBlok(World par1World)
    {
        super(par1World);
        this.texture = "/mods/lyoko/textures/models/blok.png";
        this.moveSpeed = 0.3F;
        this.setSize(0.9375F, 1.5F);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAILaserAttack(this, this.moveSpeed, 1, 80));
        this.tasks.addTask(3, new EntityAIWander(this, this.moveSpeed));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
    }
    
    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
    }

    public int getMaxHealth()
    {
        return 100;
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
	public void attackEntityWithRangedAttack(EntityLiving entityliving, float f) {
		// TODO Auto-generated method stub
		
	}
}