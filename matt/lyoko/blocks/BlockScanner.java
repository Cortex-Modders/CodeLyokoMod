package matt.lyoko.blocks;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import matt.lyoko.CodeLyoko;
import matt.lyoko.entities.tileentity.TileEntityScanner;
import matt.lyoko.lib.DimensionIds;
import matt.lyoko.lib.PlayerInformation;
import matt.lyoko.world.LyokoTeleporter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockScanner extends BlockContainer
{
	public BlockScanner(int par1)
	{
		super(par1, Material.iron);
		this.setCreativeTab(CodeLyoko.LyokoTabs);
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
	
	public static boolean isMultiBlock(World world, int x, int y, int z)
    {
    	if(world.getBlockId(x+1, y, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x+1, y, z) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x+1, y, z-1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y, z) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y, z-1) == ModBlocks.Scanner.blockID
    			
    			&& world.getBlockId(x+1, y+1, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x+1, y+1, z) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x+1, y+1, z-1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y+1, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y+1, z-1) == ModBlocks.Scanner.blockID
    			
    			&& world.getBlockId(x+1, y+2, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x+1, y+2, z) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x+1, y+2, z-1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y+2, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y+2, z-1) == ModBlocks.Scanner.blockID
    			
    			&& world.getBlockId(x+1, y+3, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x+1, y+3, z) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x+1, y+3, z-1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y+3, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y+3, z-1) == ModBlocks.Scanner.blockID
    			
    			&& world.getBlockId(x+1, y+4, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x+1, y+4, z) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x+1, y+4, z-1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y+4, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y+4, z) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y+4, z-1) == ModBlocks.Scanner.blockID)
    	{
    		return true;
    	}
    	else if(world.getBlockId(x-1, y, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x-1, y, z) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x-1, y, z-1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y, z) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y, z-1) == ModBlocks.Scanner.blockID
    			
    			&& world.getBlockId(x-1, y+1, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x-1, y+1, z) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x-1, y+1, z-1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y+1, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y+1, z-1) == ModBlocks.Scanner.blockID
    			
    			&& world.getBlockId(x-1, y+2, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x-1, y+2, z) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x-1, y+2, z-1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y+2, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y+2, z-1) == ModBlocks.Scanner.blockID
    			
    			&& world.getBlockId(x-1, y+3, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x-1, y+3, z) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x-1, y+3, z-1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y+3, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y+3, z-1) == ModBlocks.Scanner.blockID
    			
    			&& world.getBlockId(x-1, y+4, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x-1, y+4, z) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x-1, y+4, z-1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y+4, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y+4, z) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y+4, z-1) == ModBlocks.Scanner.blockID)
    	{
    		return true;
    	}
    	else if(world.getBlockId(x+1, y, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x-1, y, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x+1, y, z) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y, z) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x-1, y, z) == ModBlocks.Scanner.blockID
    			
    			&& world.getBlockId(x+1, y+1, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y+1, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x-1, y+1, z+1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x+1, y+1, z) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x-1, y+1, z) == ModBlocks.Scanner.blockID
    			
    			&& world.getBlockId(x+1, y+2, z+1) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x, y+2, z+1) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x-1, y+2, z+1) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x+1, y+2, z) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x-1, y+2, z) == ModBlocks.Scanner.blockID
    			
    	    	&& world.getBlockId(x+1, y+3, z+1) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x, y+3, z+1) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x-1, y+3, z+1) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x+1, y+3, z) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x-1, y+3, z) == ModBlocks.Scanner.blockID
    			
    	    	&& world.getBlockId(x+1, y+4, z+1) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x, y+4, z+1) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x-1, y+4, z+1) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x+1, y+4, z) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x, y+4, z) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x-1, y+4, z) == ModBlocks.Scanner.blockID)
    	{
    		return true;
    	}
    	else if(world.getBlockId(x+1, y, z-1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y, z-1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x-1, y, z-1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x+1, y, z) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y, z) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x-1, y, z) == ModBlocks.Scanner.blockID
    			
    			&& world.getBlockId(x+1, y+1, z-1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x, y+1, z-1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x-1, y+1, z-1) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x+1, y+1, z) == ModBlocks.Scanner.blockID
    			&& world.getBlockId(x-1, y+1, z) == ModBlocks.Scanner.blockID
    			
    			&& world.getBlockId(x+1, y+2, z-1) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x, y+2, z-1) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x-1, y+2, z-1) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x+1, y+2, z) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x-1, y+2, z) == ModBlocks.Scanner.blockID
    			
    	    	&& world.getBlockId(x+1, y+3, z-1) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x, y+3, z-1) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x-1, y+3, z-1) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x+1, y+3, z) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x-1, y+3, z) == ModBlocks.Scanner.blockID
    			
    	    	&& world.getBlockId(x+1, y+4, z-1) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x, y+4, z-1) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x-1, y+4, z-1) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x+1, y+4, z) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x, y+4, z) == ModBlocks.Scanner.blockID
    	    	&& world.getBlockId(x-1, y+4, z) == ModBlocks.Scanner.blockID)
    	{
    		return true;
    	}
    	return false;
    }
	
	public void activatePortal(World world, int x, int y, int z, EntityPlayer player)
	{
		TileEntityScanner tile = (TileEntityScanner)world.getBlockTileEntity(x, y, z);
		if(tile != null)
		{
			/*int portal;
			switch(tile.sector)
			{
			case 0:
				portal = ModBlocks.LyokoPolarPortal.blockID;
				break;
			case 1:
				portal = ModBlocks.LyokoDesertPortal.blockID;
				break;
			case 2:
				portal = ModBlocks.LyokoForestPortal.blockID;
				break;
			case 3:
				portal = ModBlocks.LyokoMountainPortal.blockID;
				break;
			case 4:
				portal = ModBlocks.LyokoCarthagePortal.blockID;
				break;
			default:
				portal = 0;
			}
			
			world.setBlock(x, y + 1, z, portal);
			world.setBlock(x, y + 2, z, portal);
			world.setBlock(x, y + 3, z, portal);
			
			if(portal != 0)
			{
				tile.setAutomaticTimer();
			}*/
			
			if(CodeLyoko.entityInLyoko(player))
			{
				if(player.worldObj.isRemote)
				{
					player.addChatMessage("You can't be virtualized while currently virtualized.");
					player.addChatMessage("Yes, that's right, I thought you, " + player.username + ", would try to do this.");
				}
				return;
			}
			
			int dim;
			switch(tile.sector)
			{
			case 0:
				dim = DimensionIds.ICE;
				break;
			case 1:
				dim = DimensionIds.DESERT;
				break;
			case 2:
				dim = DimensionIds.FOREST;
				break;
			case 3:
				dim = DimensionIds.MOUNTAIN;
				break;
			case 4:
				dim = DimensionIds.CARTHAGE;
				break;
			default:
				return;
			}
			
			if(player instanceof EntityPlayerMP)
			{
				LyokoTeleporter.transferPlayerToDimension((EntityPlayerMP)player, dim);
			}
			int xPos = (int) player.posX;
			int yPos = (int) player.posY;
			int zPos = (int) player.posZ;
			while(player.worldObj.getBlockId(xPos, yPos, zPos) != 0 && yPos < 256)
			{
				yPos++;
			}
			
			player.setPositionAndRotation(player.posX, yPos, player.posZ, player.rotationYaw, player.rotationPitch);
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream(32);
		    DataOutputStream outputStream = new DataOutputStream(bos);
		    try
		    {
		    	outputStream.writeDouble(player.posX);
		    	outputStream.writeDouble(player.posY);
		    	outputStream.writeDouble(player.posZ);
		    	outputStream.writeFloat(player.rotationYaw);
		    }
		    catch (Exception ex)
		    {
		    	ex.printStackTrace();
		    }
		    
		    Packet250CustomPayload packet = new Packet250CustomPayload();
		    packet.channel = "Devirt";
		    packet.data = bos.toByteArray();
		    packet.length = bos.size();
		    
		    PacketDispatcher.sendPacketToPlayer(packet,(Player) player);
			
			tile.sector = -1;
		}
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are)
    {
    	TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
    	if (tileEntity == null || player.isSneaking())
    	{
    		return false;
    	}
    	else
    	{
    		for(int i = -1; i < 2; i++)
    		{
    			for(int k = -1; k < 2; k++)
    			{
    				for(int j = -4; j < 1; j++)
    				{
    					if(isMultiBlock(world, x+i, y+j, z+k))
    					{
    						PlayerInformation pi = PlayerInformation.forPlayer(player);
    						
    						pi.scannerDim = world.provider.dimensionId;
    						pi.setScannerPosition(x+i, y+j+1, z+k);
    						((TileEntityScanner)world.getBlockTileEntity(x+i, y+j, z+k)).setPlayerDevirtYaw(pi);
    						
    						activatePortal(world, x+i, y+j, z+k, player);
    						
    						return true;
    					}
    				}
    			}
    		}
    		return false;
    	}
    }
}
