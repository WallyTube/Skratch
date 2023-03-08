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

        KeyBinding binding1 = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.fabric-key-binding-api-v1-testmod.test_keybinding_1", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_P, "key.category.first.test"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (binding1.wasPressed()) {
                client.player.sendMessage(Text.literal("Key 1 was pressed!"), false);
            }
        });

    }

}
