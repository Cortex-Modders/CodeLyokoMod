package matt.lyoko;

import net.minecraft.creativetab.CreativeTabs;

public class LyokoTab extends CreativeTabs {

	public LyokoTab(String par2Str) {
		super(par2Str);
		
	}
	
    public int getTabIconItemIndex()
    {
    	return CodeLyoko.LyokoLog.blockID;
                           
    }

    public String getTranslatedTabLabel()
    {
    	return "Lyoko";
    }

}
