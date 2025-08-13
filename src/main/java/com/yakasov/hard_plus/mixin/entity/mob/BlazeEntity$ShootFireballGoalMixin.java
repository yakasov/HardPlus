package com.yakasov.hard_plus.mixin.entity.mob;

import net.minecraft.entity.mob.BlazeEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BlazeEntity.ShootFireballGoal.class)
public class BlazeEntity$ShootFireballGoalMixin {
    @ModifyConstant(
            method = "tick",
            constant = @Constant(
                    intValue = 4
            )
    )
    private int increaseFireballCount(int i) {
        return 6;
    }

    @ModifyConstant(
            method = "tick",
            constant = @Constant(
                    intValue = 100
            )
    )
    private int increaseFireballCooldown(int i) {
        return 140;
    }
}
