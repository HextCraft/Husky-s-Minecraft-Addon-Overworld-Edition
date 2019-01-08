/*
package neutronia.client.gui;

import com.google.common.collect.Lists;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@SideOnly(Side.CLIENT)
public class GuiCreateBuffetWorld extends GuiScreen {
    private static final List<ResourceLocation> BUFFET_GENERATORS;
    private final GuiCreateWorld parent;
    private final List<ResourceLocation> biomes = Lists.newArrayList();
    private final ResourceLocation[] biomeTypes;
    private String title;
    private GuiCreateBuffetWorld.BiomeList biomeList;
    private int field_205312_t;
    private GuiButton field_205313_u;
    private final ChunkGeneratorSettings.Factory defaultSettings = new ChunkGeneratorSettings.Factory();
    private ChunkGeneratorSettings.Factory settings;

    public GuiCreateBuffetWorld(GuiCreateWorld p_i49701_1_, String p_i49701_2_) {
        this.biomeTypes = new ResourceLocation[Biome.REGISTRY.getKeys().size()];
        this.parent = p_i49701_1_;
        int lvt_3_1_ = 0;

        for(Iterator var4 = Biome.REGISTRY.getKeys().iterator(); var4.hasNext(); ++lvt_3_1_) {
            ResourceLocation lvt_5_1_ = (ResourceLocation)var4.next();
            this.biomeTypes[lvt_3_1_] = lvt_5_1_;
        }

        Arrays.sort(this.biomeTypes, (p_210140_0_, p_210140_1_) -> {
            String lvt_2_1_ = Objects.requireNonNull(Biome.REGISTRY.getObject(p_210140_0_)).getBiomeName();
            String lvt_4_1_ = (Objects.requireNonNull(Biome.REGISTRY.getObject(p_210140_1_))).getBiomeName();
            return lvt_2_1_.compareTo(lvt_4_1_);
        });
        this.loadValues(p_i49701_2_);
    }

    public void loadValues(String p_175324_1_)
    {
        if (p_175324_1_ != null && !p_175324_1_.isEmpty())
        {
            this.settings = ChunkGeneratorSettings.Factory.jsonToFactory(p_175324_1_);
        }
        else
        {
            this.settings = new ChunkGeneratorSettings.Factory();
        }
    }

    private String serialize() {
        NBTTagCompound lvt_1_1_ = new NBTTagCompound();
        NBTTagCompound lvt_2_1_ = new NBTTagCompound();
        lvt_2_1_.putString("type", ((ResourceLocation)BiomeProviderType.REGISTRY.getKey(BiomeProviderType.FIXED)).toString());
        NBTTagCompound lvt_3_1_ = new NBTTagCompound();
        NBTTagList lvt_4_1_ = new NBTTagList();
        Iterator var5 = this.biomes.iterator();

        while(var5.hasNext()) {
            ResourceLocation lvt_6_1_ = (ResourceLocation)var5.next();
            lvt_4_1_.add(new NBTTagString(lvt_6_1_.toString()));
        }

        lvt_3_1_.put("biomes", lvt_4_1_);
        lvt_2_1_.put("options", lvt_3_1_);
        NBTTagCompound lvt_5_1_ = new NBTTagCompound();
        NBTTagCompound lvt_6_2_ = new NBTTagCompound();
        lvt_5_1_.putString("type", BUFFET_GENERATORS.get(this.field_205312_t).toString());
        lvt_6_2_.putString("default_block", "minecraft:stone");
        lvt_6_2_.putString("default_fluid", "minecraft:water");
        lvt_5_1_.put("options", lvt_6_2_);
        lvt_1_1_.put("biome_source", lvt_2_1_);
        lvt_1_1_.put("chunk_generator", lvt_5_1_);
        return lvt_1_1_;
    }

    @Nullable
    public IGuiEventListener getFocused() {
        return this.biomeList;
    }

    protected void initGui() {
        this.mc.keyboardListener.enableRepeatEvents(true);
        this.title = I18n.format("createWorld.customize.buffet.title");
        this.biomeList = new GuiCreateBuffetWorld.BiomeList();
        this.children.add(this.biomeList);
        this.addButton(new GuiButton(2, (this.width - 200) / 2, 40, 200, 20, I18n.format("createWorld.customize.buffet.generatortype", new Object[0]) + " " + I18n.format(Util.makeTranslationKey("generator", (ResourceLocation)BUFFET_GENERATORS.get(this.field_205312_t)), new Object[0])) {
            public void onClick(double p_194829_1_, double p_194829_3_) {
                GuiCreateBuffetWorld.this.field_205312_t++;
                if (GuiCreateBuffetWorld.this.field_205312_t >= GuiCreateBuffetWorld.BUFFET_GENERATORS.size()) {
                    GuiCreateBuffetWorld.this.field_205312_t = 0;
                }

                this.displayString = I18n.format("createWorld.customize.buffet.generatortype") + " " + I18n.format(Util.makeTranslationKey("generator", (ResourceLocation)GuiCreateBuffetWorld.BUFFET_GENERATORS.get(GuiCreateBuffetWorld.this.field_205312_t)), new Object[0]);
            }
        });
        this.field_205313_u = this.addButton(new GuiButton(0, this.width / 2 - 155, this.height - 28, 150, 20, I18n.format("gui.done", new Object[0])) {
            public void onClick(double p_194829_1_, double p_194829_3_) {
                GuiCreateBuffetWorld.this.parent.chunkProviderSettingsJson = GuiCreateBuffetWorld.this.serialize();
                GuiCreateBuffetWorld.this.mc.displayGuiScreen(GuiCreateBuffetWorld.this.parent);
            }
        });
        this.addButton(new GuiButton(1, this.width / 2 + 5, this.height - 28, 150, 20, I18n.format("gui.cancel", new Object[0])) {
            public void onClick(double p_194829_1_, double p_194829_3_) {
                GuiCreateBuffetWorld.this.mc.displayGuiScreen(GuiCreateBuffetWorld.this.parent);
            }
        });
        this.func_205306_h();
    }

    public void func_205306_h() {
        this.field_205313_u.enabled = !this.biomes.isEmpty();
    }

    public void render(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        this.drawBackground(0);
        this.biomeList.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
        this.drawCenteredString(this.fontRenderer, this.title, this.width / 2, 8, 16777215);
        this.drawCenteredString(this.fontRenderer, I18n.format("createWorld.customize.buffet.generator"), this.width / 2, 30, 10526880);
        this.drawCenteredString(this.fontRenderer, I18n.format("createWorld.customize.buffet.biome"), this.width / 2, 68, 10526880);
        super.render(p_73863_1_, p_73863_2_, p_73863_3_);
    }

    static {
        BUFFET_GENERATORS = (List)ChunkGeneratorType.REGISTRY.getKeys().stream().filter((p_205307_0_) -> {
            return ((ChunkGeneratorType)ChunkGeneratorType.REGISTRY.get(p_205307_0_)).isOptionForBuffetWorld();
        }).collect(Collectors.toList());
    }

    @OnlyIn(Dist.CLIENT)
    class BiomeList extends GuiSlot {
        private BiomeList() {
            super(GuiCreateBuffetWorld.this.mc, GuiCreateBuffetWorld.this.width, GuiCreateBuffetWorld.this.height, 80, GuiCreateBuffetWorld.this.height - 37, 16);
        }

        protected int getSize() {
            return GuiCreateBuffetWorld.this.biomeTypes.length;
        }

        protected boolean mouseClicked(int p_195078_1_, int p_195078_2_, double p_195078_3_, double p_195078_5_) {
            GuiCreateBuffetWorld.this.biomes.clear();
            GuiCreateBuffetWorld.this.biomes.add(GuiCreateBuffetWorld.this.biomeTypes[p_195078_1_]);
            GuiCreateBuffetWorld.this.func_205306_h();
            return true;
        }

        protected boolean isSelected(int p_148131_1_) {
            return GuiCreateBuffetWorld.this.biomes.contains(GuiCreateBuffetWorld.this.biomeTypes[p_148131_1_]);
        }

        protected void drawBackground() {
        }

        protected void drawSlot(int p_192637_1_, int p_192637_2_, int p_192637_3_, int p_192637_4_, int p_192637_5_, int p_192637_6_, float p_192637_7_) {
            this.drawString(GuiCreateBuffetWorld.this.fontRenderer, ((Biome)Biome.REGISTRY.get(GuiCreateBuffetWorld.this.biomeTypes[p_192637_1_])).getDisplayName().getString(), p_192637_2_ + 5, p_192637_3_ + 2, 16777215);
        }
    }
}
*/
