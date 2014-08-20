package net.cortexmodders.lyoko.command;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.lib.DimensionIds;
import net.cortexmodders.lyoko.lib.PlayerInformation;
import net.cortexmodders.lyoko.tileentity.TileEntityScanner;
import net.cortexmodders.lyoko.world.LyokoTeleporter;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jadar
 */
public class CommandDeleteDimension implements ICommand {

    @SuppressWarnings("rawtypes")
    private List aliases;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public CommandDeleteDimension()
    {
        this.aliases = new ArrayList();
        this.aliases.add("dimdelete");
    }

    @Override
    public String getCommandName()
    {
        return "deleteDimension";
    }

    @Override
    public String getCommandUsage(ICommandSender icommandsender)
    {
        return "/deleteDimension dimensionId";
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List getCommandAliases()
    {
        return this.aliases;
    }

    @Override
    public void processCommand(ICommandSender icommandsender, String[] astring) {
        if (astring.length == 0) {
            icommandsender.addChatMessage(new ChatComponentText("Too few arguments. Needed 1."));
            return;
        } else if (astring.length > 1) {
            icommandsender.addChatMessage(new ChatComponentText("Too many arguments. Needed 1."));
            return;
        }

        String dimName = astring[0];
        if (StringUtils.isNullOrEmpty(dimName)) {
            icommandsender.addChatMessage(new ChatComponentText("Error!"));
            return;
        }

        int id = DimensionIds.dimensionIdForName(dimName);
        if (id == -2) {
            icommandsender.addChatMessage(new ChatComponentText("Error! Invalid name."));
            return;
        }

        DimensionManager.unloadWorld(id);
        World world = DimensionManager.getWorld(id);
        if (world == null) {
            icommandsender.addChatMessage(new ChatComponentText("Did not find dimension " + id + "."));
        }

        boolean flag = world.getSaveHandler().getWorldDirectory().delete();
        if (flag) {
            icommandsender.addChatMessage(new ChatComponentText("Deleted dimension " + id + "."));
        } else {
            icommandsender.addChatMessage(new ChatComponentText("Could not delete dimension " + id + "!"));
        }



    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender icommandsender)
    {
        return icommandsender.canCommandSenderUseCommand(4, this.getCommandName());
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List addTabCompletionOptions(ICommandSender icommandsender, String[] astring)
    {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] astring, int i)
    {
        return false;
    }

    @Override
    public int compareTo(Object o)
    {
        return 0;
    }
}
