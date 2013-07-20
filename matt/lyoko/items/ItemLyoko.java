package matt.lyoko.items;

import java.util.List;

import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.vehicles.EntityOverboard;
import matt.lyoko.lib.ThreadTowerGen;
import matt.lyoko.world.StructureTower;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemLyoko extends Item
{
	public ItemLyoko(int par1) {
		super(par1);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean B)
	{
		if(stack.getItem().equals(ModItems.LaserArrow))
		{
			list.add("This is the mod's equivalent of an infinite battery.");
			list.add("Use this as a fuel cell in the Super Computer.");
			list.add("This is also the debug tool for the mod as well.");
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if(stack.getItem().equals(ModItems.LaserArrow))
		{
			if(player.isSneaking())
			{
				Item item;
				int index = CodeLyoko.debugTools.indexOf(this);
				if(CodeLyoko.debugTools.size() == index + 1)
				{
					item = CodeLyoko.debugTools.get(0);
				}
				else
				{
					item = CodeLyoko.debugTools.get(index + 1);
				}
				stack = new ItemStack(item, stack.stackSize);
			}
			else if(CodeLyoko.entityInLyoko(player))
			{
				float f = 1.0F;
				float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
				float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
				double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
				double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double)f + 1.62D - (double)player.yOffset;
				double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
				Vec3 vec3 = world.getWorldVec3Pool().getVecFromPool(d0, d1, d2);
				float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
				float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
				float f5 = -MathHelper.cos(-f1 * 0.017453292F);
				float f6 = MathHelper.sin(-f1 * 0.017453292F);
				float f7 = f4 * f5;
				float f8 = f3 * f5;
				double d3 = 5.0D;
				Vec3 vec31 = vec3.addVector((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);
				MovingObjectPosition movingobjectposition = world.clip(vec3, vec31, true);
				
				if (movingobjectposition == null)
				{
					return stack;
				}
				else
				{
					Vec3 vec32 = player.getLook(f);
					boolean flag = false;
					float f9 = 1.0F;
					List list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(vec32.xCoord * d3, vec32.yCoord * d3, vec32.zCoord * d3).expand((double)f9, (double)f9, (double)f9));
					int i;
					
					for (i = 0; i < list.size(); ++i)
					{
						Entity entity = (Entity)list.get(i);
						
						if (entity.canBeCollidedWith())
						{
							float f10 = entity.getCollisionBorderSize();
							AxisAlignedBB axisalignedbb = entity.boundingBox.expand((double)f10, (double)f10, (double)f10);
							
							if (axisalignedbb.isVecInside(vec3))
							{
								flag = true;
							}
						}
					}
					
					if (flag)
					{
						return stack;
					}
					else
					{
						if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE)
						{
							i = movingobjectposition.blockX;
							int j = movingobjectposition.blockY;
							int k = movingobjectposition.blockZ;
							
							if (world.getBlockId(i, j, k) == Block.snow.blockID)
							{
								--j;
							}
							
							(new StructureTower()).generate(world, itemRand, i, j, k);
							
							if (!player.capabilities.isCreativeMode)
							{
								--stack.stackSize;
							}
						}
						return stack;
					}
				}
			}
		}
		return stack;
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		if(this.itemID == ModItems.LaserArrow.itemID)
			itemIcon = iconRegister.registerIcon("lyoko:laserarrow");
		if(this.itemID == ModItems.QuantumOrb.itemID)
			itemIcon = iconRegister.registerIcon("lyoko:quantumorb");
		if(this.itemID == ModItems.Lead.itemID)
			itemIcon = iconRegister.registerIcon("lyoko:leadingot");
		if(this.itemID == ModItems.EmptyCell.itemID)
			itemIcon = iconRegister.registerIcon("lyoko:emptycell");
		if(this.itemID == ModItems.DepletedLeadCell.itemID)
			itemIcon = iconRegister.registerIcon("lyoko:depletedleadcell");
		if(this.itemID == ModItems.Uranium.itemID)
			itemIcon = iconRegister.registerIcon("lyoko:uraniumingot");
		if(this.itemID == ModItems.DepletedUraniumCell.itemID)
			itemIcon = iconRegister.registerIcon("lyoko:depleteduraniumcell");
		if(this.itemID == ModItems.QuantumMatrix.itemID)
			itemIcon = iconRegister.registerIcon("lyoko:quantummatrix");
		if(this.itemID == ModItems.QuantumContainmentCell.itemID)
			itemIcon = iconRegister.registerIcon("lyoko:quantumcontainmentcell");
	}
	
	public void onUpdate(ItemStack stack, World world, Entity ent, int par4, boolean par5)
	{
		
		if(stack.getItem() == ModItems.Lead || stack.getItem() == ModItems.Uranium)
		{
			if(ent instanceof EntityPlayer)
			{
				if(!((EntityPlayer)ent).capabilities.isCreativeMode)
				{
					((EntityPlayer)ent).addPotionEffect((new PotionEffect(Potion.hunger.getId(), 100, 0)));
					((EntityPlayer)ent).addPotionEffect((new PotionEffect(Potion.poison.getId(), 100, 0)));
				}
			}
			else
			{
				((EntityLiving)ent).addPotionEffect((new PotionEffect(Potion.hunger.getId(), 100, 0)));
				((EntityLiving)ent).addPotionEffect((new PotionEffect(Potion.poison.getId(), 100, 0)));
			}
		}
		else if(stack.getItem() == ModItems.QuantumOrb)
		{
			if(ent instanceof EntityLiving)
			{
				((EntityLiving)ent).clearActivePotions();
				((EntityLiving)ent).addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 100, 0));
			}
		}
	}
}
