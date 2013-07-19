package matt.lyoko.blocks;

import matt.lyoko.CodeLyoko;
import matt.lyoko.lib.LyokoDamageSource;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockDigitalSeaLiquid extends BlockFluidClassic
{
	public BlockDigitalSeaLiquid(int i, Fluid fluid, Material material)
	{
		super(i, fluid, material);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
	}
	
	@Override
	public Icon getIcon(int side, int meta)
	{
		return Block.waterMoving.getIcon(side, meta);
		
	}
	
	@Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		return maxScaledLight;
    }
	
	@Override
	public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k)
    {
    	return 0x0000FF;
    }
	
	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        par5Entity.attackEntityFrom(LyokoDamageSource.digitalSea, 100);
    }
}