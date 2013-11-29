/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.items;

import matt.lyoko.CodeLyoko;
import matt.lyoko.lib.PlayerInformation;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

import com.google.common.collect.Multimap;

public class ItemLyokoSword extends ItemSword
{
    private int weaponDamage;

    public ItemLyokoSword(int par1, EnumToolMaterial par2EnumToolMaterial)
    {
        super(par1, par2EnumToolMaterial);
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

            if (player.getCurrentItemOrArmor(4) != null && player.getCurrentItemOrArmor(3) != null && player.getCurrentItemOrArmor(2) != null && player.getCurrentItemOrArmor(1) != null)
            {
                ItemStack helmet = player.getCurrentItemOrArmor(4);
                ItemStack chest = player.getCurrentItemOrArmor(3);
                ItemStack legs = player.getCurrentItemOrArmor(2);
                ItemStack boots = player.getCurrentItemOrArmor(1);
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
            } else if ((player.getCurrentItemOrArmor(4) == null || player.getCurrentItemOrArmor(3) == null || player.getCurrentItemOrArmor(2) == null || player.getCurrentItemOrArmor(1) == null) && player.capabilities.isCreativeMode == false)
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
    public void registerIcons(IconRegister iconRegister)
    {
        if (this.itemID == ModItems.Katana.itemID)
            this.itemIcon = iconRegister.registerIcon("lyoko:katana");
        if (this.itemID == ModItems.Zweihander.itemID)
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