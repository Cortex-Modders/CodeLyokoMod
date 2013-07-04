package matt.lyoko.items;

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
		if(this.itemID == ModItems.DataFragment.itemID)
			itemIcon = iconRegister.registerIcon("lyoko:datafrag");
	}
}