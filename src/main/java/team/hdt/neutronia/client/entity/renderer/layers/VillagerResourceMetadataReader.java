package team.hdt.neutronia.client.entity.renderer.layers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.minecraft.client.resources.data.BaseMetadataSectionSerializer;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Type;

@SideOnly(Side.CLIENT)
public class VillagerResourceMetadataReader extends BaseMetadataSectionSerializer<VillagerResourceMetadata> {

    @Override
    public String getSectionName() {
        return "villager";
    }

    @Override
    public VillagerResourceMetadata deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new VillagerResourceMetadata(VillagerResourceMetadata.HatType.from(JsonUtils.getString(json.getAsJsonObject(), "hat", "none")));
    }

}
