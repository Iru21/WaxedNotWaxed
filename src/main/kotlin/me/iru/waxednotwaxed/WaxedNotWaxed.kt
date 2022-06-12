package me.iru.waxednotwaxed

import me.iru.waxednotwaxed.events.EndClientTickHandler
import me.iru.waxednotwaxed.events.HudRenderHandler
import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.ConfigHolder
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import org.lwjgl.glfw.GLFW


object WaxedNotWaxed : ModInitializer {
    private const val modName = "Waxed Not Waxed"
    private const val version = "1.1a"

    var keyBinding: KeyBinding? = null
    var config: ConfigHolder<ToggleConfig>? = null
    var toggledCached: Boolean = false

    override fun onInitialize() {
        println("[$modName] Enabling $version")
        AutoConfig.register(
            ToggleConfig::class.java
        ) { definition: Config?, configClass: Class<ToggleConfig?>? ->
            JanksonConfigSerializer(
                definition,
                configClass
            )
        }
        this.config = AutoConfig.getConfigHolder(ToggleConfig::class.java)
        this.toggledCached = this.config!!.get().toggled

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