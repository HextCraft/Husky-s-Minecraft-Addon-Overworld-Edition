package team.hdt.neutronia.base.util;

import com.google.common.collect.ImmutableMap;
import com.google.gson.*;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.util.Strings;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

public class Config {
    private static final JsonParser JSON_PARSER = new JsonParser();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    private Map<String, JsonElement> DATA;
    private Map<String, JsonElement> FALLBACK_DATA;
    private Map<String, Config> DATA_BRANCHES;
    private boolean keepDataOrder;

    public Config(File configFile, boolean keepDataOrder) {
        String jsonString = new JsonObject().toString();

        if (configFile.exists()) {
            try {
                jsonString = FileUtils.readFileToString(configFile, Charset.defaultCharset());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.keepDataOrder = keepDataOrder;

        if (keepDataOrder) {
            DATA = new LinkedHashMap<>();
            FALLBACK_DATA = new LinkedHashMap<>();
            DATA_BRANCHES = new LinkedHashMap<>();
        } else {
            DATA = new HashMap<>();
            FALLBACK_DATA = new HashMap<>();
            DATA_BRANCHES = new HashMap<>();
        }

        deserialize(jsonString);
    }

    public Config(String jsonString, boolean keepDataOrder) {
        this.keepDataOrder = keepDataOrder;

        if (keepDataOrder) {
            DATA = new LinkedHashMap<>();
            FALLBACK_DATA = new LinkedHashMap<>();
            DATA_BRANCHES = new LinkedHashMap<>();
        } else {
            DATA = new HashMap<>();
            FALLBACK_DATA = new HashMap<>();
            DATA_BRANCHES = new HashMap<>();
        }

        deserialize(jsonString);
    }

    public void deserialize(String jsonString) {
        if (!Strings.isBlank(jsonString)) {
            JsonElement element = JSON_PARSER.parse(jsonString);

            if (ConfigHelper.isObject(element)) {
                for (Map.Entry<String, JsonElement> entry : element.getAsJsonObject().entrySet()) {
                    DATA.put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    public JsonElement serialize() {
        JsonObject object = new JsonObject();

        for (Map.Entry<String, Config> entry : DATA_BRANCHES.entrySet()) {
            if (hasData(entry.getKey())) {
                addData(entry.getKey(), entry.getValue().serialize());
            } else if (hasFallbackData(entry.getKey())) {
                addFallbackData(entry.getKey(), entry.getValue().serialize());
            }
        }

        for (Map.Entry<String, JsonElement> entry : DATA.entrySet()) {
            object.add(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, JsonElement> entry : FALLBACK_DATA.entrySet()) {
            if (!object.has(entry.getKey())) {
                object.add(entry.getKey(), entry.getValue());
            }
        }

        return object;
    }

    public void addData(String key, JsonElement element) {
        DATA.put(key, element);
    }

    private void addFallbackData(String key, JsonElement element) {
        FALLBACK_DATA.put(key, element);
    }

    public boolean hasData(String key) {
        return DATA.containsKey(key);
    }

    private boolean hasFallbackData(String key) {
        return FALLBACK_DATA.containsKey(key);
    }

    private boolean hasDataBranch(String key) {
        return DATA_BRANCHES.containsKey(key);
    }

    private JsonElement getData(String key) {
        return DATA.get(key);
    }

    public Map<String, JsonElement> getAllData() {
        return ImmutableMap.copyOf(DATA);
    }

    public void removeData(String key) {
        DATA.remove(key);
    }

    public int getInt(String key, int fallbackValue) {
        int value = getInt(key);

        if (value == -999) {
            addFallbackData(key, new JsonPrimitive(fallbackValue));
            return fallbackValue;
        }

        return value;
    }

    public float getFloat(String key, float fallbackValue) {
        float value = getFloat(key);

        if (value == -999.0F) {
            addFallbackData(key, new JsonPrimitive(fallbackValue));
            return fallbackValue;
        }

        return value;
    }

    public boolean getBoolean(String key, boolean fallbackValue) {
        boolean value = getBoolean(key);

        if (!ConfigHelper.isBoolean(getData(key))) {
            addFallbackData(key, new JsonPrimitive(fallbackValue));
            return fallbackValue;
        }

        return value;
    }

    public <E extends Enum> E getEnum(String key, Class<? extends E> enumClass, E fallbackValue) {
        E value = getEnum(key, enumClass);

        if (value == null) {
            addFallbackData(key, new JsonPrimitive(fallbackValue.name().toLowerCase()));
            return fallbackValue;
        }

        return value;
    }

    public IBlockState getBlock(String key, IBlockState fallbackValue) {
        IBlockState value = getBlock(key);

        if (value == null) {
            JsonObject block = new JsonObject();
            JsonObject properties = new JsonObject();
            block.addProperty("block", Objects.requireNonNull(fallbackValue.getBlock().getRegistryName()).toString());

            for (Map.Entry<IProperty<?>, Comparable<?>> entry : fallbackValue.getProperties().entrySet()) {
                properties.addProperty(entry.getKey().getName(), entry.getValue().toString().toLowerCase());
            }

            block.add("properties", properties);
            addFallbackData(key, block);
            return fallbackValue;
        }

        return value;
    }

    public ItemStack getItem(String key, ItemStack fallbackValue) {
        ItemStack value = getItem(key);

        if (value.isEmpty()) {
            JsonObject item = new JsonObject();
            item.addProperty("item", fallbackValue.getItem().getRegistryName().toString());
            item.addProperty("meta", fallbackValue.getItemDamage());
            addFallbackData(key, item);
            return fallbackValue;
        }

        return value;
    }

    public Config getDataBranch(String key, JsonObject fallbackValue) {
        Config value = getDataBranch(key);

        if (value == null) {
            addFallbackData(key, fallbackValue);
            return new Config(fallbackValue.toString(), keepDataOrder);
        }

        return value;
    }

    public String getString(String key) {
        if (ConfigHelper.isString(getData(key))) {
            return getData(key).getAsJsonPrimitive().getAsString();
        } else {
            return "MissingNo";
        }
    }

    public int getInt(String key) {
        if (ConfigHelper.isInt(getData(key))) {
            return getData(key).getAsJsonPrimitive().getAsInt();
        } else {
            return -999;
        }
    }

    private float getFloat(String key) {
        if (ConfigHelper.isFloat(getData(key))) {
            return getData(key).getAsJsonPrimitive().getAsFloat();
        } else {
            return -999.0F;
        }
    }

    public boolean getBoolean(String key) {
        if (ConfigHelper.isBoolean(getData(key))) {
            return getData(key).getAsJsonPrimitive().getAsBoolean();
        } else {
            return false;
        }
    }

    public <E extends Enum> E getEnum(String key, Class<? extends E> enumClass) {
        if (ConfigHelper.isString(getData(key))) {
            String enumIdentifier = getData(key).getAsJsonPrimitive().getAsString();

            if (enumIdentifier.equalsIgnoreCase("random") || enumIdentifier.equalsIgnoreCase("rand")) {
                return enumClass.getEnumConstants()[NumberHelper.getRand().nextInt(enumClass.getEnumConstants().length)];
            } else {
                for (E value : enumClass.getEnumConstants()) {
                    if (value.name().equalsIgnoreCase(enumIdentifier)) {
                        return value;
                    }
                }
            }
        }

        return null;
    }

    public ResourceLocation getResource(String key) {
        if (ConfigHelper.isString(getData(key))) {
            return new ResourceLocation(getString(key));
        }

        return null;
    }

    public IBlockState getBlock(String key) {
        JsonObject object;

        if (ConfigHelper.isObject(getData(key))) {
            object = getData(key).getAsJsonObject();
        } else {
            return null;
        }

        JsonElement blockName = null;

        if (ConfigHelper.isString(object.get("block"))) {
            blockName = object.get("block");

        } else if (ConfigHelper.isString(object.get("itemBlock"))) {
            blockName = object.get("itemBlock");
        }

        if (blockName != null) {
            Block block = Block.getBlockFromName(blockName.getAsJsonPrimitive().getAsString());

            if (block != null) {
                IBlockState state = block.getDefaultState();

                if (object.has("properties")) {
                    JsonElement properties = object.get("properties");

                    if (ConfigHelper.isObject(properties)) {
                        for (Map.Entry<String, JsonElement> entry : properties.getAsJsonObject().entrySet()) {
                            IProperty property = BlockStateHelper.getProperty(state, entry.getKey());

                            if (property != null && ConfigHelper.isString(entry.getValue())) {
                                Comparable propertyValue = BlockStateHelper.getPropertyValue(property, entry.getValue().getAsJsonPrimitive().getAsString());

                                if (propertyValue != null) {
                                    state = state.withProperty(property, propertyValue);
                                }
                            }
                        }
                    }
                }

                return state;
            }
        }

        return null;
    }

    public ItemStack getItem(String key) {
        Config itemConfig = getDataBranch(key);
        ItemStack stack = ItemStack.EMPTY;

        if (itemConfig != null) {
            ResourceLocation item = null;

            if (ConfigHelper.isString(itemConfig.getData("item"))) {
                item = itemConfig.getResource("item");
            } else if (ConfigHelper.isString(itemConfig.getData("itemBlock"))) {
                item = itemConfig.getResource("itemBlock");
            }

            if (item != null) {
                int meta = itemConfig.getInt("meta", 0);

                if (ForgeRegistries.ITEMS.containsKey(item)) {
                    stack = new ItemStack(Objects.requireNonNull(Item.getByNameOrId(item.toString())), 1, meta);
                } else if (ForgeRegistries.BLOCKS.containsKey(item)) {
                    IBlockState state = getBlock(key);
                    Block block = state.getBlock();
                    stack = new ItemStack(block, 1, block.getMetaFromState(state));
                }

                if (!stack.isEmpty()) {
                    if (ConfigHelper.isString(itemConfig.getData("displayName"))) {
                        stack.setStackDisplayName(itemConfig.getString("displayName"));
                    }

                    Config loreConfig = getDataBranch("lore");

                    if (loreConfig != null && loreConfig.getAllData().size() > 0) {
                        NBTHelper.setTagCompound(stack);
                        NBTTagList loreList = new NBTTagList();

                        for (Map.Entry<String, JsonElement> entry : loreConfig.getAllData().entrySet()) {
                            if (ConfigHelper.isString(entry.getValue())) {
                                loreList.appendTag(new NBTTagString(entry.getValue().getAsJsonPrimitive().getAsString()));
                            }
                        }

                        NBTTagCompound displayCompound = new NBTTagCompound();
                        displayCompound.setTag("Lore", loreList);
                        NBTTagCompound compound = new NBTTagCompound();
                        compound.setTag("display", displayCompound);
                        NBTHelper.setTagCompound(stack, compound);
                    }

                    List<Config> enchantmentConfigs = itemConfig.getDataBranches("enchantments");

                    if (enchantmentConfigs != null) {
                        for (Config enchantmentConfig : enchantmentConfigs) {
                            if (ConfigHelper.isString(enchantmentConfig.getData("enchantment"))) {
                                Enchantment enchantment = Enchantment.getEnchantmentByLocation(enchantmentConfig.getString("enchantment"));

                                if (enchantment != null) {
                                    int enchantmentLevel = NumberHelper.getNumberInRange(enchantmentConfig.getInt("minEnchantmentLevel", 1), enchantmentConfig.getInt("minEnchantmentLevel", 3), NumberHelper.getRand());

                                    if (stack.getItem() instanceof ItemEnchantedBook) {
                                        ItemEnchantedBook.addEnchantment(stack, new EnchantmentData(enchantment, enchantmentLevel));
                                    } else {
                                        stack.addEnchantment(enchantment, enchantmentLevel);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return stack;
    }

    public Config getDataBranch(String key) {
        if (hasDataBranch(key)) {
            return DATA_BRANCHES.get(key);
        } else if (ConfigHelper.isObject(getData(key))) {
            Config config = new Config(getData(key).toString(), keepDataOrder);
            DATA_BRANCHES.put(key, config);
            return config;
        }

        return null;
    }

    public List<Config> getDataBranches(String key, List<JsonObject> fallbackValue) {
        List<Config> value = getDataBranches(key);

        if (value == null) {
            JsonArray array = new JsonArray();
            fallbackValue.forEach(array::add);
            addFallbackData(key, array);

            List<Config> ret = new ArrayList<>();
            fallbackValue.forEach(k -> ret.add(new Config(k.toString(), keepDataOrder)));
            return ret;
        }

        return value;
    }

    private List<Config> getDataBranches(String key) {
        if (ConfigHelper.isArray(getData(key))) {
            JsonArray array = getData(key).getAsJsonArray();
            List<Config> subConfigs = new ArrayList<>();

            for (JsonElement element : array) {
                if (ConfigHelper.isObject(element)) {
                    subConfigs.add(new Config(element.toString(), keepDataOrder));
                }
            }

            return subConfigs;
        } else {
            return null;
        }
    }

    public void save(File configFile) {
        if (configFile != null) {
            if (configFile.getPath().startsWith("~")) {
                configFile = new File(configFile.getPath().replace("~", Reference.CONFIG_DIRECTORY.getPath()));
            }

            String jsonString = GSON.toJson(serialize());

            try {
                FileUtils.write(configFile, jsonString, Charset.defaultCharset());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}