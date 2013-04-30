package matt.lyoko.blocks;

import cpw.mods.fml.relauncher.*;
import java.util.Random;

import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.TileEntityTowerConsole;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.*;

public class BlockTowerConsole extends BlockContainer
{
    public BlockTowerConsole(int par1)
    {
        super(par1, Material.iron);
        this.setCreativeTab(CodeLyoko.LyokoTabs);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
    	blockIcon = par1IconRegister.registerIcon("lyoko:towerconsole");
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
    	TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
    	if (tileEntity == null || player.isSneaking())
    	{
    		return false;
    	}
    	else
    	{
    		//TODO uncomment this after gui is testable
			player.openGui(CodeLyoko.instance, 1, world, x, y, z);
			return true;
    	}
    }

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityTowerConsole();
	}
}
