package io.github.happyhippo77.witchery2.mixin;

import io.github.happyhippo77.witchery2.item.items.Earmuffs;
import io.github.happyhippo77.witchery2.util.MinecraftClientVariables;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin implements MinecraftClientVariables {
    @Shadow @Nullable public ClientPlayerEntity player;
    @Unique
    private boolean wearingEarmuffs = false;
    @Inject(method = "tick", at = @At("HEAD"))
    private void inject1(CallbackInfo ci) {
        this.setWearingEarmuffs(this.player != null && this.player.getInventory().getArmorStack(3).getItem() instanceof Earmuffs);
    }

    @Override
    public boolean getWearingEarmuffs() {
        return wearingEarmuffs;
    }

    @Override
    public void setWearingEarmuffs(boolean wearingEarmuffs) {
        this.wearingEarmuffs = wearingEarmuffs;
    }
}
