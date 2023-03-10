package me.wally.skratch.client;

import me.wally.skratch.client.ui.PingPongScreen;
import me.wally.skratch.client.util.NetworkManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

public class SkratchClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        KeyBinding menuBind = KeyBindingHelper.registerKeyBinding(new KeyBinding("Skratch Menu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_M, "Skratch"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (menuBind.wasPressed()) {

                CompletableFuture<PacketByteBuf> future = new CompletableFuture<>();
                NetworkManager.watch(new Identifier("skratch:generic-handshake-packet"), future);
                AtomicReference<PacketByteBuf> bufRef = new AtomicReference<>(null);

                new Thread(() -> {
                    try {
                        bufRef.set(future.get(5, TimeUnit.SECONDS));
                    } catch (ExecutionException | TimeoutException | InterruptedException e) {
                        client.player.sendMessage(Text.literal("Server didn't pong back."), false);
                    }
                }).start();

                PacketByteBuf buf = bufRef.get();

                client.setScreen(new PingPongScreen());

            }
        });

    }

}
