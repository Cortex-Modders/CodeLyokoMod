/**package matt.lyoko.world.portals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockPortal;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import matt.lyoko.world.LyokoDesertSector;

public class LyokoDesertPortal extends BlockPortal {
	
	public LyokoDesertPortal(int i, int j) {
		super(i, j);
		 this.setCreativeTab(CreativeTabs.tabMisc);
		 
	}

	public String getTextureFile()
    {
            return "/lyoko/terrain/terrain.png";
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
    
    public LyokoDesertSector getDimension()
 {
  return new LyokoDesertSector();
 }
    
    public Teleporter getTeleporter()
 {
  return new LyokoDesertTeleporter(null);
 }
    public List canTeleportFromDimension()
 {
  ArrayList list = new ArrayList();
  list.add(0);
  list.add(-1);
  return list;
 }
    
 public boolean displayPortalOverlay()
    {
        return false;
    }
 
 public int getOverlayTexture()
 {
  return blockIndexInTexture;
 }
 
 public String getEnteringMessage() 
 {
  return "Entering The Dimension.";
 }
 
 public String getLeavingMessage() 
 {
  return "Leaving The Dimension.";
 }
 
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }
    
    public int getPortalDelay() {
    	return 60;
    }
}
**/