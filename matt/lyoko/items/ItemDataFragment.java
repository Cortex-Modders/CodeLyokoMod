package matt.lyoko.items;

import matt.lyoko.CodeLyoko;
import net.minecraft.client.renderer.texture.IconRegister;

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
		    this.itemIcon = iconRegister.registerIcon("lyoko:datafrag");
	}
}
