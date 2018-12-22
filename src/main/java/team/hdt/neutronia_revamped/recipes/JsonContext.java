package team.hdt.neutronia_revamped.recipes;

import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;

import javax.annotation.Nullable;
import java.util.Map;

public class JsonContext
{
    private String modId;
    private Map<String, Ingredient> constants = Maps.newHashMap();

    public JsonContext(String modId)
    {
        this.modId = modId;
    }

    public String getModId()
    {
        return this.modId;
    }

    public String appendModId(String data)
    {
        if (data.indexOf(':') == -1)
            return modId + ":" + data;
        return data;
    }

    @Nullable
    public Ingredient getConstant(String name)
    {
        return constants.get(name);
    }

    void loadConstants(JsonObject... jsons)
    {
        for (JsonObject json : jsons)
        {
            if (json.has("conditions") && !CraftingHelper.processConditions(json.getAsJsonArray("conditions"), this))
                continue;
            if (!json.has("ingredient"))
                throw new JsonSyntaxException("Constant entry must contain 'ingredient' value");
            constants.put(JsonUtils.getString(json, "name"), CraftingHelper.getIngredient(json.get("ingredient"), this));
        }

    }
}