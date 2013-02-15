package matt.lyoko.entities;

import cpw.mods.fml.relauncher.*;
import java.util.*;
import net.minecraft.entity.*;
import net.minecraft.nbt.*;
import net.minecraft.network.packet.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class TileEntityDigitalSea extends TileEntity
{
    /** The stored delay before a new spawn. */
    public int delay = -1;

    /**
     * The string ID of the mobs being spawned from this spawner. Defaults to pig, apparently.
     */
    private String mobID = "Manta";
    private List field_92016_e = null;

    /** The extra NBT data to add to spawned entities */
    private TileEntityCarthageSpawnerSpawnData spawnerTags = null;
    public double yaw;
    public double yaw2 = 0.0D;
    private int minSpawnDelay = 200;
    private int maxSpawnDelay = 800;
    private int spawnCount = 4;
    private Entity field_92017_j;

    /** Maximum number of entities for limiting mob spawning */
    private int maxNearbyEntities = 3;

    /** Required player range for mob spawning to occur */
    private int requiredPlayerRange = 32;

    /** Range for spawning new entities with mob spawners */
    private int spawnRange = 4;

    public TileEntityDigitalSea()
    {
        this.delay = 20;
    }

    public String func_92015_a()
    {
        return this.spawnerTags == null ? this.mobID : this.spawnerTags.str;
    }

    public void setMobID(String par1Str)
    {
        this.mobID = par1Str;
    }

    /**
     * Returns true if there is a player in range (using World.getClosestPlayer)
     */
    public boolean anyPlayerInRange()
    {
        return this.worldObj.getClosestPlayer((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D, (double)this.requiredPlayerRange) != null;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        if (this.anyPlayerInRange())
        {
            double var5;

            if (this.worldObj.isRemote)
            {
                double var1 = (double)((float)this.xCoord + this.worldObj.rand.nextFloat());
                double var3 = (double)((float)this.yCoord + this.worldObj.rand.nextFloat());
                var5 = (double)((float)this.zCoord + this.worldObj.rand.nextFloat());
                this.worldObj.spawnParticle("smoke", var1, var3, var5, 0.0D, 0.0D, 0.0D);
                this.worldObj.spawnParticle("flame", var1, var3, var5, 0.0D, 0.0D, 0.0D);

                if (this.delay > 0)
                {
                    --this.delay;
                }

                this.yaw2 = this.yaw;
                this.yaw = (this.yaw + (double)(1000.0F / ((float)this.delay + 200.0F))) % 360.0D;
            }
            else
            {
                if (this.delay == -1)
                {
                    this.updateDelay();
                }

                if (this.delay > 0)
                {
                    --this.delay;
                    return;
                }

                boolean var12 = false;

                for (int var2 = 0; var2 < this.spawnCount; ++var2)
                {
                    Entity var13 = EntityList.createEntityByName(this.func_92015_a(), this.worldObj);

                    if (var13 == null)
                    {
                        return;
                    }

                    int var4 = this.worldObj.getEntitiesWithinAABB(var13.getClass(), AxisAlignedBB.getAABBPool().addOrModifyAABBInPool((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, (double)(this.xCoord + 1), (double)(this.yCoord + 1), (double)(this.zCoord + 1)).expand((double)(this.spawnRange * 2), 4.0D, (double)(this.spawnRange * 2))).size();

                    if (var4 >= this.maxNearbyEntities)
                    {
                        this.updateDelay();
                        return;
                    }

                    if (var13 != null)
                    {
                        var5 = (double)this.xCoord + (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble()) * (double)this.spawnRange;
                        double var7 = (double)(this.yCoord + this.worldObj.rand.nextInt(3) - 1);
                        double var9 = (double)this.zCoord + (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble()) * (double)this.spawnRange;
                        EntityLiving var11 = var13 instanceof EntityLiving ? (EntityLiving)var13 : null;
                        var13.setLocationAndAngles(var5, var7, var9, this.worldObj.rand.nextFloat() * 360.0F, 0.0F);

                        if (var11 == null || var11.getCanSpawnHere())
                        {
                            this.writeNBTTagsTo(var13);
                            this.worldObj.spawnEntityInWorld(var13);
                            this.worldObj.playAuxSFX(2004, this.xCoord, this.yCoord, this.zCoord, 0);

                            if (var11 != null)
                            {
                                var11.spawnExplosionParticle();
                            }

                            var12 = true;
                        }
                    }
                }

                if (var12)
                {
                    this.updateDelay();
                }
            }

            super.updateEntity();
        }
    }

    public void writeNBTTagsTo(Entity par1Entity)
    {
        if (this.spawnerTags != null)
        {
            NBTTagCompound var2 = new NBTTagCompound();
            par1Entity.addEntityID(var2);
            Iterator var3 = this.spawnerTags.nbt.getTags().iterator();

            while (var3.hasNext())
            {
                NBTBase var4 = (NBTBase)var3.next();
                var2.setTag(var4.getName(), var4.copy());
            }

            par1Entity.readFromNBT(var2);
        }
        else if (par1Entity instanceof EntityLiving && par1Entity.worldObj != null)
        {
            ((EntityLiving)par1Entity).initCreature();
        }
    }

    /**
     * Sets the delay before a new spawn (base delay of 200 + random number up to 600).
     */
    private void updateDelay()
    {
        if (this.maxSpawnDelay <= this.minSpawnDelay)
        {
            this.delay = this.minSpawnDelay;
        }
        else
        {
            this.delay = this.minSpawnDelay + this.worldObj.rand.nextInt(this.maxSpawnDelay - this.minSpawnDelay);
        }

        if (this.field_92016_e != null && this.field_92016_e.size() > 0)
        {
            this.spawnerTags = (TileEntityCarthageSpawnerSpawnData)WeightedRandom.getRandomItem(this.worldObj.rand, this.field_92016_e);
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        }

        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType().blockID, 1, 0);
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        this.mobID = par1NBTTagCompound.getString("EntityId");
        this.delay = par1NBTTagCompound.getShort("Delay");

        if (par1NBTTagCompound.hasKey("SpawnPotentials"))
        {
            this.field_92016_e = new ArrayList();
            NBTTagList var2 = par1NBTTagCompound.getTagList("SpawnPotentials");

            for (int var3 = 0; var3 < var2.tagCount(); ++var3)
            {
                this.field_92016_e.add(new TileEntityCarthageSpawnerSpawnData(this, (NBTTagCompound)var2.tagAt(var3)));
            }
        }
        else
        {
            this.field_92016_e = null;
        }

        if (par1NBTTagCompound.hasKey("SpawnData"))
        {
            this.spawnerTags = new TileEntityCarthageSpawnerSpawnData(this, par1NBTTagCompound.getCompoundTag("SpawnData"), this.mobID);
        }
        else
        {
            this.spawnerTags = null;
        }

        if (par1NBTTagCompound.hasKey("MinSpawnDelay"))
        {
            this.minSpawnDelay = par1NBTTagCompound.getShort("MinSpawnDelay");
            this.maxSpawnDelay = par1NBTTagCompound.getShort("MaxSpawnDelay");
            this.spawnCount = par1NBTTagCompound.getShort("SpawnCount");
        }

        if (par1NBTTagCompound.hasKey("MaxNearbyEntities"))
        {
            this.maxNearbyEntities = par1NBTTagCompound.getShort("MaxNearbyEntities");
            this.requiredPlayerRange = par1NBTTagCompound.getShort("RequiredPlayerRange");
        }

        if (par1NBTTagCompound.hasKey("SpawnRange"))
        {
            this.spawnRange = par1NBTTagCompound.getShort("SpawnRange");
        }

        if (this.worldObj != null && this.worldObj.isRemote)
        {
            this.field_92017_j = null;
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setString("EntityId", this.func_92015_a());
        par1NBTTagCompound.setShort("Delay", (short)this.delay);
        par1NBTTagCompound.setShort("MinSpawnDelay", (short)this.minSpawnDelay);
        par1NBTTagCompound.setShort("MaxSpawnDelay", (short)this.maxSpawnDelay);
        par1NBTTagCompound.setShort("SpawnCount", (short)this.spawnCount);
        par1NBTTagCompound.setShort("MaxNearbyEntities", (short)this.maxNearbyEntities);
        par1NBTTagCompound.setShort("RequiredPlayerRange", (short)this.requiredPlayerRange);
        par1NBTTagCompound.setShort("SpawnRange", (short)this.spawnRange);

        if (this.spawnerTags != null)
        {
            par1NBTTagCompound.setCompoundTag("SpawnData", (NBTTagCompound)this.spawnerTags.nbt.copy());
        }

        if (this.spawnerTags != null || this.field_92016_e != null && this.field_92016_e.size() > 0)
        {
            NBTTagList var2 = new NBTTagList();

            if (this.field_92016_e != null && this.field_92016_e.size() > 0)
            {
                Iterator var3 = this.field_92016_e.iterator();

                while (var3.hasNext())
                {
                	TileEntityCarthageSpawnerSpawnData var4 = (TileEntityCarthageSpawnerSpawnData)var3.next();
                    var2.appendTag(var4.func_92030_a());
                }
            }
            else
            {
                var2.appendTag(this.spawnerTags.func_92030_a());
            }

            par1NBTTagCompound.setTag("SpawnPotentials", var2);
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * will create the entity from the internalID the first time it is accessed
     */
    public Entity getMobEntity()
    {
        if (this.field_92017_j == null)
        {
            Entity var1 = EntityList.createEntityByName(this.func_92015_a(), (World)null);
            this.writeNBTTagsTo(var1);
            this.field_92017_j = var1;
        }

        return this.field_92017_j;
    }

    /**
     * Overriden in a sign to provide the text.
     */
    public Packet getDescriptionPacket()
    {
        NBTTagCompound var1 = new NBTTagCompound();
        this.writeToNBT(var1);
        var1.removeTag("SpawnPotentials");
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, var1);
    }

    /**
     * Called when a client event is received with the event number and argument, see World.sendClientEvent
     */
    public void receiveClientEvent(int par1, int par2)
    {
        if (par1 == 1 && this.worldObj.isRemote)
        {
            this.delay = this.minSpawnDelay;
        }
    }
}
