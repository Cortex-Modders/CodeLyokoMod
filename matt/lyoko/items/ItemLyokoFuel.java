package matt.lyoko.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.world.World;
import matt.lyoko.*;

public class ItemLyokoFuel extends ItemLyoko {

	public ItemLyokoFuel(int id, int maxEnergy, Item depletedItem) {
		super(id);
		this.setMaxDamage(maxEnergy);
        this.setCreativeTab(CodeLyoko.LyokoTabs);
        this.depletedForm = depletedItem;
	}
	
	@Override
	public void updateIcons(IconRegister iconRegister)
	{
		if(this.itemID == CodeLyoko.LyokoLeadCell.itemID)
	         iconIndex = iconRegister.registerIcon("lyoko:leadcell");
		if(this.itemID == CodeLyoko.LyokoUraniumCell.itemID)
	         iconIndex = iconRegister.registerIcon("lyoko:uraniumcell");
	}
	
	public Item depletedForm;
	
	public Item getDepletedFuelItem()
	{
		return this.depletedForm;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean B)
	{
		list.add(Integer.toString((stack.getMaxDamage() - stack.getItemDamage())) + "/" + Integer.toString(stack.getMaxDamage()));
	}
	
	@Override
	public void onUpdate(ItemStack stack, World par2World, Entity par3Entity, int par4, boolean par5)
	{
		if(stack.getItemDamage() > stack.getMaxDamage())
		{
			stack.setItemDamage(stack.getMaxDamage());
		}
	}
}
