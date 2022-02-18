package me.iru.waxednotwaxed.events

import com.mojang.blaze3d.systems.RenderSystem
import me.iru.waxednotwaxed.WaxedNotWaxed
import me.iru.waxednotwaxed.getBlockAtCrosshair
import me.iru.waxednotwaxed.getCrosshairLocation
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
import net.minecraft.block.OxidizableBlock
import net.minecraft.block.OxidizableSlabBlock
import net.minecraft.block.OxidizableStairsBlock
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawableHelper
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier

class HudRenderHandler : HudRenderCallback {
    override fun onHudRender(matrixStack: MatrixStack?, tickDelta: Float) {
        if(matrixStack == null || !WaxedNotWaxed.toggledCached) return
        val lookingAt = getBlockAtCrosshair()
        if (lookingAt == null) return
        if (lookingAt.name.toString().lowercase().contains("waxed")) render(matrixStack, true)
        else if(lookingAt is OxidizableBlock || lookingAt is OxidizableSlabBlock || lookingAt is OxidizableStairsBlock) {
              render(matrixStack, false)
        }
    }

    private fun render(matrixStack: MatrixStack, waxed: Boolean) {
        val mc = MinecraftClient.getInstance()
        val screenCenter = getCrosshairLocation()
        mc.textRenderer.drawWithShadow(
            matrixStack,
            if(waxed) "Waxed" else "Not Waxed",
            screenCenter.x + 18,
            screenCenter.y + 6,
            if(waxed) 0xf78204 else 0x09a073
        )
        RenderSystem.enableBlend()
        RenderSystem.setShader(GameRenderer::getPositionTexShader)
        RenderSystem.setShaderTexture(0, Identifier("minecraft", if(waxed) "textures/item/honeycomb.png" else "textures/item/iron_axe.png"))
        DrawableHelper.drawTexture(
            matrixStack,
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