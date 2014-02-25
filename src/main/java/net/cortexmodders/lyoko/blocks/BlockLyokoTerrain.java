/*
 * Code Lyoko Mod for Minecraft ${version}
 * Copyright 2014 Cortex Modders, Matthew Warren, Jacob Rhoda, and other contributors.
 * Released under the MIT license http://opensource.org/licenses/MIT
 */

package net.cortexmodders.lyoko.blocks;

/**
 * This class is only for terrain types in LyokoTerrainTypes.
 * 
 * @author Jadar
 * 
 */
public class BlockLyokoTerrain extends BlockLyoko implements ILyokoTerrain
{
    
    public BlockLyokoTerrain(LyokoTerrainTypes parType)
    {
        super();
    }
    
    @Override
    public LyokoTerrainTypes getType()
    {
        return null;
    }
    
}
