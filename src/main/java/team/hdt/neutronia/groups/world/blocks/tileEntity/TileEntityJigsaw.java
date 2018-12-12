package team.hdt.neutronia.groups.world.blocks.tileEntity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class TileEntityJigsaw extends TileEntity {

    private ResourceLocation attachementType;
    private ResourceLocation targetPool;
    private String finalState;

    public TileEntityJigsaw() {
        this.attachementType = new ResourceLocation("empty");
        this.targetPool = new ResourceLocation("empty");
        this.finalState = "minecraft:air";
    }

    public ResourceLocation getAttachementType() {
        return attachementType;
    }

    public ResourceLocation getTargetPool() {
        return targetPool;
    }

    public String getFinalState() {
        return finalState;
    }

    public void setAttachementType(ResourceLocation attachementType) {
        this.attachementType = attachementType;
    }

    public void setTargetPool(ResourceLocation targetPool) {
        this.targetPool = targetPool;
    }

    public void setFinalState(String finalState) {
        this.finalState = finalState;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setString("attachement_type", this.attachementType.toString());
        compound.setString("target_pool", this.targetPool.toString());
        compound.setString("final_state", this.finalState);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.attachementType = new ResourceLocation(compound.getString("attachement_type"));
        this.targetPool = new ResourceLocation(compound.getString("target_pool"));
        this.finalState = compound.getString("final_state");
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 12, this.getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

}