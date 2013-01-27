package matt.lyoko;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.src.*;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import java.util.Random;

public class BlockTowerBase extends BlockBreakable
{
	public BlockTowerBase(int par1, int par2, boolean flag)
	{
		super(par1, par2, Material.iron, flag);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
    	return null;
    }
	
	public int quantityDropped(Random par1Random)
	{
		return 1;
	}
	
	public String getTextureFile()
    {
            return "/matt/lyoko/terrain/terrain.png";
    }
	
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public boolean isOpaqueCube()
    {
        return false;
    }
}