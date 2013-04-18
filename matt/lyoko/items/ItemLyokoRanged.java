package matt.lyoko.items;

import net.minecraftforge.common.*;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.src.*;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.*;
import matt.lyoko.*;
import matt.lyoko.entities.*;

public class ItemLyokoRanged extends Item
{       
    public ItemLyokoRanged(int id, Class<? extends EntityLyokoRanged> c, Item item, String text)
    {
        super(id);
        maxStackSize = 1;
        setMaxDamage(200);
        this.setCreativeTab(CodeLyoko.LyokoTabs);
        this.setFull3D();
        entityLyokoRanged = c;
        reqItem = item;
        texture = text;
    }
    
    private Class<? extends EntityLyokoRanged> entityLyokoRanged;
    private Item reqItem;
    private String texture;
    
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

        if (var5 || par3EntityPlayer.inventory.hasItem(reqItem.itemID))
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

            EntityLyokoRanged var8;
            
            try
            {
            	var8 = entityLyokoRanged.getConstructor(World.class, EntityLiving.class, float.class).newInstance(par2World, par3EntityPlayer, var7 * 20F);
            }
            catch(Exception e){
            	e.printStackTrace();
            	return;
            }

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

            par1ItemStack.damageItem(1, par3EntityPlayer);
            //par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);

            if (var5)
            {
                var8.canBePickedUp = 2;
            }
            else
            {
                //par3EntityPlayer.inventory.consumeInventoryItem(Item.arrow.shiftedIndex);
            }

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
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return event.result;
        }
        
        if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(CodeLyoko.Fan.itemID))
        {
            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        }

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
	public void updateIcons(IconRegister iconRegister)
	{
    	iconIndex = iconRegister.registerIcon("lyoko:" + texture);
	}
    
    private int life = 200;
	
	public void onUpdate(ItemStack stack, World world, Entity ent, int par4, boolean par5)
	{
		if(ent instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)ent;
			if(life > 0)
			{
				life--;
			}
			else
			{
				for(int i = 0; i < player.inventory.mainInventory.length; i++)
				{
					if(player.inventory.getStackInSlot(i) == stack)
					{
						player.inventory.setInventorySlotContents(i, null);
					}
				}
				life = 200;
			}
		}
	}
}