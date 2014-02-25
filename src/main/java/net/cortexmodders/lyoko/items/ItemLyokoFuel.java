/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.items;

import java.util.List;

import net.cortexmodders.lyoko.CodeLyoko;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemLyokoFuel extends ItemLyoko
{
    
    public ItemLyokoFuel(int maxEnergy, Item depletedItem)
    {
        this.setMaxDamage(maxEnergy);
        this.setCreativeTab(CodeLyoko.LyokoTabs);
        this.depletedForm = depletedItem;
    }
    
    @Override
    public void registerIcons(IIconRegister iconRegister)
    {
        if (this == ModItems.leadCell)
            this.itemIcon = iconRegister.registerIcon("lyoko:leadcell");
        if (this == ModItems.uraniumCell)
            this.itemIcon = iconRegister.registerIcon("lyoko:uraniumcell");
    }
    
    public Item depletedForm;
    
    public Item getDepletedFuelItem()
    {
        return this.depletedForm;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean B)
    {
        list.add(Integer.toString(stack.getMaxDamage() - stack.getItemDamage()) + "/" + Integer.toString(stack.getMaxDamage()));
    }
    
    @Override
    public void onUpdate(ItemStack stack, World par2World, Entity par3Entity, int par4, boolean par5)
    {
        if (stack.getItemDamage() > stack.getMaxDamage())
            stack.setItemDamage(stack.getMaxDamage());
    }
}
