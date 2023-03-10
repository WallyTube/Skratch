package me.wally.skratch.client.util;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class NetworkManager {

    public static void watch(Identifier identifier, CompletableFuture<PacketByteBuf> future) {
        ClientPlayNetworking.registerGlobalReceiver(identifier, (client, handler, buf, responseSender) -> {
            client.execute(() -> {
                future.complete(buf);
            });
        });
    }

}