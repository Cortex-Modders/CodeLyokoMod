package matt.lyoko.client;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SoundHandler {

    @ForgeSubscribe
    public void onSoundLoad(SoundLoadEvent event) {
        try {
            event.manager.soundPoolSounds.addSound("mods/lyoko/sounds/scannerClose.ogg", this.getClass().getResource("/mods/lyoko/sounds/scannerClose.ogg"));
            System.out.println("[Code Lyoko] Loaded sounds.");
        } 
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
