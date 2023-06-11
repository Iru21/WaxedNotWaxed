package me.iru.waxednotwaxed

import me.iru.waxednotwaxed.config.WaxedNotWaxedConfig
import me.iru.waxednotwaxed.events.EndClientTickHandler
import me.iru.waxednotwaxed.events.HudRenderHandler
import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import org.lwjgl.glfw.GLFW

object WaxedNotWaxed : ClientModInitializer {

    var keyBinding: KeyBinding? = null
    lateinit var config: WaxedNotWaxedConfig

    override fun onInitializeClient() {
        AutoConfig.register(
            WaxedNotWaxedConfig::class.java
        ) { definition: Config?, configClass: Class<WaxedNotWaxedConfig?>? ->
            JanksonConfigSerializer(
                definition,
                configClass
            )
        }
        this.config = AutoConfig.getConfigHolder(WaxedNotWaxedConfig::class.java).get()

        this.keyBinding = KeyBindingHelper.registerKeyBinding(
            KeyBinding(
                "key.waxednotwaxed.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_Z,
                "category.waxednotwaxed.keybindcategory"
            )
        )

        HudRenderCallback.EVENT.register(HudRenderHandler())
        ClientTickEvents.END_CLIENT_TICK.register(EndClientTickHandler())
    }
}