package me.iru.waxednotwaxed

import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config

@Config(name = "waxednotwaxed")
class ToggleConfig : ConfigData {
    var toggled = false
}