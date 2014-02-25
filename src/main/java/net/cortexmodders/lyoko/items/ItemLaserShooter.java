/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.items;

import java.util.List;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.entities.projectile.EntityLaser;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemLaserShooter extends Item
{
    public ItemLaserShooter()
    {
        this.maxStackSize = 1;
        this.setMaxDamage(0);
        this.setCreativeTab(CodeLyoko.LyokoTabs);
        this.setFull3D();
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean B)
    {
        list.add("This is a debug tool that shoots lasers.");
    }
    
    @Override
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
        int var6 = this.getMaxItemUseDuration(par1ItemStack) - par4;
        
        ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, var6);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
            return;
        var6 = event.charge;
        
        boolean var5 = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;
        
        if (var5 || par3EntityPlayer.inventory.hasItem(ModItems.fan))
        {
            float var7 = var6 / 20.0F;
            var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;
            
            if (var7 < 0.1D)
                return;
            
            if (var7 > 1.0F)
                var7 = 1.0F;
            
            EntityLaser var8 = new EntityLaser(par2World, par3EntityPlayer, var7 * 5F);
            
            if (var7 == 1.0F)
                var8.setIsCritical(true);
            
            int var9 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);
            
            if (var9 > 0)
                var8.setDamage(var8.getDamage() + var9 * 0.5D + 0.5D);
            
            int var10 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);
            
            if (var10 > 0)
                var8.setKnockbackStrength(var10);
            
            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
                var8.setFire(100);
            
            // par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F,
            // 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);
            
            if (!par2World.isRemote)
                par2World.spawnEntityInWorld(var8);
        }
    }
    
    public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        return par1ItemStack;
    }
    
    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
    }
    
    /**
     * Called whenever this item is equipped and the right mouse button is
     * pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (player.isSneaking())
        {
            Item item;
            int index = CodeLyoko.debugTools.indexOf(this);
            if (CodeLyoko.debugTools.size() == index + 1)
                item = CodeLyoko.debugTools.get(0);
            else
                item = CodeLyoko.debugTools.get(index + 1);
            stack = new ItemStack(item, stack.stackSize);
            return stack;
        }
        
        ArrowNockEvent event = new ArrowNockEvent(player, stack);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
            return event.result;
        
        if (player.capabilities.isCreativeMode || player.inventory.hasItem(ModItems.laserShooter))
            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        
        player.setItemInUse(stack, 72000000);
        
        return stack;
    }
    
    /**
     * Return the enchantability factor of the item, most of the time is based
     * on material.
     */
    @Override
    public int getItemEnchantability()
    {
        return 1;
    }
    
    @Override
    public void registerIcons(IIconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon("lyoko:laser");
    }
}
