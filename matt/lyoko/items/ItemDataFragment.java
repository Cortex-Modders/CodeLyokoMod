package matt.lyoko.items;

import matt.lyoko.CodeLyoko;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemDataFragment extends ItemLyoko
{
	public ItemDataFragment(int par1)
	{
		super(par1);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		if(this.itemID == CodeLyoko.DataFragment.itemID)
			itemIcon = iconRegister.registerIcon("lyoko:datafrag");
	}
}
