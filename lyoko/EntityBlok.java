package matt.lyoko;

import java.util.Calendar;

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

public class EntityBlok extends EntityLyoko implements IRangedAttackMob
{
    public EntityBlok(World par1World)
    {
        super(par1World);
        this.texture = "/matt/lyoko/mob/blok.png";
        this.moveSpeed = 0.3F;
        this.tasks.addTask(1, new EntityAISwimming(this));
        //this.tasks.addTask(2, new EntityAIRestrictSun(this));
        //this.tasks.addTask(3, new EntityAIFleeSun(this, this.moveSpeed));
        this.tasks.addTask(5, new EntityAIWander(this, this.moveSpeed));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(13, new Byte((byte)0));
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
        return 50;
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

    public boolean attackEntityAsMob(Entity par1Entity)
    {
        if (super.attackEntityAsMob(par1Entity))
        {
            if (this.func_82202_m() == 1 && par1Entity instanceof EntityLiving)
            {
                ((EntityLiving)par1Entity).addPotionEffect(new PotionEffect(Potion.wither.id, 200));
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public int getAttackStrength(Entity par1Entity)
    {
    	return super.getAttackStrength(par1Entity);
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {
        return 0;//Item.arrow.shiftedIndex;
    }

    /**
     * Drop 0-2 items of this living's type
     */
    /*
    protected void dropFewItems(boolean par1, int par2)
    {
        int var3;
        int var4;

        if (this.func_82202_m() == 1)
        {
            var3 = this.rand.nextInt(3 + par2) - 1;

            for (var4 = 0; var4 < var3; ++var4)
            {
                this.dropItem(Item.coal.shiftedIndex, 1);
            }
        }
        else
        {
            var3 = this.rand.nextInt(3 + par2);

            for (var4 = 0; var4 < var3; ++var4)
            {
                this.dropItem(Item.arrow.shiftedIndex, 1);
            }
        }

        var3 = this.rand.nextInt(3 + par2);

        for (var4 = 0; var4 < var3; ++var4)
        {
            this.dropItem(Item.bone.shiftedIndex, 1);
        }
    }

    protected void dropRareDrop(int par1)
    {
        if (this.func_82202_m() == 1)
        {
            this.entityDropItem(new ItemStack(Item.field_82799_bQ.shiftedIndex, 1, 1), 0.0F);
        }
    }

    protected void func_82164_bB()
    {
        super.func_82164_bB();
        this.func_70062_b(0, new ItemStack(Item.bow));
    }
    */

    @SideOnly(Side.CLIENT)

    protected void func_82164_bB()
    {
        super.func_82164_bB();
        this.setCurrentItemOrArmor(0, new ItemStack(Item.bow));
    }

    public void func_82196_d(EntityLiving par1EntityLiving)
    {
        EntityLaser var2 = new EntityLaser(this.worldObj, this, par1EntityLiving, 1.6F, 12.0F);
        /*
        int var3 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItem());
        int var4 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItem());

        if (var3 > 0)
        {
            var2.setDamage(var2.getDamage() + (double)var3 * 0.5D + 0.5D);
        }

        if (var4 > 0)
        {
            var2.setKnockbackStrength(var4);
        }

        if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, this.getHeldItem()) > 0 || this.func_82202_m() == 1)
        {
            var2.setFire(100);
        }
*/
        //this.worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(var2);
    }

    public int func_82202_m()
    {
        return this.dataWatcher.getWatchableObjectByte(13);
    }

    public void func_82201_a(int par1)
    {
        this.dataWatcher.updateObject(13, Byte.valueOf((byte)par1));
        this.isImmuneToFire = par1 == 1;

        if (par1 == 1)
        {
            this.setSize(0.72F, 2.16F);
        }
        else
        {
            this.setSize(0.6F, 1.8F);
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);

        if (par1NBTTagCompound.hasKey("LyokoBlok"))
        {
            byte var2 = par1NBTTagCompound.getByte("LyokoBlok");
            this.func_82201_a(var2);
        }

            this.tasks.addTask(4, new EntityAIArrowAttack(this, this.moveSpeed, 60, 10.0F));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setByte("LyokoBlok", (byte)this.func_82202_m());
    }

	@Override
	public void attackEntityWithRangedAttack(EntityLiving var1) {
		// TODO Auto-generated method stub
		
	}
}


/*
package net.minecraft.src.lyoko;

import net.minecraft.src.*;

public class EntityBlok extends EntityLyoko
{
    public EntityBlok(World par1World)
    {
        super(par1World);
        texture = "/lyoko/mob/blok.png";
        //attackStrength = 6;
        moveSpeed = 0.7F;
        this.tasks.addTask(4, new EntityAILaserAttack(this, this.moveSpeed, 1, 60));
        this.tasks.addTask(5, new EntityAIWander(this, this.moveSpeed));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
    }

    public int getMaxHealth()
    {
        return 50;
    }
    
    public boolean isAIEnabled()
    {
        return true;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
/*
    protected String getLivingSound()
    {
        return null;
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
/*
    protected String getHurtSound()
    {
        return null;
    }

    /**
     * Returns the sound this mob makes on death.
     */
/*
    protected String getDeathSound()
    {
        return null;
    }
}
*/