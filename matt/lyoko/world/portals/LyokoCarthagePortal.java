package matt.lyoko.world.portals;

import java.util.Random;

import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.src.*;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import matt.lyoko.world.LyokoCarthageSector;

public class LyokoCarthagePortal extends BlockBreakable{
	
	
	public LyokoCarthagePortal(int i, int j) {
		super(i, j, Material.portal, true);
		 this.setCreativeTab(CreativeTabs.tabMisc);
		 this.setBlockName("Carthage Portal");
	}

	public String getTextureFile()
    {
            return "/lyoko/terrain/terrain.png";
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
    
    
    //Selects the World Provider
    public LyokoCarthageSector getDimension()
 {
  return new LyokoCarthageSector();
 }
    //Selects the Teleporter
    public Teleporter getTeleporter()
 {
  return new LyokoCarthageTeleporter();
 }
 
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }
    
    public boolean isOpaqueCube()
    {
            return false;
    }
    
    public int getRenderBlockPass()
    {
            return 1;
    }



    
   
    
    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity entity)
    {
if((entity instanceof EntityPlayerMP))
            {
            EntityPlayerMP playerMP = (EntityPlayerMP)entity;

                                            if(entity.ridingEntity == null && entity.riddenByEntity == null && entity instanceof EntityPlayer)
                                            {
                                                                            if(ModLoader.getMinecraftInstance().thePlayer.timeUntilPortal == 0)
                                                                            {
                                                                             //Teleporter Class
                                                                             LyokoCarthageTeleporter teleporter = new LyokoCarthageTeleporter();
                                                                             
                                                                                                            if(ModLoader.getMinecraftInstance().thePlayer.dimension != 65)
                                                                                                            {
                                                                                                            //Transfer player to Dimension
                                                                                      ModLoader.getMinecraftInstance().thePlayer.timeUntilPortal = 10;
                                                                                      playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, 7, teleporter);
                                                                                     }
                                                                                     else
                                                                                     {
                                                                                     ModLoader.getMinecraftInstance().thePlayer.timeUntilPortal = 10;
                                                                                     playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, 7, teleporter);
                                                                               }
                                                                            }
                                                                            else{
                                                                                 ModLoader.getMinecraftInstance().thePlayer.timeUntilPortal = 10;
                                                                            }

                                            }

            }
    }
}
