package net.cortexmodders.lyoko.command;

import net.cortexmodders.lyoko.CodeLyoko;
import net.cortexmodders.lyoko.lib.DimensionIds;
import net.cortexmodders.lyoko.lib.PlayerInformation;
import net.cortexmodders.lyoko.tileentity.TileEntityScanner;
import net.cortexmodders.lyoko.world.LyokoTeleporter;
import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static java.util.ArrayList.*;

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
        if (astring.length != 1) {
            throw new WrongUsageException(this.getCommandUsage(icommandsender), (Object[])null);
        }

        String dimName = astring[0];
        if (StringUtils.isNullOrEmpty(dimName)) {
            icommandsender.addChatMessage(new ChatComponentText("Error!"));
            return;
        }

        int id = DimensionIds.dimensionIdForName(dimName);
        if (id == -2) {
            throw new SyntaxErrorException("Error, invalid sector name!");
        }

        DimensionManager.unloadWorld(id);
        World world = DimensionManager.getWorld(id);
        if (world == null) {
            icommandsender.addChatMessage(new ChatComponentText("§4Did not find dimension " + id + "."));
        }

        boolean flag = true;

        try {
            File saveDirectory = DimensionManager.getCurrentSaveRootDirectory();
            String dimensionSaveDirectory = world.provider.getSaveFolder();
            String worldDirectory = FilenameUtils.concat(saveDirectory.getPath(), dimensionSaveDirectory);
            File worldFile = new File(worldDirectory);

            FileUtils.deleteDirectory(worldFile);
        } catch (Exception e) {
            e.printStackTrace();
            icommandsender.addChatMessage(new ChatComponentText("§4Could not delete dimension " + id + "!"));
            flag = false;
        }

        if (flag) {
            icommandsender.addChatMessage(new ChatComponentText("Deleted dimension " + id + "."));
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender icommandsender)
    {
        return icommandsender.canCommandSenderUseCommand(4, this.getCommandName());
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List addTabCompletionOptions(ICommandSender icommandsender, String[] args)
    {
        String[] list = {"desert", "ice", "mountain", "forrest", "carthage"};
        return args.length != 1 && args.length != 2 ? null : CommandBase.getListOfStringsMatchingLastWord(args, list);
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
