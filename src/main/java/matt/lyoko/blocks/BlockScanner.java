/*
 * Code Lyoko Mod for Minecraft v@VERSION Copyright 2013 Cortex Modders, Matthew
 * Warren, Jacob Rhoda, and other contributors. Released under the MIT license
 * http://opensource.org/licenses/MIT
 */

package matt.lyoko.blocks;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.List;

import matt.lyoko.CodeLyoko;
import matt.lyoko.client.ClientProxy;
import matt.lyoko.entities.tileentity.TileEntityScanner;
import matt.lyoko.lib.DimensionIds;
import matt.lyoko.lib.ModProperties;
import matt.lyoko.lib.PlayerInformation;
import matt.lyoko.world.LyokoTeleporter;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockScanner extends BlockContainer
{

    public BlockScanner()
    {
        // material.iron
        super(Material.field_151573_f);
        // setCreativeTab
        this.func_149647_a(CodeLyoko.LyokoTabs);
    }

    @Override
    // createNewTileEntity
    public TileEntity func_149915_a(World world, int metadata)
    {
        return new TileEntityScanner();
    }

    @Override
    // registerBlockIcons
    public void func_149651_a(IIconRegister par1IconRegister)
    {
        // blockIcon
        this.field_149761_L = par1IconRegister.registerIcon("lyoko:scanner");
    }

    @Override
    // getRenderType
    public int func_149645_b()
    {
        return ClientProxy.scannerRenderId;
    }

    @Override
    // isOpaqueCube
    public boolean func_149662_c()
    {
        return false;
    }

    @Override
    // isBlockSolid
    public boolean func_149747_d(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return true;
    }

    @Override
    // renderAsNormalBlock
    public boolean func_149686_d()
    {
        return false;
    }

    /**
     * 
     * Finds if it is part of a multi block. Returns boolean. Use
     * getPositionInMultiBlock to find exact position.
     * 
     * @param world
     * @param x
     * @param y
     * @param z
     * @return
     */
    public static boolean isMultiBlock(World world, int x, int y, int z)
    {
        return getPositionInMultiBlock(world, x, y, z) != -1;
    }

    /**
     * 
     * Returns the position at which the block is in the multiblock.
     * 
     * -1 = none, 0 = bottom, 1-3 = middle, 4 = top
     * 
     * @param world
     * @param x
     * @param y
     * @param z
     * @return
     */
    public static int getPositionInMultiBlock(IBlockAccess world, int x, int y, int z)
    {
        int prevMeta;

        // From bottom block.
        prevMeta = -1;
        for (int i = 0; i <= 4; i++)
        {
            int meta = world.getBlockMetadata(x, y + i, z);
            if (!(world.func_147439_a(x, y + i, z) instanceof BlockScanner))
                break;
            if (prevMeta != -1 & meta != prevMeta)
                break;
            if (i == 4)
                return 0;
            prevMeta = meta;
        }

        // From 1st to bottom block. i.e. block above bottom.
        prevMeta = -1;
        for (int i = -1; i <= 3; i++)
        {
            int meta = world.getBlockMetadata(x, y + i, z);
            if (!(world.func_147439_a(x, y + i, z) instanceof BlockScanner))
                break;
            if (prevMeta != -1 & meta != prevMeta)
                break;
            if (i == 3)
                return 1;
            prevMeta = meta;
        }

        // From 2nd to bottom block. i.e. very middle block.
        prevMeta = -1;
        for (int i = -2; i <= 2; i++)
        {
            int meta = world.getBlockMetadata(x, y + i, z);
            if (!(world.func_147439_a(x, y + i, z) instanceof BlockScanner))
                break;
            if (prevMeta != -1 & meta != prevMeta)
                break;
            if (i == 2)
                return 2;
            prevMeta = meta;
        }

        // From 1nd to top block. i.e. block under top block.
        prevMeta = -1;
        for (int i = -3; i <= 1; i++)
        {
            int meta = world.getBlockMetadata(x, y + i, z);
            if (!(world.func_147439_a(x, y + i, z) instanceof BlockScanner))
                break;
            if (prevMeta != -1 & meta != prevMeta)
                break;
            if (i == 1)
                return 3;
            prevMeta = meta;
        }

        // From top block.
        prevMeta = -1;
        for (int i = -4; i <= 0; i++)
        {
            int meta = world.getBlockMetadata(x, y + i, z);
            if (!(world.func_147439_a(x, y + i, z) instanceof BlockScanner))
                break;
            if (prevMeta != -1 & meta != prevMeta)
                break;
            if (i == 0)
                return 4;
            prevMeta = meta;
        }
        return -1;
    }

    /**
     * 
     * Returns an array of Vector3s that correspond to scanner blocks in a multi
     * block. world, x, y, and z are for a scanner block that is in the multi
     * block. Returns null if not in a multi block.
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
        if (pos == -1)
            return null;

        Vec3[] array = new Vec3[5];

        int j = 0;
        for (int i = 0 - pos; i < 5 - pos; i++)
        {
            if (world.func_147439_a(x, y + i, z) instanceof BlockScanner)
                array[j] = Vec3.createVectorHelper(x, y + i, z);

            j++;
        }

        if (j != 5)
            return null;

        return array;
    }

    public void virtualize(World world, int x, int y, int z, EntityPlayer player)
    {
        TileEntityScanner tile = (TileEntityScanner) world.func_147438_o(x, y, z);
        if (tile != null)
        {
            /**
             * 
             * IDEA: Here set the timer. In the tile entity if the timer is not
             * at -1, keep the doors open.
             * 
             */

            if (CodeLyoko.entityInLyoko(player))
            {
                if (player.worldObj.isRemote)
                {
                    // addChatMessage
                    player.func_145747_a(new ChatComponentText("You can't be virtualized while already virtualized."));
                    player.func_145747_a(new ChatComponentText("Yes, that's right, I thought you, " + player.func_146103_bH().getName() + ", would try to do that."));
                }
                return;
            }

            int dim;
            switch (tile.sector)
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

            if (player instanceof EntityPlayerMP)
                LyokoTeleporter.transferPlayerToDimension((EntityPlayerMP) player, dim);
            int xPos = (int) player.posX;
            int yPos = (int) player.posY;
            int zPos = (int) player.posZ;
            while (!(player.worldObj.func_147439_a(xPos, yPos, zPos) instanceof BlockAir) && yPos < 256)
                yPos++;

            if (player instanceof EntityPlayerMP)
                ((EntityPlayerMP) player).setPositionAndRotation(player.posX, yPos, player.posZ, player.rotationYaw, player.rotationPitch);

            ByteArrayOutputStream bos = new ByteArrayOutputStream(28);
            DataOutputStream outputStream = new DataOutputStream(bos);
            try
            {
                outputStream.writeDouble(player.posX);
                outputStream.writeDouble(yPos);
                outputStream.writeDouble(player.posZ);
                outputStream.writeFloat(player.rotationYaw);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            // TODO: Networking!
            // Packet250CustomPayload packet = new Packet250CustomPayload();
            // packet.channel = "Devirt";
            // packet.data = bos.toByteArray();
            // packet.length = bos.size();
            //
            // PacketDispatcher.sendPacketToPlayer(packet, (Player) player);

            tile.sector = -1;
        }
    }

    @Override
    // onBlockActivated
    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are)
    {
        if (isMultiBlock(world, x, y, z))
        {
            TileEntity tileEntity = world.func_147438_o(x, y, z);
            if (tileEntity == null || player.isSneaking() || !(tileEntity instanceof TileEntityScanner))
                return false;

            TileEntityScanner tile = (TileEntityScanner) tileEntity;

            Vec3[] array = getBlockCoordsInMultiBlock(world, x, y, z);

            tile.toggleAllDoors();

            TileEntityScanner core = (TileEntityScanner) world.func_147438_o((int) array[0].xCoord, (int) array[0].yCoord, (int) array[0].zCoord);
            if (!core.doorsOpen)// && core.yCoord == (int)player.posY - 1)
            {
                if (world.isRemote)
                    world.playSoundAtEntity(player, ModProperties.SOUND_PREFIX + "scannerClose", 1.0F, 1.0F);

                PlayerInformation pi = PlayerInformation.forPlayer(player);

                pi.scannerDim = world.provider.dimensionId;
                pi.setScannerPosition(core.field_145851_c, core.field_145848_d + 1, core.field_145849_e);
                core.setPlayerDevirtYaw(pi);

                this.virtualize(world, core.field_145851_c, core.field_145848_d, core.field_145849_e, player);
            }
            return true;
        }
        return false;
    }

    @Override
    // onBlockPlacedBy
    public void func_149689_a(World par1World, int x, int y, int z, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack)
    {
        super.func_149689_a(par1World, x, y, z, par5EntityLiving, par6ItemStack);
        int l = MathHelper.floor_double(par5EntityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        par1World.setBlockMetadataWithNotify(x, y, z, l, 2);
    }

    @SuppressWarnings("rawtypes")
    @Override
    // addCollisionBoxesToList
    // func_149676_a - setBlockBounds
    public void func_149743_a(World world, int x, int y, int z, AxisAlignedBB axisAlignedBB, List list, Entity ent)
    {
        int pos = getPositionInMultiBlock(world, x, y, z);
        if (pos == 0)
        {
            this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
            super.func_149743_a(world, x, y, z, axisAlignedBB, list, ent);
        }
        else if (pos == 4)
        {
            this.func_149676_a(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
            super.func_149743_a(world, x, y, z, axisAlignedBB, list, ent);
        }

        int meta = world.getBlockMetadata(x, y, z);
        float f = 0.25F;
        if (meta != 3 || !((TileEntityScanner) world.func_147438_o(x, y, z)).doorsOpen)
        {
            this.func_149676_a(-f, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F);
            super.func_149743_a(world, x, y, z, axisAlignedBB, list, ent);
        }
        if (meta != 0 || !((TileEntityScanner) world.func_147438_o(x, y, z)).doorsOpen)
        {
            this.func_149676_a(0.0F, 0.0F, -f, 1.0F, 1.0F, 0.0F);
            super.func_149743_a(world, x, y, z, axisAlignedBB, list, ent);
        }
        if (meta != 1 || !((TileEntityScanner) world.func_147438_o(x, y, z)).doorsOpen)
        {
            this.func_149676_a(1.0F, 0.0F, 0.0F, 1.0F + f, 1.0F, 1.0F);
            super.func_149743_a(world, x, y, z, axisAlignedBB, list, ent);
        }
        if (meta != 2 || !((TileEntityScanner) world.func_147438_o(x, y, z)).doorsOpen)
        {
            this.func_149676_a(0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F + f);
            super.func_149743_a(world, x, y, z, axisAlignedBB, list, ent);
        }
        // setBlockBoundsForItemRender
        this.func_149683_g();
    }

    @Override
    // setBlockBoundsBasedOnState
    public void func_149719_a(IBlockAccess blockAccess, int x, int y, int z)
    {
        int meta = blockAccess.getBlockMetadata(x, y, z);
        int pos = getPositionInMultiBlock(blockAccess, x, y, z);
        float pixel = 0.125F;
        float twoPixel = 0.25F;

        if (meta == 0 && (pos > 0 && pos < 4 || pos == -1))
            this.func_149676_a(0 - twoPixel, 0, 0 - pixel, 1 + twoPixel, 1, 1 + twoPixel);
        else if (meta == 1 && (pos > 0 && pos < 4 || pos == -1))
            this.func_149676_a(0 - twoPixel, 0, 0 - twoPixel, 1 + pixel, 1, 1 + twoPixel);
        else if (meta == 2 && (pos > 0 && pos < 4 || pos == -1))
            this.func_149676_a(0 - twoPixel, 0, 0 - twoPixel, 1 + twoPixel, 1, 1 + pixel);
        else if (meta == 3 && (pos > 0 && pos < 4 || pos == -1))
            this.func_149676_a(0 - pixel, 0, 0 - twoPixel, 1 + twoPixel, 1, 1 + twoPixel);
        else
            this.func_149676_a(0 - twoPixel, 0, 0 - twoPixel, 1 + twoPixel, 1, 1 + twoPixel);
    }
}