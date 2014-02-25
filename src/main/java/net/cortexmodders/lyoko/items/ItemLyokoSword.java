/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.items;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.lib.PlayerInformation;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

import com.google.common.collect.Multimap;

public class ItemLyokoSword extends ItemSword
{
    private int weaponDamage;
    
    public ItemLyokoSword(ToolMaterial par2EnumToolMaterial)
    {
        super(par2EnumToolMaterial);
        this.setCreativeTab(CodeLyoko.LyokoTabs);
        this.setMaxDamage(0);
    }
    
    @Override
    public void onUpdate(ItemStack stack, World world, Entity ent, int slot, boolean par5)
    {
        if (CodeLyoko.entityInLyoko(ent))
            this.weaponDamage = 0;
        else
            this.weaponDamage = 34;
        
        if (ent instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) ent;
            
            if (player.getCurrentArmor(4) != null && player.getCurrentArmor(3) != null && player.getCurrentArmor(2) != null && player.getCurrentArmor(1) != null)
            {
                ItemStack helmet = player.getCurrentArmor(4);
                ItemStack chest = player.getCurrentArmor(3);
                ItemStack legs = player.getCurrentArmor(2);
                ItemStack boots = player.getCurrentArmor(1);
                if (helmet.getItem() == ModItems.aelitaHelmet && chest.getItem() == ModItems.aelitaChest && legs.getItem() == ModItems.aelitaLegs && boots.getItem() == ModItems.aelitaBoots)
                {
                    if (stack.getItem() != ModItems.energyField)
                        player.inventory.setInventorySlotContents(slot, null);
                }
                else if (helmet.getItem() == ModItems.oddHelmet && chest.getItem() == ModItems.oddChest && legs.getItem() == ModItems.oddLegs && boots.getItem() == ModItems.oddBoots)
                {
                    if (stack.getItem() != ModItems.glove)
                        player.inventory.setInventorySlotContents(slot, null);
                }
                else if (helmet.getItem() == ModItems.yumiHelmet && chest.getItem() == ModItems.yumiChest && legs.getItem() == ModItems.yumiLegs && boots.getItem() == ModItems.yumiBoots)
                {
                    if (stack.getItem() != ModItems.fan)
                        player.inventory.setInventorySlotContents(slot, null);
                }
                else if (helmet.getItem() == ModItems.ulrichHelmet && chest.getItem() == ModItems.ulrichChest && legs.getItem() == ModItems.ulrichLegs && boots.getItem() == ModItems.ulrichBoots)
                {
                    if (stack.getItem() != ModItems.katana)
                        player.inventory.setInventorySlotContents(slot, null);
                }
                else if (helmet.getItem() == ModItems.williamHelmet && chest.getItem() == ModItems.williamChest && legs.getItem() == ModItems.williamLegs && boots.getItem() == ModItems.williamBoots)
                    if (stack.getItem() != ModItems.zweihander)
                        player.inventory.setInventorySlotContents(slot, null);
            }
            else if ((player.getCurrentArmor(4) == null || player.getCurrentArmor(3) == null || player.getCurrentArmor(2) == null || player.getCurrentArmor(1) == null) && player.capabilities.isCreativeMode == false)
                player.inventory.setInventorySlotContents(slot, null);
        }
    }
    
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase ent, EntityLivingBase ent2)
    {
        if (!(ent instanceof EntityPlayer))
            this.weaponDamage = 34;
        else
        {
            EntityPlayer attackedPlayer = (EntityPlayer) ent;
            PlayerInformation pi = PlayerInformation.forPlayer(attackedPlayer);
            pi.decreaseLifePoints(10);
        }
        return true;
    }
    
    @Override
    public void registerIcons(IIconRegister iconRegister)
    {
        if (this == ModItems.katana)
            this.itemIcon = iconRegister.registerIcon("lyoko:katana");
        if (this == ModItems.zweihander)
            this.itemIcon = iconRegister.registerIcon("lyoko:zweihander");
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", this.weaponDamage, 0));
        return multimap;
    }
}
