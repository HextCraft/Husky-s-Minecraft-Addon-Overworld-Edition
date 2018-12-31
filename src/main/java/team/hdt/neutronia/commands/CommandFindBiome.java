package team.hdt.neutronia.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommandFindBiome extends CommandBase {

	private static BlockPos spiralOutwardsLookingForBiome(ICommandSender sender, World world, Biome biomeToFind, double startX, double startZ, int timeout) {
		double a = 16 / Math.sqrt(Math.PI);
		double b = 2 * Math.sqrt(Math.PI);
		double x;
		double z;
		double dist = 0;
		int n;
		long start = System.currentTimeMillis();
		BlockPos.PooledMutableBlockPos pos = BlockPos.PooledMutableBlockPos.retain();
		int previous = 0;
		int i = 0;
		for (n = 0; dist < Integer.MAX_VALUE; ++n) {
			if ((System.currentTimeMillis() - start) > timeout) {
				return null;
			}
			double rootN = Math.sqrt(n);
			dist = a * rootN;
			x = startX + (dist * Math.sin(b * rootN));
			z = startZ + (dist * Math.cos(b * rootN));
			pos.setPos(x, 0, z);
			if (sender instanceof EntityPlayer) {
				if (previous == 3)
					previous = 0;
				String s = (previous == 0 ? "." : previous == 1 ? ".." : "...");
				((EntityPlayer) sender).sendStatusMessage(new TextComponentString("Scanning" + s), true);
				if (i == 1501) {
					previous++;
					i = 0;
				}
				i++;
			}
			if (world.getBiome(pos).equals(biomeToFind)) {
				pos.release();
				if (sender instanceof EntityPlayer)
					((EntityPlayer) sender).sendStatusMessage(new TextComponentString("Found Biome"), true);
				return new BlockPos((int) x, 0, (int) z);
			}
		}
		return null;
	}

	@Override
	public String getName() {
		return "locate_biome";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/locate_biome <biome>";
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if(args.length == 0) {
            List<String> strings = new ArrayList<>();
            for (Biome b : ForgeRegistries.BIOMES.getValues()) {
                String s = b.getRegistryName().toString();
                if (s.toLowerCase().contains(args[0].toLowerCase()))
                    strings.add(s);
            }

            return strings;
        }
		return Collections.emptyList();
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        ResourceLocation biomeName = new ResourceLocation(args[1]);
        String name = StringUtils.capitalize(biomeName.getPath());
        String biomeChatName = name.replace("_", " ");
        name = WordUtils.capitalizeFully(biomeChatName);
        System.out.println(Arrays.toString(args));
        if (args.length == 1) {
            notifyCommandListener(sender, this, "command.neutronia.not_defined_biome");
            return;
        }
        if (Biome.REGISTRY.getObject(biomeName) == null) {
            notifyCommandListener(sender, this, TextFormatting.RED + "command.neutronia.not_found_biome", name);
            return;
        }
        String finalName = name;
        new Thread(() -> {
            BlockPos pos = spiralOutwardsLookingForBiome(sender, sender.getEntityWorld(), Biome.REGISTRY.getObject(new ResourceLocation(biomeName.getPath())), sender.getPosition().getX(), sender.getPosition().getZ(), 10_000);
            if (pos == null) {
                server.addScheduledTask(() -> sender.sendMessage(new TextComponentString(TextFormatting.RED + "Error! Biome '" + args[0] + "' could not be found after " + TextFormatting.GRAY + 30_000 + "ms" + TextFormatting.RED + ".")));
                return;
            }
            if (sender instanceof EntityPlayerMP) {
                server.addScheduledTask(() -> notifyCommandListener(sender, this, "command.neutronia.found_biome", pos.getX(), pos.getZ(), finalName));
            }
        }, "Neutronia Biome Finder").start();
	}
}