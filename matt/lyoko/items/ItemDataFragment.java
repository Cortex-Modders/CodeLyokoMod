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
	public void updateIcons(IconRegister iconRegister)
	{
		if(this.itemID == CodeLyoko.DataFragment.itemID)
	         iconIndex = iconRegister.registerIcon("lyoko:datafrag");
	}
}
