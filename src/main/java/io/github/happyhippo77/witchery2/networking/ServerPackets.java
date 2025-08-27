package io.github.happyhippo77.witchery2.networking;

import io.github.happyhippo77.witchery2.block.entity.entities.WitchsCauldronEntity;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;

public class ServerPackets {

    public static void sendRenderParticle(PlayerEntity player, ParticleEffect particleEffect, float x, float y, float z, float xd, float yd, float zd) {
        PacketByteBuf buf = PacketByteBufs.create();

        buf.writeVarInt(Registries.PARTICLE_TYPE.getRawId(particleEffect.getType()));
        buf.writeFloat(x);
        buf.writeFloat(y);
        buf.writeFloat(z);
        buf.writeFloat(xd);
        buf.writeFloat(yd);
        buf.writeFloat(zd);

        ServerPlayNetworking.send((ServerPlayerEntity) player, PacketIdentifiers.RENDER_PARTICLE, buf);
    }

    public static void sendCauldronClientUpdate(WitchsCauldronEntity cauldron, boolean renderBubbles, boolean playBlop, boolean renderMagic, boolean renderRitual, int ritualSeconds) {
        PacketByteBuf buf = PacketByteBufs.create();

        buf.writeBlockPos(cauldron.getPos());
        buf.writeBoolean(renderBubbles);
        buf.writeBoolean(playBlop);
        buf.writeBoolean(renderMagic);
        buf.writeBoolean(renderRitual);
        buf.writeInt(ritualSeconds);

        if (cauldron.getWorld() != null) {
            for (PlayerEntity player : cauldron.getWorld().getPlayers())
                ServerPlayNetworking.send((ServerPlayerEntity) player, PacketIdentifiers.CAULDRON_CLIENT_UPDATE, buf);
        }
    }
}
