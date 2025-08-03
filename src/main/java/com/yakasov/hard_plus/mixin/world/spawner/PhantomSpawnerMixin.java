package com.yakasov.hard_plus.mixin.world.spawner;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.spawner.PhantomSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PhantomSpawner.class)
public class PhantomSpawnerMixin {
    @ModifyVariable(
            method = "spawn",
            at = @At("STORE"),
            name = "k"
    )
    private int increasePhantomGroupSize(int k, @Local LocalDifficulty localDifficulty, @Local Random random) {
        if (localDifficulty.getGlobalDifficulty() == Difficulty.HARD) {
            k = 2 + random.nextInt(5);
        }

        return k;
    }
}
