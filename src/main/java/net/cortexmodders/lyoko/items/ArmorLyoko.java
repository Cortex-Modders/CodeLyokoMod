/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.items;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.blocks.ModBlocks;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ArmorLyoko extends ItemArmor
{
    public ArmorLyoko(ArmorMaterial enumarmormaterial, int j, int k, String str)
    {
        super(enumarmormaterial, j, k);
        this.setCreativeTab(CodeLyoko.LyokoTabs);
        this.armorOwner = str;
        this.setContainerItem(this);
    }
    
    private String armorOwner;
    
    @Override
    public void registerIcons(IIconRegister iconRegister)
    {
        if (this.equals(ModItems.aelitaHelmet) || this.equals(ModItems.oddHelmet) || this.equals(ModItems.ulrichHelmet) || this.equals(ModItems.yumiHelmet) || this.equals(ModItems.williamHelmet))
            this.itemIcon = iconRegister.registerIcon("lyoko:" + this.armorOwner + "helmet");
        if (this.equals(ModItems.aelitaChest) || this.equals(ModItems.oddChest) || this.equals(ModItems.ulrichChest) || this.equals(ModItems.yumiChest) || this.equals(ModItems.williamChest))
            this.itemIcon = iconRegister.registerIcon("lyoko:" + this.armorOwner + "chestplate");
        if (this.equals(ModItems.aelitaLegs) || this.equals(ModItems.oddLegs) || this.equals(ModItems.ulrichLegs) || this.equals(ModItems.yumiLegs) || this.equals(ModItems.williamLegs))
            this.itemIcon = iconRegister.registerIcon("lyoko:" + this.armorOwner + "leggings");
        if (this.equals(ModItems.aelitaBoots) || this.equals(ModItems.oddBoots) || this.equals(ModItems.ulrichBoots) || this.equals(ModItems.yumiBoots) || this.equals(ModItems.williamBoots))
            this.itemIcon = iconRegister.registerIcon("lyoko:" + this.armorOwner + "boots");
    }
    
    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
    {
        if (player.getCurrentArmor(4) != null && player.getCurrentArmor(3) != null && player.getCurrentArmor(2) != null && player.getCurrentArmor(1) != null)
        {
            if (this.armorType == 0)
            {
                ItemStack helmet = player.getCurrentArmor(4);
                ItemStack chest = player.getCurrentArmor(3);
                ItemStack legs = player.getCurrentArmor(2);
                ItemStack boots = player.getCurrentArmor(1);
                
                if (helmet.getItem() == ModItems.aelitaHelmet && chest.getItem() == ModItems.aelitaChest && legs.getItem() == ModItems.aelitaLegs && boots.getItem() == ModItems.aelitaBoots)
                {
                    player.capabilities.allowFlying = true;
                    player.fallDistance = 0;
                    for (int x = 0; x < 9; x++)
                    {
                        ItemStack stack2 = player.inventory.getStackInSlot(x);
                        if ((stack2 == null || stack2 == new ItemStack(ModBlocks.virtualBlock, stack2.stackSize)) && !player.inventory.hasItem(Item.getItemFromBlock(ModBlocks.virtualBlock)))
                            player.inventory.setInventorySlotContents(x, new ItemStack(ModBlocks.virtualBlock, 1));
                        if ((stack2 == null || stack2 == new ItemStack(ModItems.energyField, stack2.stackSize)) && !player.inventory.hasItem(ModItems.energyField))
                            player.inventory.setInventorySlotContents(x, new ItemStack(ModItems.energyField, 1));
                    }
                }
                else if (helmet.getItem() == ModItems.oddHelmet && chest.getItem() == ModItems.oddChest && legs.getItem() == ModItems.oddLegs && boots.getItem() == ModItems.oddBoots)
                {
                    player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 1, 3));
                    player.fallDistance = 0;
                    for (int x = 0; x < 9; x++)
                    {
                        ItemStack stack2 = player.inventory.getStackInSlot(x);
                        if ((stack2 == null || stack2 == new ItemStack(ModItems.glove, stack2.stackSize)) && !player.inventory.hasItem(ModItems.glove))
                            player.inventory.setInventorySlotContents(x, new ItemStack(ModItems.glove, 1));
                    }
                }
                else if (helmet.getItem() == ModItems.ulrichHelmet && chest.getItem() == ModItems.ulrichChest && legs.getItem() == ModItems.ulrichLegs && boots.getItem() == ModItems.ulrichBoots)
                {
                    if (player.isSprinting())
                        player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 1, 2));
                    player.fallDistance = 0;
                    for (int x = 0; x < 9; x++)
                    {
                        ItemStack stack2 = player.inventory.getStackInSlot(x);
                        if ((stack2 == null || stack2 == new ItemStack(ModItems.katana, stack2.stackSize)) && !player.inventory.hasItem(ModItems.katana))
                            player.inventory.setInventorySlotContents(x, new ItemStack(ModItems.katana, 1));
                    }
                }
                else if (helmet.getItem() == ModItems.yumiHelmet && chest.getItem() == ModItems.yumiChest && legs.getItem() == ModItems.yumiLegs && boots.getItem() == ModItems.yumiBoots)
                {
                    player.getFoodStats().setFoodSaturationLevel(40.0F);
                    player.getFoodStats().setFoodLevel(20);
                    player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 1, 1));
                    if (player.isSprinting())
                        player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 1, 0));
                    player.fallDistance = 0;
                    for (int x = 0; x < 9; x++)
                    {
                        ItemStack stack2 = player.inventory.getStackInSlot(x);
                        if ((stack2 == null || stack2 == new ItemStack(ModItems.fan, stack2.stackSize)) && !player.inventory.hasItem(ModItems.fan))
                            player.inventory.setInventorySlotContents(x, new ItemStack(ModItems.fan, 1));
                    }
                    if (player instanceof EntityPlayerMP)
                        ((EntityPlayerMP) player).theItemInWorldManager.setBlockReachDistance(10.0D);
                }
                else if (helmet.getItem() == ModItems.williamHelmet && chest.getItem() == ModItems.williamChest && legs.getItem() == ModItems.williamLegs && boots.getItem() == ModItems.williamBoots)
                {
                    if (player.isSprinting())
                    {
                        player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 1, 2));
                        player.addPotionEffect(new PotionEffect(Potion.invisibility.getId(), 1, 2));
                    }
                    player.fallDistance = 0;
                    for (int x = 0; x < 9; x++)
                    {
                        ItemStack stack2 = player.inventory.getStackInSlot(x);
                        if ((stack2 == null || stack2 == new ItemStack(ModItems.zweihander, stack2.stackSize)) && !player.inventory.hasItem(ModItems.zweihander))
                            player.inventory.setInventorySlotContents(x, new ItemStack(ModItems.zweihander, 1));
                    }
                }
            }
        }
        else if ((player.getCurrentArmor(4) == null || player.getCurrentArmor(3) == null || player.getCurrentArmor(2) == null || player.getCurrentArmor(1) == null) && player.capabilities.isCreativeMode == false)
        {
            player.capabilities.allowFlying = false;
            if (player.capabilities.isFlying)
                player.capabilities.isFlying = false;
            if (player instanceof EntityPlayerMP)
                ((EntityPlayerMP) player).theItemInWorldManager.setBlockReachDistance(5.0D);
        }
    }
    
    @Override
    public boolean isValidArmor(ItemStack stack, int armorType, Entity ent)
    {
        if (ent instanceof EntityPlayer)
            return true;
        return false;
    }
    
    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String type)
    {
        if (this.armorOwner.equals("william") && entity.isSprinting())
            if (entity instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer) entity;
                boolean shouldDisappear = true;
                for (int i = 0; i <= player.inventory.armorInventory.length; i++)
                    if (player.inventory.armorInventory[i] == null || !(player.inventory.armorInventory[i].getItem() instanceof ArmorLyoko))
                        shouldDisappear = false;
                if (shouldDisappear)
                    return "lyoko:textures/armor/blank.png";
            }
        
        if (itemstack.equals(ModItems.aelitaHelmet) || itemstack.equals(ModItems.aelitaChest) || itemstack.equals(ModItems.aelitaBoots) || itemstack.equals(ModItems.oddHelmet) || itemstack.equals(ModItems.oddChest) || itemstack.equals(ModItems.oddBoots) || itemstack.equals(ModItems.ulrichHelmet) || itemstack.equals(ModItems.ulrichChest) || itemstack.equals(ModItems.ulrichBoots) || itemstack.equals(ModItems.yumiHelmet) || itemstack.equals(ModItems.yumiChest) || itemstack.equals(ModItems.yumiBoots) || itemstack.equals(ModItems.williamHelmet) || itemstack.equals(ModItems.williamChest) || itemstack.equals(ModItems.williamBoots))
            return "lyoko:textures/armor/" + this.armorOwner + "_1.png";
        if (itemstack.equals(ModItems.aelitaLegs) || itemstack.equals(ModItems.oddLegs) || itemstack.equals(ModItems.ulrichLegs) || itemstack.equals(ModItems.yumiLegs) || itemstack.equals(ModItems.williamLegs))
            return "lyoko:textures/armor/" + this.armorOwner + "_2.png";
        return "lyoko:textures/armor/" + this.armorOwner + "_1.png";
    }
}
