package matt.lyoko.world.portals;

import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.BlockBreakable;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.Teleporter;
import net.minecraft.src.World;
import matt.lyoko.world.LyokoCarthageSector;

public class OverworldPortal extends BlockBreakable{
	
	
	public OverworldPortal(int i, int j) {
		super(i, j, Material.portal, true);
		 this.setCreativeTab(CreativeTabs.tabMisc);
		 this.setBlockName("Overworld Portal");
		 
	}

	public String getTextureFile()
    {
            return "/lyoko/terrain/terrain.png";
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
    
    public Teleporter getTeleporter()
    {
        return new OverworldTeleporter();
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
	
	 public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity entity)
	    {
		 if((entity instanceof EntityPlayerMP))
         {
         EntityPlayerMP playerMP = (EntityPlayerMP)entity;

                                         if(entity.ridingEntity == null && entity.riddenByEntity == null && entity instanceof EntityPlayer)
                                         {
                                                                         if(ModLoader.getMinecraftInstance().thePlayer.timeUntilPortal == 0)
                                                                         {
                                                                          
                                                                          OverworldTeleporter teleporter = new OverworldTeleporter();
                                                                          
                                                                                                     if(ModLoader.getMinecraftInstance().thePlayer.dimension != 65)
                                                                                                         {
                                                                                                         ModLoader.getMinecraftInstance().thePlayer.timeUntilPortal = 30;
                                                                                                          playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, 0, teleporter);
                                                                                                         }
                                                                                                         else
                                                                                                         {
                                                                                                         ModLoader.getMinecraftInstance().thePlayer.timeUntilPortal = 30;
                                                                                                         playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, 0, teleporter);
                                                                                                         }
                                                                         }
                                                                         else{
                                                                                                         ModLoader.getMinecraftInstance().thePlayer.timeUntilPortal = 30;
                                                                         }

                                         }

         }
	 }

}
