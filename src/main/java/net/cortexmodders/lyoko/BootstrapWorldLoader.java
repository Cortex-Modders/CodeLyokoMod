package net.cortexmodders.lyoko;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.LoadingScreenRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.client.event.GuiScreenEvent;

/**
 *
 * This class will automatically load a world upon the first display of the Minecraft main menu. <br />
 * Initialize an instance of this class, then register it with {@link net.minecraftforge.common.MinecraftForge MinecraftForge.EVENT_BUS} <br />
 *
 * @author jadar
 */
public class BootstrapWorldLoader {

    public boolean eventTriggered = false;
    public final String worldName;

    public BootstrapWorldLoader(String worldName) {
        this.worldName = worldName;
    }

    @SubscribeEvent
    public void guiOpened(GuiScreenEvent.InitGuiEvent.Post event) {
        if (!(event.gui instanceof GuiMainMenu)) {
            return;
        }

        if (eventTriggered) {
            return;
        }

        // this is needed because the event is called before the loading screen is initialized in Minecraft.startGame().
        if (Minecraft.getMinecraft().loadingScreen == null) {
            Minecraft.getMinecraft().loadingScreen = new LoadingScreenRenderer(Minecraft.getMinecraft());
        }

        // keeps us from getting in a hard loop.
        eventTriggered = true;

        if (Minecraft.getMinecraft().getSaveLoader().canLoadWorld(worldName)) {
            // loads the world.
            FMLClientHandler.instance().tryLoadExistingWorld(new GuiSelectWorld(new GuiMainMenu()), worldName, worldName);
        } else {
            // present a GuiCreateWorld so you can make the world.
            GuiCreateWorld gui = new GuiCreateWorld(new GuiMainMenu());
            WorldInfo info = new WorldInfo(new WorldSettings(0, WorldSettings.GameType.SURVIVAL, true, false, WorldType.DEFAULT), worldName);
            gui.func_146318_a(info);
            Minecraft.getMinecraft().displayGuiScreen(gui);
        }
    }


}
