package me.iru.waxednotwaxed.config

import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config

@Config(name = "waxednotwaxed")
class WaxedNotWaxedConfig : ConfigData {
    var enabled: Boolean = false
    var onlyShowWhenHoldingAxe: Boolean = false

    fun save() {
        AutoConfig.getConfigHolder(this::class.java).save()
    }
}