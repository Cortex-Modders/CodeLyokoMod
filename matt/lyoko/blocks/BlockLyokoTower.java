package matt.lyoko.blocks;

import java.util.Random;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import matt.lyoko.*;
import matt.lyoko.entities.*;
import matt.lyoko.items.ModItems;
import matt.lyoko.particles.LyokoParticleEffects;

public class BlockLyokoTower extends BlockContainer
{
	public BlockLyokoTower(int par1)
	{
		super(par1, Material.iron);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("lyoko:tower");
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityTower();
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
		TileEntityTower tet = (TileEntityTower) world.getBlockTileEntity(x, y, z);
		
		if(!world.isRemote)
		{
			if(player.getHeldItem() != null && player.getHeldItem().getItem() == ModItems.LaserArrow)
			{
				tet.owner = "reset";
			}
			else if(tet.owner.equals("none"))
			{
				tet.owner = "xana";
				for(int i = 0; i < CodeLyoko.getDevelopers().length; i++)
				{
					if(player.username.equals(CodeLyoko.getDevelopers()[i]))
					{
						tet.owner = "developer";
						player.sendChatToPlayer("Developer mode activated on Tower at: x=" + x + " y=" + y + " z=" + z);
					}
				}
			}
			else if(tet.owner.equals("developer"))
			{
				tet.owner = "xana";
			}
			else if(tet.owner.equals("xana"))
			{
				tet.owner = "lyoko";
			}
			else if(tet.owner.equals("lyoko"))
			{
				tet.owner = "none";
			}
			else
			{
				tet.owner = "none";
				System.err.println("ERROR: Invalid value detected for tower");
			}
	        world.markBlockForUpdate(x, y, z);
			return true;
		}
		return false;
    }
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
		TileEntityTower tet = (TileEntityTower) world.getBlockTileEntity(x, y, z);
		
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
            
            if (var8 == 2 && !world.isBlockOpaqueCube(x, y, z + 1))
            {
                var13 = (double)(z + 1) + var6;
            }
            
            if (var8 == 3 && !world.isBlockOpaqueCube(x, y, z - 1))
            {
                var13 = (double)(z + 0) - var6;
            }
            
            if (var8 == 4 && !world.isBlockOpaqueCube(x + 1, y, z))
            {
                var9 = (double)(x + 1) + var6;
            }
            
            if (var8 == 5 && !world.isBlockOpaqueCube(x - 1, y, z))
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
}