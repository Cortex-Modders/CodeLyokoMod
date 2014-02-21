/*
 * Code Lyoko Mod for Minecraft v@VERSION
 * Copyright 2013 Cortex Modders, Matthew Warren, Jacob Rhoda, and
 * other contributors.
 * Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.items;

import java.util.List;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.entities.mobs.EntityLyoko;
import net.cortexmodders.lyoko.entities.mobs.EntitySpecter;
import net.cortexmodders.lyoko.entities.mobs.EntityXanafiedMob;
import net.cortexmodders.lyoko.world.StructureTower;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemLyoko extends Item
{
    public ItemLyoko()
    {
        this.setCreativeTab(CodeLyoko.LyokoTabs);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean B)
    {
        if (stack.getItem().equals(ModItems.laserArrow))
        {
            list.add("This is the mod's equivalent of an");
            list.add("infinite battery. Use this as a fuel");
            list.add("cell in the Super Computer. This is");
            list.add("also the debug tool for the mod as");
            list.add("well.");
        }
    }

    @SuppressWarnings("rawtypes")
	@Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (stack.getItem().equals(ModItems.laserArrow))
            if (player.isSneaking())
            {
                Item item;
                int index = CodeLyoko.debugTools.indexOf(this);
                if (CodeLyoko.debugTools.size() == index + 1)
                    item = CodeLyoko.debugTools.get(0);
                else
                    item = CodeLyoko.debugTools.get(index + 1);
                stack = new ItemStack(item, stack.stackSize);
            } else if (CodeLyoko.entityInLyoko(player))
            {
                float f = 1.0F;
                float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
                float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
                double d0 = player.prevPosX + (player.posX - player.prevPosX) * f;
                double d1 = player.prevPosY + (player.posY - player.prevPosY) * f + 1.62D - player.yOffset;
                double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * f;
                Vec3 vec3 = world.getWorldVec3Pool().getVecFromPool(d0, d1, d2);
                float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
                float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
                float f5 = -MathHelper.cos(-f1 * 0.017453292F);
                float f6 = MathHelper.sin(-f1 * 0.017453292F);
                float f7 = f4 * f5;
                float f8 = f3 * f5;
                double d3 = 5.0D;
                Vec3 vec31 = vec3.addVector(f7 * d3, f6 * d3, f8 * d3);
                MovingObjectPosition movingobjectposition = world.rayTraceBlocks(vec3, vec31, true);

                if (movingobjectposition == null)
                    return stack;
                else
                {
                    Vec3 vec32 = player.getLook(f);
                    boolean flag = false;
                    float f9 = 1.0F;
                    List list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(vec32.xCoord * d3, vec32.yCoord * d3, vec32.zCoord * d3).expand(f9, f9, f9));
                    int i;

                    for (i = 0; i < list.size(); ++i)
                    {
                        Entity entity = (Entity) list.get(i);

                        if (entity.canBeCollidedWith())
                        {
                            float f10 = entity.getCollisionBorderSize();
                            AxisAlignedBB axisalignedbb = entity.boundingBox.expand(f10, f10, f10);

                            if (axisalignedbb.isVecInside(vec3))
                                flag = true;
                        }
                    }

                    if (flag)
                        return stack;
                    else
                    {
                        if (movingobjectposition.typeOfHit == MovingObjectType.BLOCK)
                        {
                            i = movingobjectposition.blockX;
                            int j = movingobjectposition.blockY;
                            int k = movingobjectposition.blockZ;

                            if (world.getBlock(i, j, k) == Block.getBlockFromName("snow"))
                                --j;

                            new StructureTower().generate(world, itemRand, i - 3, j, k - 3);

                            if (!player.capabilities.isCreativeMode)
                                --stack.stackSize;
                        }
                        return stack;
                    }
                }
            }
        return stack;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase ent, EntityLivingBase player)
    {
        if (stack.getItem().equals(ModItems.laserArrow) && !(ent instanceof EntityLyoko) && !(ent instanceof EntitySpecter))
        {
            EntityXanafiedMob xana = new EntityXanafiedMob(ent.worldObj, ent.posX, ent.posY, ent.posZ, ent);
            if (!player.worldObj.isRemote)
            {
                ent.worldObj.spawnEntityInWorld(xana);
                ent.setDead();
            }
        }
        return false;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister)
    {
        if (this.equals(ModItems.laserArrow))
            this.itemIcon = iconRegister.registerIcon("lyoko:laserarrow");
        if (this.equals(ModItems.quantumOrb))
            this.itemIcon = iconRegister.registerIcon("lyoko:quantumorb" + (!CodeLyoko.useHDTextures ? "_16_16" : ""));
        if (this.equals(ModItems.lead))
            this.itemIcon = iconRegister.registerIcon("lyoko:leadingot");
        if (this.equals(ModItems.emptyCell))
            this.itemIcon = iconRegister.registerIcon("lyoko:emptycell");
        if (this.equals(ModItems.depletedLeadCell))
            this.itemIcon = iconRegister.registerIcon("lyoko:depletedleadcell");
        if (this.equals(ModItems.uranium))
            this.itemIcon = iconRegister.registerIcon("lyoko:uraniumingot");
        if (this.equals(ModItems.depletedUraniumCell))
            this.itemIcon = iconRegister.registerIcon("lyoko:depleteduraniumcell");
        if (this.equals(ModItems.quantumMatrix))
            this.itemIcon = iconRegister.registerIcon("lyoko:quantummatrix" + (!CodeLyoko.useHDTextures ? "_16_16" : ""));
        if (this.equals(ModItems.quantumContainmentCell))
            this.itemIcon = iconRegister.registerIcon("lyoko:quantumcontainmentcell" + (!CodeLyoko.useHDTextures ? "_16_16" : ""));
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity ent, int par4, boolean par5)
    {

        if (stack.getItem() == ModItems.lead || stack.getItem() == ModItems.uranium)
        {
            if (ent instanceof EntityPlayer)
            {
                if (!((EntityPlayer) ent).capabilities.isCreativeMode)
                {
                    ((EntityPlayer) ent).addPotionEffect(new PotionEffect(Potion.hunger.getId(), 100, 0));
                    ((EntityPlayer) ent).addPotionEffect(new PotionEffect(Potion.poison.getId(), 100, 0));
                }
            } else
            {
                ((EntityLiving) ent).addPotionEffect(new PotionEffect(Potion.hunger.getId(), 100, 0));
                ((EntityLiving) ent).addPotionEffect(new PotionEffect(Potion.poison.getId(), 100, 0));
            }
        } else if (stack.getItem() == ModItems.quantumOrb)
            if (ent instanceof EntityLiving)
            {
                ((EntityLiving) ent).clearActivePotions();
                ((EntityLiving) ent).addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 100, 0));
            }
    }
}
