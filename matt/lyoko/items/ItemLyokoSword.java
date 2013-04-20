package matt.lyoko.items;

import matt.lyoko.CodeLyoko;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class ItemLyokoSword extends ItemSword
{
	public ItemLyokoSword(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1, par2EnumToolMaterial);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
	}
	
	private int life = 10;
	
	public void onUpdate(ItemStack stack, World world, Entity ent, int slot, boolean par5)
	{
		if(ent instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)ent;
			if(life > 0)
			{
				life--;
			}
			else
			{
				player.inventory.setInventorySlotContents(slot, null);
				life = 10;
			}
		}
	}
	
	@Override
	public void updateIcons(IconRegister iconRegister)
	{
		if(this.itemID == CodeLyoko.Katana.itemID)
	         iconIndex = iconRegister.registerIcon("lyoko:katana");
		if(this.itemID == CodeLyoko.Zweihander.itemID)
	         iconIndex = iconRegister.registerIcon("lyoko:zweihander");
	}
}
