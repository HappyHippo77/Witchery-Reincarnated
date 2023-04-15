package io.github.happyhippo77.witchery2.sounds;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final Identifier RANDOM_BLOP_IDENTIFIER = new Identifier("witchery2:random.blop");
    public static final Identifier RANDOM_SPLASH_IDENTIFIER = new Identifier("witchery2:random.splash");
    public static SoundEvent RANDOM_BLOP = SoundEvent.of(RANDOM_BLOP_IDENTIFIER);
    public static SoundEvent RANDOM_SPLASH = SoundEvent.of(RANDOM_SPLASH_IDENTIFIER);
    public static void registerAllSounds() {
        Registry.register(Registries.SOUND_EVENT, RANDOM_BLOP_IDENTIFIER, RANDOM_BLOP);
        Registry.register(Registries.SOUND_EVENT, RANDOM_SPLASH_IDENTIFIER, RANDOM_SPLASH);
    }
}
