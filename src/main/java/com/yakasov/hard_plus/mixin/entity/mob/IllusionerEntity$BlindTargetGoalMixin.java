package com.yakasov.hard_plus.mixin.entity.mob;

import net.minecraft.entity.mob.IllusionerEntity;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(IllusionerEntity.BlindTargetGoal.class)
public class IllusionerEntity$BlindTargetGoalMixin {
    @Redirect(
            method = "canStart",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/LocalDifficulty;isHarderThan(F)Z"
            )
    )
    private boolean forceIsHarderThanCheck(LocalDifficulty instance, float difficulty) {
        if (instance.getGlobalDifficulty() == Difficulty.HARD) {
            return true;
        }

        return instance.getLocalDifficulty() > difficulty;
    }
}
