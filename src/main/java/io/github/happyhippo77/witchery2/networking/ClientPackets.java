package io.github.happyhippo77.witchery2.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.Registries;

public class ClientPackets {
    public static void initialize() {
        ClientPlayNetworking.registerGlobalReceiver(PacketIdentifiers.RENDER_PARTICLE, ClientPackets::handleRenderParticle);
    }
    public static void handleRenderParticle(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        int id = buf.readVarInt();
        float x = buf.readFloat();
        float y = buf.readFloat();
        float z = buf.readFloat();
        float xd = buf.readFloat();
        float yd = buf.readFloat();
        float zd = buf.readFloat();

        ParticleEffect particleEffect = (ParticleEffect) Registries.PARTICLE_TYPE.get(id);

        client.execute(() -> {
            client.world.addParticle(particleEffect, x, y, z, xd, yd, zd);
        });
    }
}
