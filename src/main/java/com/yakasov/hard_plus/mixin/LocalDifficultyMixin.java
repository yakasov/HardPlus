package com.yakasov.hard_plus.mixin;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LocalDifficulty.class)
public class LocalDifficultyMixin {
    @Inject(
            method = "setLocalDifficulty",
            at = @At("HEAD"),
            cancellable = true
    )
    private void overrideLocalDifficultyCalculation(Difficulty difficulty, long totalDaytime, long inhabitedTime, float moonSize, CallbackInfoReturnable<Float> cir) {
        // moonSize is irrelevant and acts as 1
        // daytimeFactor is multiplied by 0.5 instead of 0.25
        // chunkFactor inhabitedTime is divided by 25 instead of 150
        // resultantDifficulty starts at 1.0 instead of 0.75
        if (difficulty == Difficulty.PEACEFUL) {
            cir.setReturnValue(0.0F);
        } else {
            final float dayTicks = 24000.0F;

            boolean bl = difficulty == Difficulty.HARD;
            float resultantDifficulty = 1.0F;
            float daytimeFactor = MathHelper.clamp(((float) totalDaytime - (dayTicks * 3)) / (dayTicks * 60), 0.0F, 1.0F) * 0.5F;
            resultantDifficulty += daytimeFactor;
            float chunkFactor = 0.0F;
            chunkFactor += MathHelper.clamp((float)inhabitedTime / (dayTicks * 25), 0.0F, 1.0F) * (bl ? 1.0F : 0.75F);
            chunkFactor += MathHelper.clamp(0.25F, 0.0F, daytimeFactor);
            if (difficulty == Difficulty.EASY) {
                chunkFactor *= 0.5F;
            }

            resultantDifficulty += chunkFactor;
            cir.setReturnValue(difficulty.getId() * resultantDifficulty);
        }
    }
}
