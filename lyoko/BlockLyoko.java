package matt.lyoko;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.src.*;

import java.util.Random;

public class BlockLyoko extends Block
{
	public BlockLyoko(int par1, int par2)
	{
		super(par1, par2, Material.iron);
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
	
	public int quantityDropped(Random par1Random)
	{
		return 1;
	}
	
	public String getTextureFile()
    {
            return "/matt/lyoko/terrain/terrain.png";
    }
}