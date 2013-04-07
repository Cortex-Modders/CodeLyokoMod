package matt.lyoko.blocks;

import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.TileEntityVirtualBlock;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLyokoVirtual extends BlockContainer
{
	public BlockLyokoVirtual(int par1)
	{
		super(par1, Material.iron);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		if(world.provider.dimensionId != CodeLyoko.Polar_Sector_ID && world.provider.dimensionId != CodeLyoko.Mountain_Sector_ID
				&& world.provider.dimensionId != CodeLyoko.Forest_Sector_ID
				&& world.provider.dimensionId != CodeLyoko.Desert_Sector_ID
				&& world.provider.dimensionId != CodeLyoko.Carthage_Sector_ID)
		{
			world.setBlock(x, y, z, 0);
		}
	}
	
	public void onBlockPlacedBy(World world, int par2, int par3, int par4, EntityLiving ent)
	{
		if(ent instanceof EntityPlayer)
		{
			EntityPlayer entp = ((EntityPlayer)ent);
			entp.setEntityHealth(entp.getHealth() - 1);
		}
	}
	
	private Icon virtualGrass;
	private Icon virtualStone;
	private Icon virtualSand;
	private Icon virtualIce;
	private Icon virtualCarthage;
	
	@Override
	public Icon getBlockTexture(IBlockAccess access, int x, int y, int z, int side)
    {
		if(access.getBlockTileEntity(x, y, z).worldObj.provider.dimensionId == CodeLyoko.Forest_Sector_ID)
		{
			return virtualGrass;
		}
		else if(access.getBlockTileEntity(x, y, z).worldObj.provider.dimensionId == CodeLyoko.Mountain_Sector_ID)
		{
			return virtualStone;
		}
		else if(access.getBlockTileEntity(x, y, z).worldObj.provider.dimensionId == CodeLyoko.Desert_Sector_ID)
		{
			return virtualSand;
		}
		else if(access.getBlockTileEntity(x, y, z).worldObj.provider.dimensionId == CodeLyoko.Polar_Sector_ID)
		{
			return virtualIce;
		}
		else if(access.getBlockTileEntity(x, y, z).worldObj.provider.dimensionId == CodeLyoko.Carthage_Sector_ID)
		{
			return virtualCarthage;
		}
        return virtualGrass;
    }
	
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		virtualGrass = this.blockIcon = par1IconRegister.registerIcon("lyoko:lyokograss");
		virtualStone = par1IconRegister.registerIcon("lyoko:lyokostone");
		virtualSand  = par1IconRegister.registerIcon("lyoko:lyokosand");
		virtualIce = par1IconRegister.registerIcon("lyoko:lyokoice");
		virtualCarthage = par1IconRegister.registerIcon("lyoko:carthage");
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityVirtualBlock();
	}
}
