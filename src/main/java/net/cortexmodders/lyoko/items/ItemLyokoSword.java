/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
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
                if (helmet.getItem() == ModItems.AelitaHelmet && chest.getItem() == ModItems.AelitaChest && legs.getItem() == ModItems.AelitaLegs && boots.getItem() == ModItems.AelitaBoots)
                {
                    if (stack.getItem() != ModItems.EnergyField)
                        player.inventory.setInventorySlotContents(slot, null);
                } else if (helmet.getItem() == ModItems.OddHelmet && chest.getItem() == ModItems.OddChest && legs.getItem() == ModItems.OddLegs && boots.getItem() == ModItems.OddBoots)
                {
                    if (stack.getItem() != ModItems.Glove)
                        player.inventory.setInventorySlotContents(slot, null);
                } else if (helmet.getItem() == ModItems.YumiHelmet && chest.getItem() == ModItems.YumiChest && legs.getItem() == ModItems.YumiLegs && boots.getItem() == ModItems.YumiBoots)
                {
                    if (stack.getItem() != ModItems.Fan)
                        player.inventory.setInventorySlotContents(slot, null);
                } else if (helmet.getItem() == ModItems.UlrichHelmet && chest.getItem() == ModItems.UlrichChest && legs.getItem() == ModItems.UlrichLegs && boots.getItem() == ModItems.UlrichBoots)
                {
                    if (stack.getItem() != ModItems.Katana)
                        player.inventory.setInventorySlotContents(slot, null);
                } else if (helmet.getItem() == ModItems.WilliamHelmet && chest.getItem() == ModItems.WilliamChest && legs.getItem() == ModItems.WilliamLegs && boots.getItem() == ModItems.WilliamBoots)
                    if (stack.getItem() != ModItems.Zweihander)
                        player.inventory.setInventorySlotContents(slot, null);
            } else if ((player.getCurrentArmor(4) == null || player.getCurrentArmor(3) == null || player.getCurrentArmor(2) == null || player.getCurrentArmor(1) == null) && player.capabilities.isCreativeMode == false)
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
        if (this == ModItems.Katana)
            this.itemIcon = iconRegister.registerIcon("lyoko:katana");
        if (this == ModItems.Zweihander)
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
