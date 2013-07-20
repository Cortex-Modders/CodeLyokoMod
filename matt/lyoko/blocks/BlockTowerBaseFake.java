package matt.lyoko.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.tileentity.TileEntityTower;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockTowerBaseFake extends BlockContainer
{
	public BlockTowerBaseFake(int par1)
	{
		super(par1, Material.iron);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
	}
	
	Icon inside;
    
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("lyoko:towerbase");
        inside = par1IconRegister.registerIcon("lyoko:blank");
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIcon(int side, int meta)
    {
    	if(side == 2 && meta == 0)
    	{
    		return inside;
    	}
    	else if(side == 3 && meta == 2)
    	{
    		return inside;
    	}
    	else if(side == 4 && meta == 3)
    	{
    		return inside;
    	}
    	else if(side == 5 && meta == 1)
    	{
    		return inside;
    	}
    	return this.blockIcon;
    }
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityTower();
	}
	
    @Override
    public void onBlockPlacedBy(World par1World, int x, int y, int z, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack)
    {
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