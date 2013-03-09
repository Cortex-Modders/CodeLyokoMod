package matt.lyoko.world.portals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.BlockPortal;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;
import net.minecraft.src.Teleporter;
import net.minecraft.src.World;
import matt.lyoko.world.LyokoMountainSector;

public class LyokoMountainPortal extends BlockPortal{
	
	public LyokoMountainPortal(int i, int j) {
		super(i, j);
		 this.setCreativeTab(CreativeTabs.tabMisc);
		 this.setBlockName("Mountain Portal");
		 this.setBlockUnbreakable();
	}

	  public String getTextureFile()
	    {
	            return "/lyoko/terrain/terrain.png";
	    }
	    
	    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	    {
	        return null;
	    }
	    
	    public LyokoMountainSector getDimension()
	 {
	  return new LyokoMountainSector();
	 }
	    
	    public Teleporter getTeleporter()
	 {
	  return new LyokoMountainTeleporter();
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
	  return "Entering The Forest Sector.";
	 }
	 
	 public String getLeavingMessage() 
	 {
	  return "Leaving The Forest Sector.";
	 }
	 
	    public int quantityDropped(Random par1Random)
	    {
	        return 0;
	    }
	    
	    public int getPortalDelay() {
			return 60;
	    }


}
