package matt.lyoko;

import java.util.List;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;

public class ItemLyoko extends Item
{
	public ItemLyoko(int par1) {
		super(par1);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.canRepair = false;
	}
	
	public String getTextureFile()
    {
            return "/matt/lyoko/gui/items.png";
    }
}
