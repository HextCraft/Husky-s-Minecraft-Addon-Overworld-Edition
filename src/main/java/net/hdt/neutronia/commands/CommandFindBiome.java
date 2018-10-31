package net.hdt.neutronia.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CommandFindBiome extends CommandBase {

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        ResourceLocation biomeName = new ResourceLocation(args[0]);
        String name = StringUtils.capitalize(biomeName.getPath());
        String biomeChatName = name.replace("_", " ");
        name = WordUtils.capitalizeFully(biomeChatName);
        if (args[0].isEmpty()) {
            notifyCommandListener(sender, this, TextFormatting.RED + "command.neutronia.biome_not_defined");
            return;
        }
        if (Biome.REGISTRY.getObject(new ResourceLocation(biomeName.getPath())) == null) {
            notifyCommandListener(sender, this, new TextComponentTranslation(TextFormatting.RED + "command.neutronia.biome_not_found").getKey(), name);
            return;
        }
        String finalName = name;
        new Thread(() -> {
            BlockPos pos = BiomeUtils.spiralOutwardsLookingForBiome(sender, sender.getEntityWorld(), Biome.REGISTRY.getObject(new ResourceLocation(biomeName.getPath())), sender.getPosition().getX(), sender.getPosition().getZ(), 10_000);
            if (pos == null) {
                server.addScheduledTask(() -> sender.sendMessage(new TextComponentString(TextFormatting.RED + "Error! Biome '" + args[0] + "' could not be found after " + TextFormatting.GRAY + 30_000 + "ms" + TextFormatting.RED + ".")));
                return;
            }
            if (sender instanceof EntityPlayerMP) {
                server.addScheduledTask(() -> sender.sendMessage(new TextComponentTranslation("command.neutronia.biome_found", Objects.requireNonNull(pos).getX(), pos.getZ(), finalName)));
            }
        }, "Neutronia Biome Finder").start();
    }

    @Override
    public String getName() {
        return "locatebiome";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/locatebiome <name>";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    /**
     * Get a list of options for when the user presses the TAB key
     */
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1) {
            List<String> strings = new ArrayList<>();
            for (Biome b : ForgeRegistries.BIOMES.getValues()) {
                String s = Objects.requireNonNull(b.getRegistryName()).toString();
                if (!s.toLowerCase().contains(args[0].toLowerCase())) {
                    continue;
                }
                strings.add(s);
            }

            return strings;
        }
        return Collections.emptyList();
    }


}