package matt.lyoko.lib;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public final class PlayerInformation implements IExtendedEntityProperties
{
	public static final String IDENTIFIER = "lyoko_data";
    
    public static PlayerInformation forPlayer(Entity player)
    {
        return (PlayerInformation)player.getExtendedProperties(IDENTIFIER);
    }
    
    public boolean dirty = true;
    
    /** The current amount of mana the player has in their mana bar */
    private int lifePoints;
    public int coolDown;
    private int scannerPosX;
    private int scannerPosY;
    private int scannerPosZ;
    public int scannerDim;
    
    private final EntityPlayer player;
    
    public PlayerInformation(EntityPlayer player)
    {
        this.player = player;
    }
    
    @Override
    public void init(Entity entity, World world)
    {
    	lifePoints = 100;
    	coolDown = 0;
    	scannerPosX = 0;
    	scannerPosY = 80;
    	scannerPosZ = 0;
    	scannerDim = 0;
    }
    
    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("lifePoints", lifePoints);
        nbt.setInteger("coolDown", coolDown);
        nbt.setInteger("spx", scannerPosX);
        nbt.setInteger("spy", scannerPosY);
        nbt.setInteger("spz", scannerPosZ);
        nbt.setInteger("sd", scannerDim);
        compound.setCompoundTag(IDENTIFIER, nbt);
    }
    
    @Override
    public void loadNBTData(NBTTagCompound playerNbt)
    {
        NBTTagCompound nbt = playerNbt.getCompoundTag(IDENTIFIER);
        lifePoints = nbt.getInteger("lifePoints");
        coolDown = nbt.getInteger("coolDown");
        scannerPosX = nbt.getInteger("spx");
        scannerPosY = nbt.getInteger("spy");
        scannerPosZ = nbt.getInteger("spz");
        scannerDim = nbt.getInteger("sd");
    }
    
    public int getLifePoints()
    {
        return lifePoints;
    }
    
    public int setLifePoints(int lifePoints)
    {
        if(this.lifePoints != lifePoints)
        {
            this.lifePoints = lifePoints;
            setDirty();
        }
        return this.lifePoints;
    }
    
    public int getMaxLifePoints()
    {
        return 100;
    }
    
    public int decreaseLifePoints(int decrement)
    {
    	lifePoints -= decrement;
        setDirty();
        if (lifePoints < 0)
        {
        	lifePoints = 0;
            setDirty();
        }
        return lifePoints;
    }
    
    public int increaseLifePoints(int increment)
    {
    	lifePoints += increment;
        setDirty();
        if (lifePoints > 100)
        {
        	lifePoints = 100;
            setDirty();
        }
        return lifePoints;
    }
    
    public int getCoolDown()
    {
    	return coolDown;
    }
    
    public void resetCoolDown()
    {
    	coolDown = 10;
    }
    
    public void decreaseCoolDown(int amt)
    {
    	coolDown -= amt;
    	if(coolDown > 10)
    	{
    		coolDown = 10;
    	}
    	if(coolDown < 0)
    	{
    		coolDown = 0;
    	}
    	setDirty();
    }
    
    public int getMaxCoolDown()
    {
    	return 10;
    }
    
    public void setScannerPosition(int x, int y, int z)
    {
    	scannerPosX = x;
    	scannerPosY = y;
    	scannerPosZ = z;
    }
    
    public int getScannerPosX()
    {
    	return scannerPosX;
    }
    
    public int getScannerPosY()
    {
    	return scannerPosY;
    }
    
    public int getScannerPosZ()
    {
    	return scannerPosZ;
    }
    
    /*
     * marks that this needs to be resend to the client
     */
    public void setDirty()
    {
        dirty = true;
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream(4);
    	DataOutputStream outputStream = new DataOutputStream(bos);
    	try
    	{
    		outputStream.writeInt(this.getLifePoints());
    	}
    	catch (Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	
    	Packet250CustomPayload packet = new Packet250CustomPayload();
    	packet.channel = "LifePoints";
    	packet.data = bos.toByteArray();
    	packet.length = bos.size();
    	
    	PacketDispatcher.sendPacketToPlayer(packet,(Player) player);
    }
}