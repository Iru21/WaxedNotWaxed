package me.iru.waxednotwaxed

import net.minecraft.block.Block
import net.minecraft.client.MinecraftClient
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.hit.HitResult
import net.minecraft.util.math.Vec2f


fun getBlockAtCrosshair(): Block? {
    val client: MinecraftClient = MinecraftClient.getInstance()
    val hit: HitResult? = client.crosshairTarget
    if(hit != null && hit.type == HitResult.Type.BLOCK) {
        val blockHit = hit as BlockHitResult
        val blockPos = blockHit.blockPos
        val blockState = client.world!!.getBlockState(blockPos)
        return blockState.block
    }
    return null
}

fun getCrosshairLocation(): Vec2f {
    val mc = MinecraftClient.getInstance()
    val x = mc.getWindow().getScaledWidth().toFloat() / 2.0f
    val y = mc.getWindow().getScaledHeight().toFloat() / 2.0f
    return Vec2f(x, y)
}