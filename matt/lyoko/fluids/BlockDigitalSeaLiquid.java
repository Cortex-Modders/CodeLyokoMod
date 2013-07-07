package matt.lyoko.fluids;

import matt.lyoko.CodeLyoko;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;

public class BlockDigitalSeaLiquid extends BlockFluidClassic
{
	public BlockDigitalSeaLiquid(int i, Material material)
	{
		super(i, ModFluids.digitalSea, material);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = Block.waterMoving.getIcon(0, 0);
	}
	
	@Override
	public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k)
    {
    	return 0x0000FF;
    }
	
	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        par5Entity.attackEntityFrom(DamageSource.generic, 100);
    }
}