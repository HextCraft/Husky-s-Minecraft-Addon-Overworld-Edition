package net.hdt.neutronia.base.util;

import com.google.common.collect.ImmutableList;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Loader;

import java.util.List;

public class NFMLMisc {

    private static List<String> brandings;
    private static List<String> brandingsNoMC;

    public static void computeBranding() {
        if (brandings == null) {
            ImmutableList.Builder<String> brd = ImmutableList.builder();
            brd.add("Minecraft 1.13");
            brd.add(Loader.instance().getMCPVersionString());
            brd.add("Powered by Forge " + ForgeVersion.getVersion());
            if (Loader.instance().getFMLBrandingProperties().containsKey("fmlbranding")) {
                brd.add(Loader.instance().getFMLBrandingProperties().get("fmlbranding"));
            }
            int tModCount = Loader.instance().getModList().size();
            int aModCount = Loader.instance().getActiveModList().size();
            brd.add(String.format("%d mod%s loaded, %d mod%s active", tModCount, tModCount != 1 ? "s" : "", aModCount, aModCount != 1 ? "s" : ""));
            brandings = brd.build();
            brandingsNoMC = brandings.subList(1, brandings.size());
        }
    }

    public static List<String> getBrandings(boolean includeMC) {
        if (brandings == null) {
            computeBranding();
        }
        return includeMC ? ImmutableList.copyOf(brandings) : ImmutableList.copyOf(brandingsNoMC);
    }

}
