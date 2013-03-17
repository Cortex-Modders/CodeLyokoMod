package matt.lyoko.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.src.*;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import java.util.Random;
import matt.lyoko.*;

public class BlockTowerBase extends BlockBreakable
{
	public BlockTowerBase(int par1, String par2, boolean flag)
	{
		super(par1, par2, Material.iron, flag);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
	}
	
	@Override
	public void func_94332_a(IconRegister par1IconRegister)
	{
		this.field_94336_cN = par1IconRegister.func_94245_a("lyoko:towerbase");
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
    	return null;
    }
	
	public int quantityDropped(Random par1Random)
	{
		return 1;
	}
	
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public boolean isOpaqueCube()
    {
        return false;
    }
}