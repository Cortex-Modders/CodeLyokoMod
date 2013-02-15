package matt.lyoko.entities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomItem;

class TileEntityCarthageSpawnerSpawnData extends WeightedRandomItem
{
    public final NBTTagCompound nbt;
    public final String str;

    final TileEntityDigitalSea teds;

    public TileEntityCarthageSpawnerSpawnData(TileEntityDigitalSea par1TileEntityMobSpawner, NBTTagCompound par2NBTTagCompound)
    {
        super(par2NBTTagCompound.getInteger("Weight"));
        this.teds = par1TileEntityMobSpawner;
        this.nbt = par2NBTTagCompound.getCompoundTag("Properties");
        this.str = par2NBTTagCompound.getString("Type");
    }

    public TileEntityCarthageSpawnerSpawnData(TileEntityDigitalSea par1TileEntityMobSpawner, NBTTagCompound par2NBTTagCompound, String par3Str)
    {
        super(1);
        this.teds = par1TileEntityMobSpawner;
        this.nbt = par2NBTTagCompound;
        this.str = par3Str;
    }

    public NBTTagCompound func_92030_a()
    {
        NBTTagCompound var1 = new NBTTagCompound();
        var1.setCompoundTag("Properties", this.nbt);
        var1.setString("Type", this.str);
        var1.setInteger("Weight", this.itemWeight);
        return var1;
    }
}
