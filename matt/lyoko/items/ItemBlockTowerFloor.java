package matt.lyoko.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlockTowerFloor extends ItemBlock
{
	public ItemBlockTowerFloor(int par1)
	{
		super(par1);
	}
	
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		boolean result = super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
		if(result && par2EntityPlayer.isSneaking())
		{
			par3World.setBlockMetadataWithNotify(par4, par5, par6, 1, 2);
		}
		return result;
	}
}