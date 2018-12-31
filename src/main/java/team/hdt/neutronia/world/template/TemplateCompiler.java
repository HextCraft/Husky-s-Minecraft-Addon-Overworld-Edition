package team.hdt.neutronia.world.template;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.ITemplateProcessor;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.BiConsumer;

public class TemplateCompiler {
    private final List<ResourceLocation> templates = new ArrayList<>();
    private String anchorKey;

    private final Collection<BiConsumer<PlacementSettings, Random>> settingConfigurators = new ArrayList<>();
    private ITemplateProcessor processor;
    private TemplateDataProcessor dataProcessor;
    private final Collection<TemplatePostProcessor> postProcessors = new ArrayList<>();

    public static TemplateCompiler of(ResourceLocation... templates) {
        return new TemplateCompiler().withTemplates(templates);
    }

    public TemplateCompiler withTemplates(ResourceLocation... templates) {
        Collections.addAll(this.templates, templates);
        return this;
    }

    public TemplateCompiler withSettingConfigurator(BiConsumer<PlacementSettings, Random> configurator) {
        this.settingConfigurators.add(configurator);
        return this;
    }

    public TemplateCompiler withProcessor(ITemplateProcessor processor) {
        this.processor = processor;
        return this;
    }

    public TemplateCompiler withDataProcessor(TemplateDataProcessor processor) {
        this.dataProcessor = processor;
        return this;
    }

    public TemplateCompiler withPostProcessor(TemplatePostProcessor processor) {
        this.postProcessors.add(processor);
        return this;
    }

    public TemplateCompiler withAnchor(String key) {
        if (this.anchorKey != null) {
            throw new IllegalStateException("Template is already anchored to '" + this.anchorKey + "'");
        }
        this.anchorKey = key;
        return this;
    }

    public CompiledTemplate compile(World world, Random random, BlockPos origin) {
        ResourceLocation templateId = this.templates.get(random.nextInt(this.templates.size()));

        MinecraftServer server = world.getMinecraftServer();
        TemplateManager templateManager = world.getSaveHandler().getStructureTemplateManager();

        PlacementSettings settings = this.buildPlacementSettings(random);
        Template template = templateManager.getTemplate(server, templateId);

        BlockPos anchor = this.computeAnchor(template, settings);
        BlockPos anchoredOrigin = anchor != null ? origin.subtract(anchor) : origin;

        return new CompiledTemplate(template, settings, anchoredOrigin, this.processor, this.dataProcessor, this.postProcessors);
    }

    @Nullable
    private BlockPos computeAnchor(Template template, PlacementSettings placementSettings) {
        if (this.anchorKey == null) {
            return null;
        }
        Map<BlockPos, String> dataBlocks = template.getDataBlocks(BlockPos.ORIGIN, placementSettings);
        return dataBlocks.entrySet().stream()
                .filter(e -> e.getValue().equals(this.anchorKey))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    private PlacementSettings buildPlacementSettings(Random random) {
        PlacementSettings settings = new PlacementSettings();
        for (BiConsumer<PlacementSettings, Random> configurator : this.settingConfigurators) {
            configurator.accept(settings, random);
        }
        return settings;
    }
}