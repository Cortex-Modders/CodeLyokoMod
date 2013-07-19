package matt.lyoko.lib;

import net.minecraft.util.DamageSource;

public class LyokoDamageSource extends DamageSource
{
	public LyokoDamageSource(String par1Str)
	{
		super(par1Str);
	}
	
	public static LyokoDamageSource digitalSea = new LyokoDamageSource("CodeLyoko.digitalSea").setDamageBypassesArmor();
	public static LyokoDamageSource marabounta = new LyokoDamageSource("CodeLyoko.marabounta").setDamageBypassesArmor();
	
	public LyokoDamageSource setDamageBypassesArmor()
    {
        return (LyokoDamageSource) super.setDamageBypassesArmor();
    }
}