package matt.lyoko.blocks;

import java.util.Random;

import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.mobs.EntityLyoko;
import matt.lyoko.entities.tileentity.TileEntityMarabounta;
import matt.lyoko.entities.vehicles.EntityVehicle;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMarabounta extends BlockContainer
{
	public BlockMarabounta(int par1)
	{
		super(par1, Material.iron);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
		this.setTickRandomly(true);
	}
	
	private Icon normalTexture;
	private Icon evilTexture;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		normalTexture = par1IconRegister.registerIcon("lyoko:marabounta");
		evilTexture = par1IconRegister.registerIcon("lyoko:evilmarabounta");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
	{
        switch (meta)
        {
        case 0:
        	return normalTexture;
        case 1:
        	return evilTexture;
        default:
        	return normalTexture;
        }
    }
	
	//@Override
	//public int idDropped(int par1, Random par1Random, int par2)
	//{
	//	return 0;
	//}
	
	@Override
	public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {
		TileEntityMarabounta tem = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);
		
		if(tem.consumedBlock == 0)
		{
			return world.setBlockToAir(x, y, z);
		}
		
        return world.setBlock(x, y, z, tem.consumedBlock);
    }
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		convertLyokoBlocks(world, x+1, y, z);
		convertLyokoBlocks(world, x-1, y, z);
		convertLyokoBlocks(world, x, y+1, z);
		convertLyokoBlocks(world, x, y-1, z);
		convertLyokoBlocks(world, x, y, z+1);
		convertLyokoBlocks(world, x, y, z-1);
	}
	
	public void convertLyokoBlocks(World world, int x, int y, int z)
	{
		TileEntityMarabounta tem;
		
		if(world.getBlockId(x, y, z) == CodeLyoko.LyokoLog.blockID)
		{
			world.setBlock(x, y, z, blockID);
			tem = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);
			tem.consumedBlock = CodeLyoko.LyokoLog.blockID;
		}
		else if(world.getBlockId(x, y, z) == CodeLyoko.LyokoIce.blockID)
		{
			world.setBlock(x, y, z, blockID);
			tem = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);
			tem.consumedBlock = CodeLyoko.LyokoIce.blockID;
		}
		else if(world.getBlockId(x, y, z) == CodeLyoko.LyokoGrass.blockID)
		{
			world.setBlock(x, y, z, blockID);
			tem = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);
			tem.consumedBlock = CodeLyoko.LyokoGrass.blockID;
		}
		else if(world.getBlockId(x, y, z) == CodeLyoko.LyokoSand.blockID)
		{
			world.setBlock(x, y, z, blockID);
			tem = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);
			tem.consumedBlock = CodeLyoko.LyokoSand.blockID;
		}
		else if(world.getBlockId(x, y, z) == CodeLyoko.LyokoStone.blockID)
		{
			world.setBlock(x, y, z, blockID);
			tem = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);
			tem.consumedBlock = CodeLyoko.LyokoStone.blockID;
		}
		else if(world.getBlockId(x, y, z) == CodeLyoko.LyokoCarthage.blockID)
		{
			world.setBlock(x, y, z, blockID);
			tem = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);
			tem.consumedBlock = CodeLyoko.LyokoCarthage.blockID;
		}
		else if(world.getBlockId(x, y, z) == CodeLyoko.VirtualBlock.blockID)
		{
			world.setBlock(x, y, z, blockID);
			tem = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);
			tem.consumedBlock = CodeLyoko.VirtualBlock.blockID;
		}
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        float f = 0.125F;
        return AxisAlignedBB.getBoundingBox((float)(par2 - 1) + f, (float)(par3 - 1) + f, (float)(par4 - 1) + f, (float)(par2 + 1) - f, (float)(par3 + 1) - f, (float)(par4 + 1) - f);
    }
	
	@Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity ent)
    {
		TileEntityMarabounta tem = ((TileEntityMarabounta)world.getBlockTileEntity(x, y, z));
    	if(ent instanceof EntityLyoko)
    	{
    		ent.attackEntityFrom(DamageSource.generic, 100);
    	}
    	else if(ent instanceof EntityVehicle && world.getBlockMetadata(x, y, z) == 1)
    	{
    		((EntityVehicle)ent).setDead();
    	}
    	else if(ent instanceof EntityPlayer && world.getBlockMetadata(x, y, z) == 1)
    	{
    		if(!((EntityPlayer)ent).capabilities.isCreativeMode)
    		{
    			ent.attackEntityFrom(DamageSource.generic, 9);
    		}
    	}
    }
	
	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
	{
		super.onBlockClicked(world, x, y, z, player);
		TileEntityMarabounta temp = (TileEntityMarabounta) world.getBlockTileEntity(x, y, z);
		world.setBlockMetadataWithNotify(x, y, z, 1, 2);
		world.setBlockTileEntity(x, y, z, temp);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityMarabounta();
	}
}