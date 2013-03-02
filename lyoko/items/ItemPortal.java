package matt.lyoko.items;

import java.util.List;
import matt.lyoko.CodeLyoko;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.*;

public class ItemPortal extends ItemLyoko
{
	public ItemPortal(int par1)
	{
		super(par1);
		this.setItemName("LyokoPortal");
		this.hasSubtypes = true;
		this.maxStackSize = 1;
	}
	
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean B)
	{
		if(stack.getItemDamage() != 6)
		{
			list.add("Right click while holding this item to go to lyoko");
		}
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
		if(player instanceof EntityPlayerMP)
		{
			if(stack.getItemDamage() == 0)
			{
				EntityPlayerMP thePlayer = (EntityPlayerMP)player;
				if (player.dimension != CodeLyoko.Polar_Sector_ID)
				{
					thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, CodeLyoko.Polar_Sector_ID);
				}
				else
				{
					thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0);
				}
				
				player.timeUntilPortal = 40;
				stack = new ItemStack(this, 1, 5);
			}
			else if(stack.getItemDamage() == 1)
			{
				EntityPlayerMP thePlayer = (EntityPlayerMP)player;
				if (player.dimension != CodeLyoko.Mountain_Sector_ID)
				{
					thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, CodeLyoko.Mountain_Sector_ID);
				}
				else
				{
					thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0);
				}
				
				player.timeUntilPortal = 40;
				stack = new ItemStack(this, 1, 5);
			}
			else if(stack.getItemDamage() == 2)
			{
				EntityPlayerMP thePlayer = (EntityPlayerMP)player;
				if (player.dimension != CodeLyoko.Forest_Sector_ID)
				{
					thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, CodeLyoko.Forest_Sector_ID);
				}
				else
				{
					thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0);
				}
				
				player.timeUntilPortal = 40;
				stack = new ItemStack(this, 1, 5);
			}
			else if(stack.getItemDamage() == 3)
			{
				EntityPlayerMP thePlayer = (EntityPlayerMP)player;
				if (player.dimension != CodeLyoko.Desert_Sector_ID)
				{
					thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, CodeLyoko.Desert_Sector_ID);
				}
				else
				{
					thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0);
				}
				
				player.timeUntilPortal = 40;
				stack = new ItemStack(this, 1, 5);
			}
			else if(stack.getItemDamage() == 4)
			{
				EntityPlayerMP thePlayer = (EntityPlayerMP)player;
				if (player.dimension != CodeLyoko.Carthage_Sector_ID)
				{
					thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, CodeLyoko.Carthage_Sector_ID);
				}
				else
				{
					thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0);
				}
				
				player.timeUntilPortal = 40;
				stack = new ItemStack(this, 1, 5);
			}
			else if(stack.getItemDamage() == 5)
			{
				EntityPlayerMP thePlayer = (EntityPlayerMP)player;
				if (player.dimension != 0)
				{
					thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0);
				}
				
				player.timeUntilPortal = 40;
				stack = new ItemStack(this, 1, 6);
			}
		}
        return stack;
    }
	
	@SideOnly(Side.CLIENT) //Marks a method as client side only, typically for graphics and rendering
	public int getIconFromDamage(int i)
	{
		switch(i)
		{
		case 0: return 43;
		case 1: return 44;
		case 2: return 45;
		case 3: return 46;
		case 4: return 47;
		case 5: return 48;
		case 6: return 49;
		default: return 43;
		}
	}
	
	public String getItemNameIS(ItemStack stack)
	{
		switch(stack.getItemDamage())
		{
		case 0: return this.getItemName() + "Polar";
		case 1: return this.getItemName() + "Mountain";
		case 2: return this.getItemName() + "Forest";
		case 3: return this.getItemName() + "Desert";
		case 4: return this.getItemName() + "Carthage";
		case 5: return this.getItemName() + "Overworld";
		case 6: return this.getItemName() + "Disabled";
		default: return this.getItemName();
		}
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(int itemID, CreativeTabs tab, List itemList)
	{
		for(int i=0; i<7; i++)
		{
			itemList.add(new ItemStack(itemID, 1, i));
		}
	}
}