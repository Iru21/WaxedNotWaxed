package me.iru.waxednotwaxed.events

import me.iru.waxednotwaxed.WaxedNotWaxed
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.minecraft.client.MinecraftClient
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents

fun EndClientTickHandler(): ClientTickEvents.EndTick {
    return ClientTickEvents.EndTick {
        while(WaxedNotWaxed.keyBinding!!.wasPressed()) {
            val newValue = !WaxedNotWaxed.config.enabled
            WaxedNotWaxed.config.enabled = newValue
            WaxedNotWaxed.config.save()
            val mc = MinecraftClient.getInstance()
            val p = mc.player!!
            mc.world!!.playSound(p, p.blockPos, SoundEvents.UI_BUTTON_CLICK.value(), SoundCategory.MASTER, 0.1f, 1f)
        }
    }
}