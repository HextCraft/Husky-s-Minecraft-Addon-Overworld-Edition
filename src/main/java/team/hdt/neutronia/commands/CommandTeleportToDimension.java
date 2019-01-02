package team.hdt.neutronia.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.DimensionType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandTeleportToDimension extends CommandBase {

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        String s = args[0];
        int dimension = 0;

        try {
            if(s.equals("nether")) dimension = -1;
            if(s.equals("the_end")) dimension = 1;
        } catch (NumberFormatException e) {
            dimension = Integer.parseInt(s);
            sender.sendMessage(new TextComponentTranslation("commands.neutronia.dimension_not_found", dimension).setStyle(new Style().setColor(TextFormatting.RED)));
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
        if(args.length == 1) {
            List<String> dimensionIDs = new ArrayList<>();
            for(DimensionType dimensionType : DimensionType.values()) {
                dimensionIDs.add(dimensionType.getName());
            }
            return dimensionIDs;
        }
        return Collections.emptyList();
    }

}