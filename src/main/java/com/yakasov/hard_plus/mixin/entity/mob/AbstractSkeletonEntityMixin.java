package com.yakasov.hard_plus.mixin.entity.mob;

import net.minecraft.entity.mob.AbstractSkeletonEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(AbstractSkeletonEntity.class)
public class AbstractSkeletonEntityMixin {
    @ModifyArg(
            method = "<init>",
            at = @At(
                    value = "NEW",
                    target = "net/minecraft/entity/ai/goal/BowAttackGoal"
            ),
            index = 0
    )
    private float increaseAttackGoalRange(float range) {
        return 20.0F;
    }

    @ModifyArg(
            method = "initialize",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/mob/AbstractSkeletonEntity;setCanPickUpLoot(Z)V"
            )
    )
    private boolean ensureSkeletonCanPickUpLoot(boolean par1) {
        return true;
    }
}
