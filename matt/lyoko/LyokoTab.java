package matt.lyoko;

import matt.lyoko.blocks.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;

public class LyokoTab extends CreativeTabs {

	public LyokoTab(String par2Str) {
		super(par2Str);
		
	}
	
    public int getTabIconItemIndex()
    {
    	return ModBlocks.Log.blockID;
                           
    }

    public String getTranslatedTabLabel()
    {
    	return "Lyoko";
    }

}
