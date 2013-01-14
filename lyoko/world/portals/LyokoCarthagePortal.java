package matt.lyoko.world.portals;

import java.util.Random;

import matt.lyoko.CodeLyoko;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class LyokoCarthagePortal extends BlockPortal{
	
	
	public LyokoCarthagePortal(int id, int texture)
	 {
	  super(id, texture);
	  this.setCreativeTab(CodeLyoko.LyokoTabs);
	 }
	 
	 public String getTextureFile()
	    {
		 	return "/lyoko/terrain/terrain.png";
	    }
	 
	 public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	 {

	  }
	 
	 public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
	 {
	  if (par5Entity.ridingEntity == null && par5Entity.riddenByEntity == null)
	  {
	   if (par5Entity instanceof EntityPlayerMP)
	   {
	    EntityPlayerMP thePlayer = (EntityPlayerMP) par5Entity;
	    if (par5Entity.dimension != CodeLyoko.Carthage)
	    {
	     thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, CodeLyoko.Carthage, new LyokoCarthageTeleporter(thePlayer.mcServer.worldServerForDimension(CodeLyoko.Carthage)));
	    }
	    else
	    {
	     thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, new LyokoCarthageTeleporter(thePlayer.mcServer.worldServerForDimension(0)));
	    }
	   }
	  }
	 }
	 
	 public boolean tryToCreatePortal(World par1World, int par2, int par3, int par4)
	    {
	        byte var5 = 0;
	        byte var6 = 0;

	        if (par1World.getBlockId(par2 - 1, par3, par4) == Block.sandStone.blockID || par1World.getBlockId(par2 + 1, par3, par4) == Block.sandStone.blockID)
	        {
	            var5 = 1;
	        }

	        if (par1World.getBlockId(par2, par3, par4 - 1) == Block.sandStone.blockID || par1World.getBlockId(par2, par3, par4 + 1) == Block.sandStone.blockID)
	        {
	            var6 = 1;
	        }

	        if (var5 == var6)
	        {
	            return false;
	        }
	        else
	        {
	            if (par1World.getBlockId(par2 - var5, par3, par4 - var6) == 0)
	            {
	                par2 -= var5;
	                par4 -= var6;
	            }

	            int var7;
	            int var8;

	            for (var7 = -1; var7 <= 2; ++var7)
	            {
	                for (var8 = -1; var8 <= 3; ++var8)
	                {
	                    boolean var9 = var7 == -1 || var7 == 2 || var8 == -1 || var8 == 3;

	                    if (var7 != -1 && var7 != 2 || var8 != -1 && var8 != 3)
	                    {
	                        int var10 = par1World.getBlockId(par2 + var5 * var7, par3 + var8, par4 + var6 * var7);

	                        if (var9)
	                        {
	                            if (var10 != Block.sandStone.blockID)
	                            {
	                                return false;
	                            }
	                        }
	                        else if (var10 != 0 && var10 != Block.fire.blockID)
	                        {
	                            return false;
	                        }
	                    }
	                }
	            }

	            par1World.editingBlocks = true;

	            for (var7 = 0; var7 < 2; ++var7)
	            {
	                for (var8 = 0; var8 < 3; ++var8)
	                {
	                    par1World.setBlockWithNotify(par2 + var5 * var7, par3 + var8, par4 + var6 * var7, this.blockID);
	                }
	            }

	            par1World.editingBlocks = false;
	            return true;
	        }
	    }
	 
	 public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	    {
	        
	    }
	 
	 public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
	 {
	  byte var6 = 0;
	  byte var7 = 1;

	   if (par1World.getBlockId(par2 - 1, par3, par4) == this.blockID || par1World.getBlockId(par2 + 1, par3, par4) == this.blockID)
	  {
	   var6 = 1;
	   var7 = 0;
	  }

	   int var8;

	   for (var8 = par3; par1World.getBlockId(par2, var8 - 1, par4) == this.blockID; --var8)
	  {
	   ;
	  }

	   if (par1World.getBlockId(par2, var8 - 1, par4) != Block.sandStone.blockID)
	  {
	   par1World.setBlockWithNotify(par2, par3, par4, 0);
	  }
	  else
	  {
	   int var9;

	    for (var9 = 1; var9 < 4 && par1World.getBlockId(par2, var8 + var9, par4) == this.blockID; ++var9)
	   {
	    ;
	   }

	    if (var9 == 3 && par1World.getBlockId(par2, var8 + var9, par4) == Block.sandStone.blockID)
	   {
	    boolean var10 = par1World.getBlockId(par2 - 1, par3, par4) == this.blockID || par1World.getBlockId(par2 + 1, par3, par4) == this.blockID;
	    boolean var11 = par1World.getBlockId(par2, par3, par4 - 1) == this.blockID || par1World.getBlockId(par2, par3, par4 + 1) == this.blockID;

	     if (var10 && var11)
	    {
	     par1World.setBlockWithNotify(par2, par3, par4, 0);
	    }
	    else
	    {
	     if ((par1World.getBlockId(par2 + var6, par3, par4 + var7) != Block.sandStone.blockID || par1World.getBlockId(par2 - var6, par3, par4 - var7) != this.blockID) && (par1World.getBlockId(par2 - var6, par3, par4 - var7) != Block.sandStone.blockID || par1World.getBlockId(par2 + var6, par3, par4 + var7) != this.blockID))
	     {
	      par1World.setBlockWithNotify(par2, par3, par4, 0);
	     }
	    }
	   }
	   else
	   {
	    par1World.setBlockWithNotify(par2, par3, par4, 0);
	   }
	  }
	 }
}
