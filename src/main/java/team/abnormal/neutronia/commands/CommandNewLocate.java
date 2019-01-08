package team.abnormal.neutronia.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.server.command.CommandTreeBase;

import javax.annotation.Nullable;
import java.util.*;

public class CommandNewLocate extends CommandTreeBase {

    public CommandNewLocate() {
        addSubcommand(new LocateBiomeCommand());
        addSubcommand(new LocateStructureCommand());
    }

    @Override
    public String getName() {
        return "locate";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.neutronia.usage.locate";
    }

    public static class LocateBiomeCommand extends CommandBase {

        @Override
        public String getName() {
            return "biome";
        }

        @Override
        public String getUsage(ICommandSender sender) {
            return "commands.neutronia.locate.biome.usage";
        }

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
                pos.setPos(x, 100, z);
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
                    return new BlockPos((int) x, 100, (int) z);
                }
            }
            return null;
        }

        @Override
        public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
            List<String> strings = new ArrayList<>();
            for (Biome b : ForgeRegistries.BIOMES.getValues()) {
                String s = Objects.requireNonNull(b.getRegistryName()).toString();
                if (s.toLowerCase().contains(args[0].toLowerCase()))
                    strings.add(s);
            }
            return args.length == 1 ? strings : Collections.emptyList();
        }

        @Override
        public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
            if(args.length != 1) {
                try {
                    throw new WrongUsageException("command.neutronia.locate_biome.usage");
                } catch (WrongUsageException e) {
                    e.printStackTrace();
                }
            } else {
                ResourceLocation biomeName = new ResourceLocation(args[0]);
                if (ForgeRegistries.BIOMES.getValue(biomeName) == null) {
                    sender.sendMessage(new TextComponentTranslation("command.neutronia.not_found_biome", biomeName.toString()).setStyle(new Style().setColor(TextFormatting.RED)));
                    try {
                        throw new CommandException("command.neutronia.locate_biome.failure", args[0]);
                    } catch (CommandException e) {
                        e.printStackTrace();
                    }
                }
                new Thread(() -> {
                    BlockPos blockpos = sender.getEntityWorld().getBiomeProvider().findBiomePosition(sender.getPosition().getX(), sender.getPosition().getZ(), 10_000, sender.getEntityWorld().getBiomeProvider().getBiomesToSpawnIn(), new Random());
                    BlockPos pos = spiralOutwardsLookingForBiome(sender, sender.getEntityWorld(), ForgeRegistries.BIOMES.getValue(biomeName), sender.getPosition().getX(), sender.getPosition().getZ(), 30_000);
                    if(sender.getEntityWorld().getBiome(Objects.requireNonNull(pos)) == ForgeRegistries.BIOMES.getValue(biomeName)) {
                        sender.sendMessage(new TextComponentTranslation("command.neutronia.found_biome", biomeName, pos.getX(), pos.getZ()));
                    }
                }, "Neutronia Biome Locator").start();
            }
        }
    }

    public static class LocateStructureCommand extends CommandBase {

        /**
         * Gets the name of the command
         */
        public String getName()
        {
            return "structure";
        }

        /**
         * Return the required permission level for this command.
         */
        public int getRequiredPermissionLevel()
        {
            return 2;
        }

        /**
         * Gets the usage string for the command.
         */
        public String getUsage(ICommandSender sender)
        {
            return "commands.neutronia.locate.structure.usage";
        }

        /**
         * Callback for when the command is executed
         */
        public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
        {
            if (args.length != 1)
            {
                throw new WrongUsageException("commands.locate.usage");
            }
            else
            {
                String s = args[0];
                BlockPos blockpos = sender.getEntityWorld().findNearestStructure(s, sender.getPosition(), false);

                if (blockpos != null)
                {
                    sender.sendMessage(new TextComponentTranslation("commands.locate.success", s, blockpos.getX(), blockpos.getZ()));
                }
                else
                {
                    throw new CommandException("commands.locate.failure", s);
                }
            }
        }

        /**
         * Get a list of options for when the user presses the TAB key
         */
        public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
        {
            return args.length == 1 ? getListOfStringsMatchingLastWord(args, "Stronghold", "Monument", "Village", "Mansion", "EndCity", "Fortress", "Temple", "Mineshaft") : Collections.emptyList();
        }

    }

}
