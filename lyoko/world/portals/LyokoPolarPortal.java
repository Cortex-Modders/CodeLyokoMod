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
import matt.lyoko.world.LyokoPolarSector;

public class LyokoPolarPortal extends BlockPortal {

	public LyokoPolarPortal(int i, int j) {
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
	    
	    public LyokoPolarSector getDimension()
	 {
	  return new LyokoPolarSector();
	 }
	    
	    public Teleporter getTeleporter()
	 {
	  return new LyokoPolarTeleporter();
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
	        return true;
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
	        return 1;
	    }

}
