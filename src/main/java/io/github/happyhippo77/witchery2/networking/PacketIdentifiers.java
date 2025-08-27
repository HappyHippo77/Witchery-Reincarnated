package io.github.happyhippo77.witchery2.networking;

import io.github.happyhippo77.witchery2.Witchery2;
import net.minecraft.util.Identifier;

public class PacketIdentifiers {
    public static final Identifier RENDER_PARTICLE = new Identifier(Witchery2.MOD_ID, "render_particle");
    public static final Identifier CAULDRON_CLIENT_UPDATE = new Identifier(Witchery2.MOD_ID, "cauldron_client_update");
}
