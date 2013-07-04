package matt.lyoko.items;

import java.util.List;

import matt.lyoko.CodeLyoko;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemLyokoFuel extends ItemLyoko {

	public ItemLyokoFuel(int id, int maxEnergy, Item depletedItem) {
		super(id);
		this.setMaxDamage(maxEnergy);
        this.setCreativeTab(CodeLyoko.LyokoTabs);
        this.depletedForm = depletedItem;
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		if(this.itemID == ModItems.LeadCell.itemID)
			itemIcon = iconRegister.registerIcon("lyoko:leadcell");
		if(this.itemID == ModItems.UraniumCell.itemID)
			itemIcon = iconRegister.registerIcon("lyoko:uraniumcell");
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
