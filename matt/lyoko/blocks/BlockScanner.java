package matt.lyoko.blocks;

import java.util.List;

import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.tileentity.TileEntityScanner;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockScanner extends BlockContainer
{
    public BlockScanner(int par1)
	{
		super(par1, Material.iron);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
		this.setBlockBounds(-0.25F, 0.0F, -0.25F, 1.25F, 5F, 1.25F);
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1)
	{
		return new TileEntityScanner();
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("lyoko:scanner");
	}
	
	@Override
    public int getRenderType()
    {
        return -1;
    }
	
	@Override
    public boolean isOpaqueCube()
    {
        return false;
    }
	
	public static boolean isMultiBlock(World world, int x, int y, int z)
    {
    	if(world.getBlockId(x, y, z) == CodeLyoko.Scanner.blockID
    			&& world.getBlockId(x, y + 1, z) == CodeLyoko.Scanner.blockID
    			&& world.getBlockId(x, y + 2, z) == CodeLyoko.Scanner.blockID
    			&& world.getBlockId(x, y + 3, z) == CodeLyoko.Scanner.blockID
    			&& world.getBlockId(x, y + 4, z) == CodeLyoko.Scanner.blockID)
    	{
    		return true;
    	}
    	return false;
    }
	
	/**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    @SuppressWarnings("rawtypes")
    @Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity entity)
    {
        float f = 0.125F;
        
        
        //Top
        this.setBlockBounds(-0.25F, 4.5F, -0.25F - f, 1.25F, 5F, 1.25F);
        super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
        //Left
        this.setBlockBounds(-0.25F, 0.0F, -0.25F, -f, 10.0F, 1.25F);
        super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
        //Back
        this.setBlockBounds(-0.25F, 0.0F, 0.0F, 1.25F, 5.0F, -f);
        super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
        //Right
        this.setBlockBounds(1.25F - f, 0.0F, -0.25F, 1.25F, 5.0F, 1.25F);
        super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
        //Bottom
        this.setBlockBounds(-0.25F, 0.0F, -0.25F, 1.25F, 0.5F, 1.25F);
        super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
        
        this.setBlockBoundsForItemRender();
        
    }
    
	public void activatePortal(World world, int x, int y, int z)
	{
		TileEntityScanner tile = (TileEntityScanner)world.getBlockTileEntity(x, y, z);
		if(tile != null)
		{
			int portal;
			switch(tile.sector)
			{
			case 0:
				portal = CodeLyoko.LyokoPolarPortal.blockID;
				break;
			case 1:
				portal = CodeLyoko.LyokoDesertPortal.blockID;
				break;
			case 2:
				portal = CodeLyoko.LyokoForestPortal.blockID;
				break;
			case 3:
				portal = CodeLyoko.LyokoMountainPortal.blockID;
				break;
			case 4:
				portal = CodeLyoko.LyokoCarthagePortal.blockID;
				break;
			default:
				portal = 0;
			}
			world.setBlock(x, y + 1, z, portal);
			world.setBlock(x, y + 2, z, portal);
			world.setBlock(x, y + 3, z, portal);
			tile.sector = -1;
		}
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are)
    {
		TileEntity getTile = world.getBlockTileEntity(x, y, z);
		if(!(getTile instanceof TileEntityScanner)) return false;
		TileEntityScanner tileEntity = (TileEntityScanner) getTile;
		if (tileEntity == null || player.isSneaking())
		{
			return false;
		}
		else
		{   
			if(!tileEntity.doorsOpen)
			{
				tileEntity.doorsOpen = true;
			}
			else
			{
				tileEntity.doorsOpen = false;
				world.playSoundEffect(x+0.5F, y+0.5F, z+0.5F, "assets.lyoko.sounds.scannerClose", 0.9F, world.rand.nextFloat() * 0.1F + 0.9F);
			}
			for(int i = -4; i < 1; i++)
			{
				if(BlockScanner.isMultiBlock(world, x, y+i, z))
				{
					activatePortal(world, x, y+i, z);
					return true;
				}
			}
		}
		return false;
    }
	
	@Override
    public void onBlockPlacedBy(World par1World, int x, int y, int z, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack)
    {
        super.onBlockPlacedBy(par1World, x, y, z, par5EntityLiving, par6ItemStack);
        int l = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            par1World.setBlockMetadataWithNotify(x, y, z, 0, 2);
        }

        if (l == 1)
        {
            par1World.setBlockMetadataWithNotify(x, y, z, 1, 2);
        }

        if (l == 2)
        {
            par1World.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 3)
        {
            par1World.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }
    }
}