package matt.lyoko;

import java.util.EnumSet;

import matt.lyoko.render.TileAnimator;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTickHandler implements ITickHandler
{
	public int lifePoints = 80;
	
    private void onRenderTick(EntityPlayer player)
    {
    	//TODO set this up to render life points
        /* not used anymore
        Minecraft mc = Minecraft.getMinecraft();
        FontRenderer font = mc.fontRenderer;
        int x = 30;
        int y = 30;
        int color = 10000;
        FMLClientHandler.instance().getClient().ingameGUI.drawString(font, Integer.toString(lifePoints), x, y, color);
        */
    }
    
    /**
     * Used to advance all the animator instances once every tick.
     * 
     * @param tickData
     */
    public void advanceAnimatorInstances(Object... tickData) {
        for(TileAnimator inst : CodeLyoko.animatorInstances) {
            inst.animate();
        }
    }
    
    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {
        if (type.equals(EnumSet.of(TickType.RENDER)))
        {
        	for(int i = 0; i < tickData.length; i++)
        	{
        		if(tickData[i] instanceof EntityPlayer)
        		{
        			onRenderTick((EntityPlayer)tickData[0]);
        		}
        	}
        	
            advanceAnimatorInstances(tickData);
        }
    }
    
    @Override
    public EnumSet<TickType> ticks() 
    {
        return EnumSet.of(TickType.PLAYER, TickType.CLIENT, TickType.RENDER);
    }
    
    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
    	
    }
    
    @Override
    public String getLabel()
    {
        return null;
    }
}