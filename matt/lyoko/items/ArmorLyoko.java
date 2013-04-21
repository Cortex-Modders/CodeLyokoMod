package matt.lyoko.items;

import matt.lyoko.CodeLyoko;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemInWorldManager;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ArmorLyoko extends ItemArmor
{

        public ArmorLyoko(int i, EnumArmorMaterial enumarmormaterial, int j, int k, String str)
        {
                super(i, enumarmormaterial, j, k);
                this.setCreativeTab(CodeLyoko.LyokoTabs);
                this.armorOwner = str;
                this.setContainerItem(this);
        }
        
        private static double defaultReachDistance = 5;
        private static double newReachDistance = 10;
        private String armorOwner;
        
        @Override
    	public void updateIcons(IconRegister iconRegister)
    	{
    		if(this.itemID == CodeLyoko.AelitaHelmet.itemID || this.itemID == CodeLyoko.OddHelmet.itemID
    				|| this.itemID == CodeLyoko.UlrichHelmet.itemID || this.itemID == CodeLyoko.YumiHelmet.itemID
    				|| this.itemID == CodeLyoko.WilliamHelmet.itemID)
    	         iconIndex = iconRegister.registerIcon("lyoko:" + armorOwner + "helmet");
    		if(this.itemID == CodeLyoko.AelitaChest.itemID || this.itemID == CodeLyoko.OddChest.itemID
    				|| this.itemID == CodeLyoko.UlrichChest.itemID || this.itemID == CodeLyoko.YumiChest.itemID
    				|| this.itemID == CodeLyoko.WilliamChest.itemID)
    	         iconIndex = iconRegister.registerIcon("lyoko:" + armorOwner + "chest");
    		if(this.itemID == CodeLyoko.AelitaLegs.itemID || this.itemID == CodeLyoko.OddLegs.itemID
    				|| this.itemID == CodeLyoko.UlrichLegs.itemID || this.itemID == CodeLyoko.YumiLegs.itemID
    				|| this.itemID == CodeLyoko.WilliamLegs.itemID)
    	         iconIndex = iconRegister.registerIcon("lyoko:" + armorOwner + "legs");
    		if(this.itemID == CodeLyoko.AelitaBoots.itemID || this.itemID == CodeLyoko.OddBoots.itemID
    				|| this.itemID == CodeLyoko.UlrichBoots.itemID || this.itemID == CodeLyoko.YumiBoots.itemID
    				|| this.itemID == CodeLyoko.WilliamBoots.itemID)
    	         iconIndex = iconRegister.registerIcon("lyoko:" + armorOwner + "boots");
    	}
        
        @Override
        public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack stack)
        {
        	if (player.getCurrentItemOrArmor(4) != null && player.getCurrentItemOrArmor(3) != null
    				&& player.getCurrentItemOrArmor(2) != null && player.getCurrentItemOrArmor(1) != null)
    		{
        		if(this.armorType == 0)
        		{
        			ItemStack helmet = player.getCurrentItemOrArmor(4);
        			ItemStack chest = player.getCurrentItemOrArmor(3);
        			ItemStack legs = player.getCurrentItemOrArmor(2);
        			ItemStack boots = player.getCurrentItemOrArmor(1);
        			if (helmet.getItem() == CodeLyoko.AelitaHelmet && chest.getItem() == CodeLyoko.AelitaChest
        					&& legs.getItem() == CodeLyoko.AelitaLegs && boots.getItem() == CodeLyoko.AelitaBoots)
        			{
        				player.capabilities.allowFlying = true;
        				player.fallDistance = 0;
        				for(int x = 0; x < 9; x++)
        				{
        					ItemStack stack2 = player.inventory.getStackInSlot(x);
        					if((stack2 == null || stack2 == new ItemStack(CodeLyoko.VirtualBlock, stack2.stackSize)) && !player.inventory.hasItem(CodeLyoko.VirtualBlock.blockID))
        					{
        						player.inventory.setInventorySlotContents(x, new ItemStack(CodeLyoko.VirtualBlock, 1));
        					}
        					if((stack2 == null || stack2 == new ItemStack(CodeLyoko.EnergyField, stack2.stackSize)) && !player.inventory.hasItem(CodeLyoko.EnergyField.itemID))
        					{
        						player.inventory.setInventorySlotContents(x, new ItemStack(CodeLyoko.EnergyField, 1));
        					}
        				}
        			}
        			else if (helmet.getItem() == CodeLyoko.OddHelmet && chest.getItem() == CodeLyoko.OddChest
        					&& legs.getItem() == CodeLyoko.OddLegs && boots.getItem() == CodeLyoko.OddBoots)
        			{
        				player.addPotionEffect((new PotionEffect(Potion.jump.getId(), 20, 3)));
        				player.fallDistance = 0;
        				for(int x = 0; x < 9; x++)
        				{
        					ItemStack stack2 = player.inventory.getStackInSlot(x);
        					if((stack2 == null || stack2 == new ItemStack(CodeLyoko.Glove, stack2.stackSize)) && !player.inventory.hasItem(CodeLyoko.Glove.itemID))
        					{
        						player.inventory.setInventorySlotContents(x, new ItemStack(CodeLyoko.Glove, 1));
        					}
        				}
        			}
        			else if (helmet.getItem() == CodeLyoko.UlrichHelmet && chest.getItem() == CodeLyoko.UlrichChest
        					&& legs.getItem() == CodeLyoko.UlrichLegs && boots.getItem() == CodeLyoko.UlrichBoots)
        			{
        				if(player.isSprinting())
        				{
        					player.addPotionEffect((new PotionEffect(Potion.moveSpeed.getId(), 20, 2)));
        				}
        				player.fallDistance = 0;
        				for(int x = 0; x < 9; x++)
        				{
        					ItemStack stack2 = player.inventory.getStackInSlot(x);
        					if((stack2 == null || stack2 == new ItemStack(CodeLyoko.Katana, stack2.stackSize)) && !player.inventory.hasItem(CodeLyoko.Katana.itemID))
        					{
        						player.inventory.setInventorySlotContents(x, new ItemStack(CodeLyoko.Katana, 1));
        					}
        				}
        			}
        			else if (helmet.getItem() == CodeLyoko.YumiHelmet && chest.getItem() == CodeLyoko.YumiChest
        					&& legs.getItem() == CodeLyoko.YumiLegs && boots.getItem() == CodeLyoko.YumiBoots)
        			{
        				player.getFoodStats().setFoodSaturationLevel(40.0F);
        				player.getFoodStats().setFoodLevel(20);
        				player.fallDistance = 0;
        				if(player instanceof EntityPlayerMP)
        				{
        					ItemInWorldManager iiwm = ((EntityPlayerMP)player).theItemInWorldManager;
        					if(newReachDistance != iiwm.getBlockReachDistance())
        					{
        						iiwm.setBlockReachDistance(newReachDistance);
        					}
        				}
        				for(int x = 0; x < 9; x++)
        				{
        					ItemStack stack2 = player.inventory.getStackInSlot(x);
        					if((stack2 == null || stack2 == new ItemStack(CodeLyoko.Fan, stack2.stackSize)) && !player.inventory.hasItem(CodeLyoko.Fan.itemID))
        					{
        						player.inventory.setInventorySlotContents(x, new ItemStack(CodeLyoko.Fan, 1));
        					}
        				}
        			}
        			else if (helmet.getItem() == CodeLyoko.WilliamHelmet && chest.getItem() == CodeLyoko.WilliamChest
        					&& legs.getItem() == CodeLyoko.WilliamLegs && boots.getItem() == CodeLyoko.WilliamBoots)
        			{
        				if(player.isSprinting())
        				{
        					player.addPotionEffect((new PotionEffect(Potion.moveSpeed.getId(), 20, 2)));
        					player.addPotionEffect((new PotionEffect(Potion.invisibility.getId(), 20, 2)));
        				}
        				player.fallDistance = 0;
        				for(int x = 0; x < 9; x++)
        				{
        					ItemStack stack2 = player.inventory.getStackInSlot(x);
        					if((stack2 == null || stack2 == new ItemStack(CodeLyoko.Zweihander, stack2.stackSize)) && !player.inventory.hasItem(CodeLyoko.Zweihander.itemID))
        					{
        						player.inventory.setInventorySlotContents(x, new ItemStack(CodeLyoko.Zweihander, 1));
        					}
        				}
        			}
        		}
    		}
        	else if ((player.getCurrentItemOrArmor(4) == null || player.getCurrentItemOrArmor(3) == null
    				|| player.getCurrentItemOrArmor(2) == null || player.getCurrentItemOrArmor(1) == null)
    				&& player.capabilities.isCreativeMode == false)
			{
				player.capabilities.allowFlying = false;
				if(player instanceof EntityPlayerMP)
				{
					ItemInWorldManager iiwm = ((EntityPlayerMP)player).theItemInWorldManager;
					if(iiwm.getBlockReachDistance() != defaultReachDistance)
					{
						iiwm.setBlockReachDistance(defaultReachDistance);
					}
				}
			}
        }
        
        @Override
        public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, int layer)
        {
        	if(itemstack.itemID == CodeLyoko.AelitaHelmet.itemID || itemstack.itemID == CodeLyoko.AelitaChest.itemID || itemstack.itemID == CodeLyoko.AelitaBoots.itemID
        			|| itemstack.itemID == CodeLyoko.OddHelmet.itemID || itemstack.itemID == CodeLyoko.OddChest.itemID || itemstack.itemID == CodeLyoko.OddBoots.itemID
        			|| itemstack.itemID == CodeLyoko.UlrichHelmet.itemID || itemstack.itemID == CodeLyoko.UlrichChest.itemID || itemstack.itemID == CodeLyoko.UlrichBoots.itemID
        			|| itemstack.itemID == CodeLyoko.YumiHelmet.itemID || itemstack.itemID == CodeLyoko.YumiChest.itemID || itemstack.itemID == CodeLyoko.YumiBoots.itemID
        			|| itemstack.itemID == CodeLyoko.WilliamHelmet.itemID || itemstack.itemID == CodeLyoko.WilliamChest.itemID || itemstack.itemID == CodeLyoko.WilliamBoots.itemID)
            {
            	return "/mods/lyoko/textures/armor/" + this.armorOwner + "_1.png";
            }
            if(itemstack.itemID == CodeLyoko.AelitaLegs.itemID || itemstack.itemID == CodeLyoko.OddLegs.itemID
            		|| itemstack.itemID == CodeLyoko.UlrichLegs.itemID || itemstack.itemID == CodeLyoko.YumiLegs.itemID
            		|| itemstack.itemID == CodeLyoko.WilliamLegs.itemID)
            {
            	return "/mods/lyoko/textures/armor/" + this.armorOwner + "_2.png";
            }
            return "/mods/lyoko/textures/armor/" + this.armorOwner + "_1.png";
        }
}
