package matt.lyoko.blocks;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import matt.lyoko.CodeLyoko;
import matt.lyoko.client.ClientProxy;
import matt.lyoko.entities.tileentity.TileEntityScanner;
import matt.lyoko.lib.DimensionIds;
import matt.lyoko.lib.PlayerInformation;
import matt.lyoko.world.LyokoTeleporter;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class BlockScanner extends BlockContainer {

    public BlockScanner(int par1) {
        super(par1, Material.iron);
        this.setCreativeTab(CodeLyoko.LyokoTabs);
    }

    @Override
    public TileEntity createNewTileEntity(World var1) {
        return new TileEntityScanner();
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        blockIcon = par1IconRegister.registerIcon("lyoko:scanner");
    }

    @Override
    public int getRenderType() {
        return ClientProxy.scannerRenderId;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isBlockSolid(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return true;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    /**
     * 
     * Finds if it is part of a multi block. Returns boolean. 
     * Use getPositionInMultiBlock to find exact position.
     * 
     * @param world
     * @param x
     * @param y
     * @param z
     * @return
     */
    public static boolean isMultiBlock(World world, int x, int y, int z) {
        /*
         * if(world.getBlockId(x+1, y, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x+1, y, z) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x+1, y, z-1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y, z) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y, z-1) == ModBlocks.Scanner.blockID
         * 
         * && world.getBlockId(x+1, y+1, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x+1, y+1, z) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x+1, y+1, z-1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+1, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+1, z-1) == ModBlocks.Scanner.blockID
         * 
         * && world.getBlockId(x+1, y+2, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x+1, y+2, z) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x+1, y+2, z-1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+2, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+2, z-1) == ModBlocks.Scanner.blockID
         * 
         * && world.getBlockId(x+1, y+3, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x+1, y+3, z) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x+1, y+3, z-1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+3, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+3, z-1) == ModBlocks.Scanner.blockID
         * 
         * && world.getBlockId(x+1, y+4, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x+1, y+4, z) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x+1, y+4, z-1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+4, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+4, z) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+4, z-1) == ModBlocks.Scanner.blockID) { return
         * true; } else if(world.getBlockId(x-1, y, z+1) ==
         * ModBlocks.Scanner.blockID && world.getBlockId(x-1, y, z) ==
         * ModBlocks.Scanner.blockID && world.getBlockId(x-1, y, z-1) ==
         * ModBlocks.Scanner.blockID && world.getBlockId(x, y, z+1) ==
         * ModBlocks.Scanner.blockID && world.getBlockId(x, y, z) ==
         * ModBlocks.Scanner.blockID && world.getBlockId(x, y, z-1) ==
         * ModBlocks.Scanner.blockID
         * 
         * && world.getBlockId(x-1, y+1, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x-1, y+1, z) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x-1, y+1, z-1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+1, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+1, z-1) == ModBlocks.Scanner.blockID
         * 
         * && world.getBlockId(x-1, y+2, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x-1, y+2, z) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x-1, y+2, z-1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+2, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+2, z-1) == ModBlocks.Scanner.blockID
         * 
         * && world.getBlockId(x-1, y+3, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x-1, y+3, z) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x-1, y+3, z-1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+3, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+3, z-1) == ModBlocks.Scanner.blockID
         * 
         * && world.getBlockId(x-1, y+4, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x-1, y+4, z) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x-1, y+4, z-1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+4, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+4, z) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+4, z-1) == ModBlocks.Scanner.blockID) { return
         * true; } else if(world.getBlockId(x+1, y, z+1) ==
         * ModBlocks.Scanner.blockID && world.getBlockId(x, y, z+1) ==
         * ModBlocks.Scanner.blockID && world.getBlockId(x-1, y, z+1) ==
         * ModBlocks.Scanner.blockID && world.getBlockId(x+1, y, z) ==
         * ModBlocks.Scanner.blockID && world.getBlockId(x, y, z) ==
         * ModBlocks.Scanner.blockID && world.getBlockId(x-1, y, z) ==
         * ModBlocks.Scanner.blockID
         * 
         * && world.getBlockId(x+1, y+1, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+1, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x-1, y+1, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x+1, y+1, z) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x-1, y+1, z) == ModBlocks.Scanner.blockID
         * 
         * && world.getBlockId(x+1, y+2, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+2, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x-1, y+2, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x+1, y+2, z) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x-1, y+2, z) == ModBlocks.Scanner.blockID
         * 
         * && world.getBlockId(x+1, y+3, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+3, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x-1, y+3, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x+1, y+3, z) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x-1, y+3, z) == ModBlocks.Scanner.blockID
         * 
         * && world.getBlockId(x+1, y+4, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+4, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x-1, y+4, z+1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x+1, y+4, z) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+4, z) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x-1, y+4, z) == ModBlocks.Scanner.blockID) { return
         * true; } else if(world.getBlockId(x+1, y, z-1) ==
         * ModBlocks.Scanner.blockID && world.getBlockId(x, y, z-1) ==
         * ModBlocks.Scanner.blockID && world.getBlockId(x-1, y, z-1) ==
         * ModBlocks.Scanner.blockID && world.getBlockId(x+1, y, z) ==
         * ModBlocks.Scanner.blockID && world.getBlockId(x, y, z) ==
         * ModBlocks.Scanner.blockID && world.getBlockId(x-1, y, z) ==
         * ModBlocks.Scanner.blockID
         * 
         * && world.getBlockId(x+1, y+1, z-1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+1, z-1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x-1, y+1, z-1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x+1, y+1, z) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x-1, y+1, z) == ModBlocks.Scanner.blockID
         * 
         * && world.getBlockId(x+1, y+2, z-1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+2, z-1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x-1, y+2, z-1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x+1, y+2, z) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x-1, y+2, z) == ModBlocks.Scanner.blockID
         * 
         * && world.getBlockId(x+1, y+3, z-1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+3, z-1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x-1, y+3, z-1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x+1, y+3, z) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x-1, y+3, z) == ModBlocks.Scanner.blockID
         * 
         * && world.getBlockId(x+1, y+4, z-1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+4, z-1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x-1, y+4, z-1) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x+1, y+4, z) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x, y+4, z) == ModBlocks.Scanner.blockID &&
         * world.getBlockId(x-1, y+4, z) == ModBlocks.Scanner.blockID) { return
         * true; }
         */


        return getPositionInMultiBlock(world, x, y, z) != -1;
    }

    /**
     * 
     * Returns the position at which the block is in the multiblock.
     * 
     * -1 = none, 1 = bottom, 2-4 = middle, 5 = top
     * 
     * @param world
     * @param x
     * @param y
     * @param z
     * @return
     */
    public static int getPositionInMultiBlock(World world, int x, int y, int z) {
        // From bottom block.
        for (int i = 0; i <= 4; i++) {
            if (world.getBlockId(x, y + i, z) != ModBlocks.Scanner.blockID)
                break;
            if(i == 4)
                return 0;
        }
        // From 1st to bottom block. i.e. block above bottom.
        for (int i = -1; i <= 3; i++) {
            if (world.getBlockId(x, y + i, z) != ModBlocks.Scanner.blockID)
                break;
            if(i == 3)
                return 1;
        }
        // From 2nd to bottom block. i.e. very middle block.
        for (int i = -2; i <= 2; i++) {
            if (world.getBlockId(x, y + i, z) != ModBlocks.Scanner.blockID)
                break;
            if(i == 2)
                return 2;
        }
        // From 1nd to top block. i.e. block under top block.
        for (int i = -3; i <= 1; i++) {
            if (world.getBlockId(x, y + i, z) != ModBlocks.Scanner.blockID)
                break;
            if(i == 1)
                return 3;
        }
        // From top block.
        for (int i = -4; i <= 0; i++) {
            if (world.getBlockId(x, y + i, z) != ModBlocks.Scanner.blockID)
                break;
            if(i == 0)
                return 4;
        }
        return -1;
    }


    /**
     * 
     * Returns an array of Vector3s that correspond to scanner blocks in
     * a multi block. world, x, y, and z are for a scanner block that is in
     * the multi block. Returns null if not in a multi block. 
     * 
     * @param world
     * @param x
     * @param y
     * @param z
     * @return
     */
    public static Vec3[] getBlockCoordsInMultiBlock(World world, int x, int y, int z)
    {
        int pos = getPositionInMultiBlock(world, x, y, z);
        if(pos == -1)
            return null;
        
        Vec3[] array = new Vec3[5];
        
        int j = 0;
        for(int i = 0 - pos; i < 5 - pos; i++)
        {
            if(world.getBlockId(x, y + i, z) == ModBlocks.Scanner.blockID)
                array[j] = Vec3.createVectorHelper(x, y + i, z);
            else
            	System.out.println("for loop not complete successfully");
            
            j++;
        }

        if(j != 5)
        	return null;
        
        return array;
    }


    public void virtualize(World world, int x, int y, int z, EntityPlayer player)
    {
        TileEntityScanner tile = (TileEntityScanner) world.getBlockTileEntity(x, y, z);
        if (tile != null)
        {
            /**
             * 
             * IDEA: Here set the timer. In the tile entity if the timer is not at -1, keep the doors open.
             * 
             */
            
            
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
                LyokoTeleporter.transferPlayerToDimension((EntityPlayerMP) player, dim);
            }
            int xPos = (int) player.posX;
            int yPos = (int) player.posY;
            int zPos = (int) player.posZ;
            while(player.worldObj.getBlockId(xPos, yPos, zPos) != 0 && yPos < 256)
            {
                yPos++;
            }
            
            player.setPositionAndRotation(player.posX, yPos, player.posZ, player.rotationYaw, player.rotationPitch);
            
            ByteArrayOutputStream bos = new ByteArrayOutputStream(28);
            DataOutputStream outputStream = new DataOutputStream(bos);
            try
            {
                outputStream.writeDouble(player.posX);
                outputStream.writeDouble(player.posY);
                outputStream.writeDouble(player.posZ);
                outputStream.writeFloat(player.rotationYaw);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            
            Packet250CustomPayload packet = new Packet250CustomPayload();
            packet.channel = "Devirt";
            packet.data = bos.toByteArray();
            packet.length = bos.size();
            
            PacketDispatcher.sendPacketToPlayer(packet, (Player) player);
            
            tile.sector = -1;
        }
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are) {

        /*
        if(isMultiBlock(world, x, y, z)) {
            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            if (tileEntity == null || player.isSneaking() || !(tileEntity instanceof TileEntityScanner))
                return false;

            TileEntityScanner reference = (TileEntityScanner)tileEntity;
            reference.toggleDoors();

            return true;
        }
        else
            return false;
         */

        if(isMultiBlock(world, x, y, z))
        {
            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            if (tileEntity == null || player.isSneaking() || !(tileEntity instanceof TileEntityScanner))
                return false;
            
            TileEntityScanner tile = (TileEntityScanner)tileEntity;
            
            Vec3[] array = getBlockCoordsInMultiBlock(world, x, y, z);
            
            tile.toggleDoors();
            
            for(Vec3 coords : array)
            {
                TileEntityScanner scanner = (TileEntityScanner) world.getBlockTileEntity((int)coords.xCoord, (int)coords.yCoord, (int)coords.zCoord);
                scanner.doorsOpen = tile.doorsOpen;
            }
            
            TileEntityScanner core = (TileEntityScanner)world.getBlockTileEntity((int)array[0].xCoord, (int)array[0].yCoord, (int)array[0].zCoord);
            if(!core.doorsOpen)// && core.yCoord == (int)player.posY - 1)
            {
            	//if(core.xCoord == Math.floor(player.posX) && core.zCoord == Math.floor(player.posZ))
            	//{
                    PlayerInformation pi = PlayerInformation.forPlayer(player);
                    
                    pi.scannerDim = world.provider.dimensionId;
                    pi.setScannerPosition(core.xCoord, core.yCoord + 1, core.zCoord);
                    core.setPlayerDevirtYaw(pi);
                    
                    virtualize(world, core.xCoord, core.yCoord, core.zCoord, player);
            	//}
            }
            return true;
        }
        return false;
    }
    
    @Override
    public void onBlockPlacedBy(World par1World, int x, int y, int z, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack)
    {
    	super.onBlockPlacedBy(par1World, x, y, z, par5EntityLiving, par6ItemStack);
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
    
    @Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
    	return null;
    }
}