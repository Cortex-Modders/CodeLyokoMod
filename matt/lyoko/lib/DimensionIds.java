package matt.lyoko.lib;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;

public class DimensionIds {

    /* Default IDs. */
    public static int ICE_DEFAULT = 3;
    public static int MOUNTAIN_DEFAULT = 4;
    public static int FOREST_DEFAULT = 5;
    public static int DESERT_DEFAULT = 6;
    // seven is skipped so the mod will be
    // inherently compatible with Twilight Forest
    public static int CARTHAGE_DEFAULT = 8;
    public static int DIGITALSEA_DEFAULT = 9;
    public static int CORTEX_DEFAULT = 10;
    
    /* IDs loaded from config. */
    public static int ICE;
    public static int MOUNTAIN;
    public static int FOREST;
    public static int DESERT;
    public static int CARTHAGE;
    public static int DIGITALSEA;
    public static int CORTEX;
    
    public static void teleportToDimension(EntityPlayer player, int newDim)
    {
        if (!player.worldObj.isRemote && !player.isDead)
        {
        	player.worldObj.theProfiler.startSection("changeDimension");
            MinecraftServer minecraftServer = MinecraftServer.getServer();
            int currentDim = player.dimension;
            WorldServer currentWorldServer = minecraftServer.worldServerForDimension(currentDim);
            WorldServer newWorldServer = minecraftServer.worldServerForDimension(newDim);
            player.dimension = newDim;
            
            player.worldObj.removeEntity(player);
            player.isDead = false;
            player.worldObj.theProfiler.startSection("reposition");
            transferEntityToWorld(player, currentDim, currentWorldServer, newWorldServer);
            player.worldObj.theProfiler.endStartSection("reloading");
            Entity entity = EntityList.createEntityByName(EntityList.getEntityString(player), newWorldServer);
            
            if (entity != null)
            {
                entity.copyDataFrom(player, true);
                newWorldServer.spawnEntityInWorld(entity);
            }
            
            player.isDead = true;
            player.worldObj.theProfiler.endSection();
            currentWorldServer.resetUpdateEntityTick();
            newWorldServer.resetUpdateEntityTick();
            player.worldObj.theProfiler.endSection();
        }
    }
    
    public static void transferEntityToWorld(EntityPlayer player, int currentDim, WorldServer currentWorldServer, WorldServer newWorldServer)//, Teleporter teleporter)
    {
        WorldProvider pOld = currentWorldServer.provider;
        WorldProvider pNew = newWorldServer.provider;
        double moveFactor = pOld.getMovementFactor() / pNew.getMovementFactor();
        double d0 = player.posX * moveFactor;
        double d1 = player.posZ * moveFactor;
        double d3 = player.posX;
        double d4 = player.posY;
        double d5 = player.posZ;
        float f = player.rotationYaw;
        currentWorldServer.theProfiler.startSection("moving");
        
        currentWorldServer.theProfiler.endSection();
        
        currentWorldServer.theProfiler.startSection("placing");
        d0 = (double)MathHelper.clamp_int((int)d0, -29999872, 29999872);
        d1 = (double)MathHelper.clamp_int((int)d1, -29999872, 29999872);
        
        if (player.isEntityAlive())
        {
        	newWorldServer.spawnEntityInWorld(player);
        	player.setLocationAndAngles(d0, player.posY, d1, player.rotationYaw, player.rotationPitch);
        	newWorldServer.updateEntityWithOptionalForce(player, false);
        }
        
        currentWorldServer.theProfiler.endSection();
        
        player.setWorld(newWorldServer);
    }
}