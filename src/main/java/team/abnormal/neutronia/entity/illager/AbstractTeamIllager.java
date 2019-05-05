package team.abnormal.neutronia.entity.illager;

import net.minecraft.entity.monster.AbstractIllager;
import net.minecraft.world.World;
import team.abnormal.neutronia.base.entity.ITeamIllager;

public abstract class AbstractTeamIllager extends AbstractIllager implements ITeamIllager {
    public AbstractTeamIllager(World world) {
        super(world);
    }

    @Override
    public void onJoinRaid() {

    }

}
