package io.github.happyhippo77.witchery2.networking;

import io.github.happyhippo77.witchery2.block.entity.entities.WitchsCauldronEntity;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockPos;

public class ClientPackets {
    public static void initialize() {
        ClientPlayNetworking.registerGlobalReceiver(PacketIdentifiers.RENDER_PARTICLE, ClientPackets::handleRenderParticle);
        ClientPlayNetworking.registerGlobalReceiver(PacketIdentifiers.CAULDRON_CLIENT_UPDATE, ClientPackets::handleCauldronClientUpdate);
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

    public static void handleCauldronClientUpdate(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        BlockPos cauldronPos = buf.readBlockPos();
        boolean renderBubbles = buf.readBoolean();
        boolean playBlop = buf.readBoolean();
        boolean renderMagic = buf.readBoolean();
        boolean renderRitual = buf.readBoolean();
        int ritualSeconds = buf.readInt();

        if (client.world != null) {
            WitchsCauldronEntity cauldron = (WitchsCauldronEntity) client.world.getBlockEntity(cauldronPos);

            if (cauldron != null) {
                cauldron.clientRenderBubbles = renderBubbles;
                cauldron.clientPlayBlop = playBlop;
                cauldron.clientRenderMagic = renderMagic;
                cauldron.clientRenderRitual = renderRitual;
                cauldron.clientRitualSeconds = ritualSeconds;
            }
        }
    }
}
