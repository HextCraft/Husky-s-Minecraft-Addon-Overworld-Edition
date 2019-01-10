package team.abnormal.neutronia.entity.villagers;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.abnormal.neutronia.entity.EntityVillager;
import team.abnormal.neutronia.village.VillagerData;
import team.abnormal.neutronia.village.VillagerProfession;
import team.abnormal.neutronia.village.VillagerType;

public class EntityVillagerGuard extends EntityVillager {

    public EntityVillagerGuard(World worldIn) {
        this(VillagerType.forBiome(worldIn.getBiome(new BlockPos(0, 0, 0))), worldIn);
    }

    public EntityVillagerGuard(VillagerType type, World worldIn) {
        super(new VillagerData(type, VillagerProfession.GUARD, 0), worldIn);
    }

}
