package me.iru.waxednotwaxed.events

import me.iru.waxednotwaxed.Utils
import me.iru.waxednotwaxed.WaxedNotWaxed
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
import net.minecraft.block.OxidizableBlock
import net.minecraft.block.OxidizableSlabBlock
import net.minecraft.block.OxidizableStairsBlock
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.util.Identifier

class HudRenderHandler : HudRenderCallback {

    override fun onHudRender(drawContext: DrawContext?, tickDelta: Float) {
        if(drawContext == null || !WaxedNotWaxed.toggledCached) return
        val lookingAt = Utils.getBlockAtCrosshair() ?: return
        if (lookingAt.name.toString().lowercase().contains("waxed")) render(drawContext, true)
        else if(lookingAt is OxidizableBlock || lookingAt is OxidizableSlabBlock || lookingAt is OxidizableStairsBlock) {
              render(drawContext, false)
        }
    }

    private fun render(drawContext: DrawContext, waxed: Boolean) {
        val mc = MinecraftClient.getInstance()
        val screenCenter = Utils.getCrosshairLocation()
        drawContext.drawTextWithShadow(
            mc.textRenderer,
            if(waxed) "Waxed" else "Not Waxed",
            screenCenter.x.toInt() + 18,
            screenCenter.y.toInt() + 6,
            if(waxed) 0xf78204 else 0x09a073
        )
        drawContext.drawTexture(
            Identifier("minecraft", if(waxed) "textures/item/honeycomb.png" else "textures/item/iron_axe.png"),
            screenCenter.x.toInt() + 5,
            screenCenter.y.toInt() + 5,
            0.0f,
            0.0f,
            10,
            10,
            10,
            10
        )
    }
}