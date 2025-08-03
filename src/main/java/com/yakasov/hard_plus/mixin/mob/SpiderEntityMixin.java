package com.yakasov.hard_plus.mixin.mob;

import net.minecraft.entity.mob.SpiderEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(SpiderEntity.class)
public class SpiderEntityMixin {
    @ModifyConstant(
            method = "initialize",
            constant = @Constant(
                    floatValue = 0.1F
            )
    )
    private float increaseStatusEffectChance(float f) {
        // Original check already ensure difficulty is HARD so we can omit that here
        return 0.25F;
    }
}

