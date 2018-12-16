package team.hdt.neutronia_legacy.base.client.gui.utils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Loader;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;

public class BrandingControl {
    private static List<String> brandings;
    private static List<String> brandingsNoMC;

    private static void computeBranding() {
        if (brandings == null) {
            ImmutableList.Builder<String> brd = ImmutableList.builder();
            brd.add(I18n.format("fml.mainMenu.mcVersion", Loader.instance().getMCVersionString()));
            brd.add(I18n.format("fml.mainMenu.mcpVersion", Loader.instance().getMCPVersionString()));
            brd.add(I18n.format("fml.mainMenu.forgeVersion", ForgeVersion.getVersion()));
            int tModCount = Loader.instance().getModList().size();
            int aModCount = Loader.instance().getActiveModList().size();
            brd.add(I18n.format("fml.menu.loadingmods", tModCount, tModCount != 1 ? "s" : "", aModCount, aModCount != 1 ? "s" : ""));
            brandings = brd.build();
            brandingsNoMC = brandings.subList(1, brandings.size());
        }
    }

    private static List<String> getBrandings(boolean includeMC, boolean reverse) {
        computeBranding();
        if (includeMC) {
            return reverse ? Lists.reverse(brandings) : brandings;
        } else {
            return reverse ? Lists.reverse(brandingsNoMC) : brandingsNoMC;
        }
    }

    public static void forEachLine(boolean includeMC, boolean reverse, BiConsumer<Integer, String> lineConsumer) {
        final List<String> brandings = getBrandings(includeMC, reverse);
        IntStream.range(0, brandings.size()).boxed().forEachOrdered(idx -> lineConsumer.accept(idx, brandings.get(idx)));
    }

    public static String getClientBranding() {
        return "forge";
    }

    public static String getServerBranding() {
        return "forge";
    }
}