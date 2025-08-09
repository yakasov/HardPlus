package com.yakasov.hard_plus.mixin.entity.mob;

import net.minecraft.entity.ai.goal.BowAttackGoal;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.HostileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractSkeletonEntity.class)
public class AbstractSkeletonEntityMixin {
    @Redirect(
            method = "<init>",
            at = @At(
                    value = "NEW",
                    target = "net/minecraft/entity/ai/goal/BowAttackGoal"
            )
    )
    private BowAttackGoal<AbstractSkeletonEntity> redirectBowAttackGoal(
            HostileEntity actor, double speed, int attackInterval, float range
    ) {
        return new BowAttackGoal<>((AbstractSkeletonEntity) actor, speed, attackInterval, 20.0F);
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
