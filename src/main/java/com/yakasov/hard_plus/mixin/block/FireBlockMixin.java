package com.yakasov.hard_plus.mixin.block;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.FireBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(FireBlock.class)
public class FireBlockMixin {
    @ModifyVariable(
            method = "scheduledTick",
            at = @At("STORE"),
            name = "q",
            ordinal = 0
    )
    private int increaseFireEncouragementChance(int q, @Local(argsOnly = true) ServerWorld world) {
        if (world.getDifficulty() == Difficulty.HARD) {
            q = (int) Math.ceil(q * 1.25);
        }
        return q;
    }
}
