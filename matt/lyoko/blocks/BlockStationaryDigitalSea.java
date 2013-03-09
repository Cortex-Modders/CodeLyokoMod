package matt.lyoko.blocks;

import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.liquids.ILiquid;
import matt.lyoko.*;

public class BlockStationaryDigitalSea extends BlockStationary implements ILiquid {

	public BlockStationaryDigitalSea(int i, Material material) {
		super(i, material);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
		setHardness(100F);
		setLightOpacity(3);
	}
	
	public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k)
    {
    	return 0x0000FF;
    }
	
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        par5Entity.attackEntityFrom(DamageSource.generic, 100);
    }

	@Override
	public int getRenderType() {
		return 4;
	}
	
	@Override
	public int stillLiquidId() {
		return CodeLyoko.DigitalSeaStill.blockID;
	}

	@Override
	public boolean isMetaSensitive() {
		return false;
	}

	@Override
	public int stillLiquidMeta() {
		return 0;
	}

	@Override
	public boolean isBlockReplaceable(World world, int i, int j, int k) {
		return true;
	}

}