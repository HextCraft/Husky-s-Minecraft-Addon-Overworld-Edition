package team.hdt.neutronia.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.util.Collections;
import java.util.List;

public class CommandTeleportToDimension extends CommandBase {

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        if (args.length < 1) return;

        String s = args[0];
        int dimension;

        try {
            dimension = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            dimension = Integer.parseInt(s);
            sender.sendMessage(new TextComponentString(TextFormatting.RED + String.format("Dimension %d not found", dimension)));
            return;
        }

        if (sender instanceof EntityPlayer) {
            TeleportUtil.teleportToDimension((EntityPlayer) sender, dimension, 0, 100, 0);
        }
    }

    @Override
    public String getName() {
        return "dimension_tp";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "dimension_tp <id>";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
        return Collections.emptyList();
    }

}