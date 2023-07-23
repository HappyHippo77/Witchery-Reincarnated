package io.github.happyhippo77.witchery2.mixin;

import io.github.happyhippo77.witchery2.util.MinecraftClientVariables;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.sound.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundManager.class)
public abstract class SoundManagerMixin {

    @Inject(method = "play(Lnet/minecraft/client/sound/SoundInstance;)V", at = @At("HEAD"), cancellable = true)
    private void inject1(SoundInstance sound, CallbackInfo ci) {
        if (((MinecraftClientVariables) MinecraftClient.getInstance()).getWearingEarmuffs()) {
            if (!sound.getCategory().equals(SoundCategory.MASTER) && !sound.getCategory().equals(SoundCategory.MUSIC) && !sound.getCategory().equals(SoundCategory.VOICE)) {
                ci.cancel();
            }
        }
    }
}
