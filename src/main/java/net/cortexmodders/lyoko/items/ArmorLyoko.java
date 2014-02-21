/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
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
        if (this.equals(ModItems.AelitaHelmet) || this.equals(ModItems.OddHelmet) || this.equals(ModItems.UlrichHelmet) || this.equals(ModItems.YumiHelmet) || this.equals(ModItems.WilliamHelmet))
            this.itemIcon = iconRegister.registerIcon("lyoko:" + this.armorOwner + "helmet");
        if (this.equals(ModItems.AelitaChest) || this.equals(ModItems.OddChest) || this.equals(ModItems.UlrichChest) || this.equals(ModItems.YumiChest) || this.equals(ModItems.WilliamChest))
            this.itemIcon = iconRegister.registerIcon("lyoko:" + this.armorOwner + "chestplate");
        if (this.equals(ModItems.AelitaLegs) || this.equals(ModItems.OddLegs) || this.equals(ModItems.UlrichLegs) || this.equals(ModItems.YumiLegs) || this.equals(ModItems.WilliamLegs))
            this.itemIcon = iconRegister.registerIcon("lyoko:" + this.armorOwner + "leggings");
        if (this.equals(ModItems.AelitaBoots) || this.equals(ModItems.OddBoots) || this.equals(ModItems.UlrichBoots) || this.equals(ModItems.YumiBoots) || this.equals(ModItems.WilliamBoots))
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

                if (helmet.getItem() == ModItems.AelitaHelmet && chest.getItem() == ModItems.AelitaChest && legs.getItem() == ModItems.AelitaLegs && boots.getItem() == ModItems.AelitaBoots)
                {
                    player.capabilities.allowFlying = true;
                    player.fallDistance = 0;
                    for (int x = 0; x < 9; x++)
                    {
                        ItemStack stack2 = player.inventory.getStackInSlot(x);
                        if ((stack2 == null || stack2 == new ItemStack(ModBlocks.VirtualBlock, stack2.stackSize)) && !player.inventory.hasItem(Item.getItemFromBlock(ModBlocks.VirtualBlock)))
                            player.inventory.setInventorySlotContents(x, new ItemStack(ModBlocks.VirtualBlock, 1));
                        if ((stack2 == null || stack2 == new ItemStack(ModItems.EnergyField, stack2.stackSize)) && !player.inventory.hasItem(ModItems.EnergyField))
                            player.inventory.setInventorySlotContents(x, new ItemStack(ModItems.EnergyField, 1));
                    }
                } else if (helmet.getItem() == ModItems.OddHelmet && chest.getItem() == ModItems.OddChest && legs.getItem() == ModItems.OddLegs && boots.getItem() == ModItems.OddBoots)
                {
                    player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 1, 3));
                    player.fallDistance = 0;
                    for (int x = 0; x < 9; x++)
                    {
                        ItemStack stack2 = player.inventory.getStackInSlot(x);
                        if ((stack2 == null || stack2 == new ItemStack(ModItems.Glove, stack2.stackSize)) && !player.inventory.hasItem(ModItems.Glove))
                            player.inventory.setInventorySlotContents(x, new ItemStack(ModItems.Glove, 1));
                    }
                } else if (helmet.getItem() == ModItems.UlrichHelmet && chest.getItem() == ModItems.UlrichChest && legs.getItem() == ModItems.UlrichLegs && boots.getItem() == ModItems.UlrichBoots)
                {
                    if (player.isSprinting())
                        player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 1, 2));
                    player.fallDistance = 0;
                    for (int x = 0; x < 9; x++)
                    {
                        ItemStack stack2 = player.inventory.getStackInSlot(x);
                        if ((stack2 == null || stack2 == new ItemStack(ModItems.Katana, stack2.stackSize)) && !player.inventory.hasItem(ModItems.Katana))
                            player.inventory.setInventorySlotContents(x, new ItemStack(ModItems.Katana, 1));
                    }
                } else if (helmet.getItem() == ModItems.YumiHelmet && chest.getItem() == ModItems.YumiChest && legs.getItem() == ModItems.YumiLegs && boots.getItem() == ModItems.YumiBoots)
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
                        if ((stack2 == null || stack2 == new ItemStack(ModItems.Fan, stack2.stackSize)) && !player.inventory.hasItem(ModItems.Fan))
                            player.inventory.setInventorySlotContents(x, new ItemStack(ModItems.Fan, 1));
                    }
                    if (player instanceof EntityPlayerMP)
                        ((EntityPlayerMP) player).theItemInWorldManager.setBlockReachDistance(10.0D);
                } else if (helmet.getItem() == ModItems.WilliamHelmet && chest.getItem() == ModItems.WilliamChest && legs.getItem() == ModItems.WilliamLegs && boots.getItem() == ModItems.WilliamBoots)
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
                        if ((stack2 == null || stack2 == new ItemStack(ModItems.Zweihander, stack2.stackSize)) && !player.inventory.hasItem(ModItems.Zweihander))
                            player.inventory.setInventorySlotContents(x, new ItemStack(ModItems.Zweihander, 1));
                    }
                }
            }
        } else if ((player.getCurrentArmor(4) == null || player.getCurrentArmor(3) == null || player.getCurrentArmor(2) == null || player.getCurrentArmor(1) == null) && player.capabilities.isCreativeMode == false)
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

        if (itemstack.equals(ModItems.AelitaHelmet) || itemstack.equals(ModItems.AelitaChest) || itemstack.equals(ModItems.AelitaBoots) || itemstack.equals(ModItems.OddHelmet) || itemstack.equals(ModItems.OddChest) || itemstack.equals(ModItems.OddBoots) || itemstack.equals(ModItems.UlrichHelmet) || itemstack.equals(ModItems.UlrichChest) || itemstack.equals(ModItems.UlrichBoots) || itemstack.equals(ModItems.YumiHelmet) || itemstack.equals(ModItems.YumiChest) || itemstack.equals(ModItems.YumiBoots) || itemstack.equals(ModItems.WilliamHelmet) || itemstack.equals(ModItems.WilliamChest) || itemstack.equals(ModItems.WilliamBoots))
            return "lyoko:textures/armor/" + this.armorOwner + "_1.png";
        if (itemstack.equals(ModItems.AelitaLegs) || itemstack.equals(ModItems.OddLegs) || itemstack.equals(ModItems.UlrichLegs) || itemstack.equals(ModItems.YumiLegs) || itemstack.equals(ModItems.WilliamLegs))
            return "lyoko:textures/armor/" + this.armorOwner + "_2.png";
        return "lyoko:textures/armor/" + this.armorOwner + "_1.png";
    }
}
