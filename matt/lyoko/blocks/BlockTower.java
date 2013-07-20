package matt.lyoko.blocks;

import java.util.Random;

import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.tileentity.TileEntityTower;
import matt.lyoko.items.ModItems;
import matt.lyoko.particles.LyokoParticleEffects;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTower extends BlockContainer
{
    public BlockTower(int par1)
    {
        super(par1, Material.iron);
        this.setCreativeTab(CodeLyoko.LyokoTabs);
    }
    
    Icon inside;
    
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("lyoko:tower");
        inside = par1IconRegister.registerIcon("lyoko:blank");
    }

    @Override
    public TileEntity createNewTileEntity(World var1) {
        return new TileEntityTower();
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

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
		TileEntityTower tet = (TileEntityTower) world.getBlockTileEntity(x, y, z);

		if(!world.isRemote)
		{
			if(player.getHeldItem() != null && player.getHeldItem().getItem() == ModItems.LaserArrow)
			{
				tet.owner = "reset";
				world.markBlockForUpdate(x, y, z);
				return true;
			}
		}
		return false;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        TileEntityTower tet = (TileEntityTower) world.getBlockTileEntity(x, y, z);
        
        int meta = world.getBlockMetadata(x, y, z);
        
        Random var5 = world.rand;
        double var6 = 0.0625D;

        for (int var8 = 0; var8 < 6; ++var8)
        {
            double var9 = (double)((float)x + var5.nextFloat());
            double var11 = (double)((float)y + var5.nextFloat());
            double var13 = (double)((float)z + var5.nextFloat());

            if (var8 == 0 && !world.isBlockOpaqueCube(x, y + 1, z))
            {
                var11 = (double)(y + 1) + var6;
            }

            if (var8 == 1 && !world.isBlockOpaqueCube(x, y - 1, z))
            {
                var11 = (double)(y + 0) - var6;
            }

            if (var8 == 2 && !world.isBlockOpaqueCube(x, y, z + 1) && meta != 2)
            {
                var13 = (double)(z + 1) + var6;
            }

            if (var8 == 3 && !world.isBlockOpaqueCube(x, y, z - 1) && meta != 0)
            {
                var13 = (double)(z + 0) - var6;
            }

            if (var8 == 4 && !world.isBlockOpaqueCube(x + 1, y, z) && meta != 1)
            {
                var9 = (double)(x + 1) + var6;
            }

            if (var8 == 5 && !world.isBlockOpaqueCube(x - 1, y, z) && meta != 3)
            {
                var9 = (double)(x + 0) - var6;
            }

            if (var9 < (double)x || var9 > (double)(x + 1) || var11 < 0.0D || var11 > (double)(y + 1) || var13 < (double)z || var13 > (double)(z + 1))
            {
                if(tet.owner.equals("xana"))
                {
                    LyokoParticleEffects.spawnParticle("xana", var9, var11, var13, 0.0D, 0.0D, 0.0D);
                }
                else if(tet.owner.equals("lyoko"))
                {
                    LyokoParticleEffects.spawnParticle("lyoko", var9, var11, var13, 0.0D, 0.0D, 0.0D);
                }
                else if(tet.owner.equals("none"))
                {
                    LyokoParticleEffects.spawnParticle("deactivated", var9, var11, var13, 0.0D, 0.0D, 0.0D);
                }
                else if(tet.owner.equals("developer"))
                {
                    LyokoParticleEffects.spawnParticle("dev", var9, var11, var13, 0.0D, 0.0D, 0.0D);
                }
            }
        }
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