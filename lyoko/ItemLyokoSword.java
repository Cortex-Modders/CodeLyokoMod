package matt.lyoko;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;
import net.minecraft.src.*;

public class ItemLyokoSword extends ItemSword
{
	public ItemLyokoSword(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1, par2EnumToolMaterial);
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
	
	public String getTextureFile()
    {
            return "/matt/lyoko/gui/items.png";
    }
}
