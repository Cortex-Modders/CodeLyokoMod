package matt.lyoko.items;

import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.projectile.EntityEnergyField;
import matt.lyoko.entities.projectile.EntityLaserArrow;
import matt.lyoko.entities.projectile.EntityLyokoRanged;
import matt.lyoko.lib.ItemIds;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemGlove extends Item
{       
    public ItemGlove(int id)
    {
        super(id);
        maxStackSize = 1;
        setMaxDamage(0);
        this.setCreativeTab(CodeLyoko.LyokoTabs);
        this.setFull3D();
    }
    
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
        
        int var6 = this.getMaxItemUseDuration(par1ItemStack) - par4;
        
        ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, var6);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return;
        }
        var6 = event.charge;
        
        boolean var5 = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

        if (var5 || par3EntityPlayer.inventory.hasItem(ModItems.Glove.itemID))
        {
            float var7 = (float)var6 / 20.0F;
            var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;

            if ((double)var7 < 0.1D)
            {
                return;
            }

            if (var7 > 1.0F)
            {
                var7 = 1.0F;
            }

            EntityLaserArrow var8 = new EntityLaserArrow(par2World, par3EntityPlayer, var7 * 20F);

            if (var7 == 1.0F)
            {
                var8.setIsCritical(true);
            }

            int var9 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);

            if (var9 > 0)
            {
                var8.setDamage(var8.getDamage() + (double)var9 * 0.5D + 0.5D);
            }

            int var10 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);

            if (var10 > 0)
            {
                var8.setKnockbackStrength(var10);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
            {
                var8.setFire(100);
            }
            
            //par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);
            
            if (!par2World.isRemote)
            {
                par2World.spawnEntityInWorld(var8);
            }
        }
    }

    public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        return par1ItemStack;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    //public EnumAction getItemUseAction(ItemStack par1ItemStack)
    //{
    //    return EnumAction.bow;
    //}

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return event.result;
        }
        
        if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(ModItems.Glove.itemID))
        {
            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        }
        
        par3EntityPlayer.setItemInUse(par1ItemStack, 72000000);
        
        return par1ItemStack;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 1;
    }
    
    @Override
	public void registerIcons(IconRegister iconRegister)
	{
    	itemIcon = iconRegister.registerIcon("lyoko:glove");
	}
    
    @Override
    public void onUpdate(ItemStack stack, World world, Entity ent, int slot, boolean par5)
	{
		if(ent instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)ent;
			
			if (player.getCurrentItemOrArmor(4) != null && player.getCurrentItemOrArmor(3) != null
					&& player.getCurrentItemOrArmor(2) != null && player.getCurrentItemOrArmor(1) != null)
			{
				ItemStack helmet = player.getCurrentItemOrArmor(4);
				ItemStack chest = player.getCurrentItemOrArmor(3);
				ItemStack legs = player.getCurrentItemOrArmor(2);
				ItemStack boots = player.getCurrentItemOrArmor(1);
				if (helmet.getItem() == ModItems.AelitaHelmet && chest.getItem() == ModItems.AelitaChest
						&& legs.getItem() == ModItems.AelitaLegs && boots.getItem() == ModItems.AelitaBoots)
				{
					if(stack.getItem() != ModItems.EnergyField)
					{
						player.inventory.setInventorySlotContents(slot, null);
					}
				}
				else if (helmet.getItem() == ModItems.OddHelmet && chest.getItem() == ModItems.OddChest
						&& legs.getItem() == ModItems.OddLegs && boots.getItem() == ModItems.OddBoots)
				{
					if(stack.getItem() != ModItems.Glove)
					{
						player.inventory.setInventorySlotContents(slot, null);
					}
				}
				else if (helmet.getItem() == ModItems.YumiHelmet && chest.getItem() == ModItems.YumiChest
						&& legs.getItem() == ModItems.YumiLegs && boots.getItem() == ModItems.YumiBoots)
				{
					if(stack.getItem() != ModItems.Fan)
					{
						player.inventory.setInventorySlotContents(slot, null);
					}
				}
				else if (helmet.getItem() == ModItems.UlrichHelmet && chest.getItem() == ModItems.UlrichChest
						&& legs.getItem() == ModItems.UlrichLegs && boots.getItem() == ModItems.UlrichBoots)
				{
					if(stack.getItem() != ModItems.Katana)
					{
						player.inventory.setInventorySlotContents(slot, null);
					}
				}
				else if (helmet.getItem() == ModItems.WilliamHelmet && chest.getItem() == ModItems.WilliamChest
						&& legs.getItem() == ModItems.WilliamLegs && boots.getItem() == ModItems.WilliamBoots)
				{
					if(stack.getItem() != ModItems.Zweihander)
					{
						player.inventory.setInventorySlotContents(slot, null);
					}
				}
			}
			else if ((player.getCurrentItemOrArmor(4) == null || player.getCurrentItemOrArmor(3) == null
					|| player.getCurrentItemOrArmor(2) == null || player.getCurrentItemOrArmor(1) == null)
					&& player.capabilities.isCreativeMode == false)
			{
				player.inventory.setInventorySlotContents(slot, null);
			}
		}
	}
}