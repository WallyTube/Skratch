package me.wally.skratch.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class SkratchClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        KeyBinding menuBind = KeyBindingHelper.registerKeyBinding(new KeyBinding("Skratch Menu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_M, "Skratch"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (menuBind.wasPressed()) {
                client.player.sendMessage(Text.literal("Menu bind was pressed!"), false);
            }
        });

    }

}
