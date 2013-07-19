package matt.lyoko.entities.tileentity;

import matt.lyoko.blocks.BlockScanner;
import matt.lyoko.blocks.ModBlocks;
import matt.lyoko.lib.PlayerInformation;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityScanner extends TileEntity
{
	public int sector = -1;
	private int timer = -1;
	
	// Doors are open by default.
    public boolean doorsOpen = true;
    public float doorRotationYaw = 0;
    // Position for left door. Right door just inverts left door. Y sould never change, so it is not used.
    public float doorPosX = -9;
    public float doorPosZ = 0;
    
    // Constants.
    private final float closedScannerYaw = -90F;
    private final float openScannerYaw = 0F;

    private final float closedScannerX = -8F;
    private final float openScannerX = -9F;

    private final float closedScannerZ = -11F;
    private final float openScannerZ = 0F;
	
	
	@Override
	public void updateEntity()
	{
		if(timer > 0)
		{
			timer--;
		}
		
		if(timer == 0)
		{
			worldObj.setBlock(xCoord, yCoord + 1, zCoord, 0);
			worldObj.setBlock(xCoord, yCoord + 2, zCoord, 0);
			worldObj.setBlock(xCoord, yCoord + 3, zCoord, 0);
			timer--;
		}
		
		if(this.sector != -1)
		{
			for(int i = -1; i < 2; i++)
			{
				for(int k = -1; k < 2; k++)
				{
					for(int j = -4; j < 1; j++)
					{
						if(i != 0 || j != 0 || k != 0)
						{
							if(BlockScanner.isMultiBlock(worldObj, xCoord + i, yCoord + j, zCoord + k))
							{
								TileEntityScanner core = (TileEntityScanner)worldObj.getBlockTileEntity(xCoord + i, yCoord + j, zCoord + k);
								if(this.sector != core.sector && core.sector == -1)
									core.sector = this.sector;
								this.sector = -1;
								
								if(this.doorsOpen != core.doorsOpen && core.doorsOpen == true)
								    core.doorsOpen = this.doorsOpen;
								this.doorsOpen = true;
							}
						}
					}
				}
			}
		}
		
		// Open doors!
        // If doors are set to open, but have not rendered as fully open.
        if(this.doorsOpen & this.doorRotationYaw <= openScannerYaw) {
            this.doorRotationYaw += 5.75;
            if(this.doorRotationYaw > openScannerYaw) this.doorRotationYaw = openScannerYaw;

            if(this.doorPosZ < openScannerZ) {
                this.doorPosZ += 0.75;
                if(this.doorPosZ >= openScannerZ) {
                    this.doorPosZ = openScannerZ;
                    this.doorPosX = openScannerX;
                }
            }
        }
        // Close doors!
        else if(!this.doorsOpen & this.doorRotationYaw >= closedScannerYaw) {
            this.doorRotationYaw -= 5.75;
            if(this.doorRotationYaw < closedScannerYaw) this.doorRotationYaw = closedScannerYaw;

            if(this.doorPosZ > closedScannerZ) {
                this.doorPosZ -= 0.75;
                if(this.doorPosZ <= closedScannerZ) {
                    this.doorPosZ = closedScannerZ;
                    this.doorPosX = closedScannerX;
                }
            }
        }
	}
	
	public void setAutomaticTimer()
	{
		timer = 200;
	}
	
	public void setPlayerDevirtYaw(PlayerInformation pi)
	{
		if(worldObj.getBlockId(xCoord + 1, yCoord + 1, zCoord) != ModBlocks.Scanner.blockID)
		{
			pi.scannerYaw = 270;
		}
		if(worldObj.getBlockId(xCoord - 1, yCoord + 1, zCoord) != ModBlocks.Scanner.blockID)
		{
			pi.scannerYaw = 90;
		}
		if(worldObj.getBlockId(xCoord, yCoord + 1, zCoord + 1) != ModBlocks.Scanner.blockID)
		{
			pi.scannerYaw = 0;
		}
		if(worldObj.getBlockId(xCoord, yCoord + 1, zCoord - 1) != ModBlocks.Scanner.blockID)
		{
			pi.scannerYaw = 180;
		}
	}
	
	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, tag);
	}
	
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
	{
		NBTTagCompound tag = pkt.customParam1;
		this.readFromNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		this.sector = tagCompound.getInteger("sector");
		this.timer = tagCompound.getInteger("timer");
		this.doorsOpen = tagCompound.getBoolean("doorsOpen");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
        tagCompound.setInteger("sector", this.sector);
        tagCompound.setInteger("timer", this.timer);
        tagCompound.setBoolean("doorsOpen", this.doorsOpen);
	}
}