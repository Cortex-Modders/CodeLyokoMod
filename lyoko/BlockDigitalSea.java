package matt.lyoko;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.src.*;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.Random;

public class BlockDigitalSea extends Block
{
	public BlockDigitalSea(int par1, int par2)
	{
		super(par1, par2, Material.iron);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
	}
	
	public int quantityDropped(Random par1Random)
	{
		return 1;
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        float f = 0.125F;
        return AxisAlignedBB.getBoundingBox((float)(par2 - 1) + f, (float)(par3 - 1) + f, (float)(par4 - 1) + f, (float)(par2 + 1) - f, (float)(par3 + 1) - f, (float)(par4 + 1) - f);
    }

    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        par5Entity.attackEntityFrom(DamageSource.generic, 100);
    }
    
    public String getTextureFile()
    {
            return "/matt/lyoko/terrain/terrain.png";
    }
}