package me.iru.waxednotwaxed.events

import me.iru.waxednotwaxed.WaxedNotWaxed
import me.shedaniel.autoconfig.AutoConfig
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.minecraft.client.MinecraftClient
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents

fun EndClientTickHandler(): ClientTickEvents.EndTick {
    return ClientTickEvents.EndTick {
        while(WaxedNotWaxed.keyBinding!!.wasPressed()) {
            val newValue = !WaxedNotWaxed.toggledCached
            WaxedNotWaxed.toggledCached = newValue
            WaxedNotWaxed.config!!.config.toggled = newValue
            WaxedNotWaxed.config!!.save()
            val mc = MinecraftClient.getInstance()
            val p = mc.player!!
            mc.world!!.playSound(p, p.blockPos, SoundEvents.UI_BUTTON_CLICK, SoundCategory.MASTER, 0.1f, 1f)
        }
    }
}