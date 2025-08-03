package com.yakasov.hard_plus.mixin.entity.mob;

import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(PhantomEntity.class)
public class PhantomEntityMixin {
    @ModifyArg(
            method = "setPhantomSize",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/math/MathHelper;clamp(III)I"
            ),
            index = 2
    )
    private int increaseMaxPhantomSize(int max) {
        PhantomEntity phantomEntity = (PhantomEntity)(Object)this;

        if (phantomEntity.getWorld().getDifficulty() == Difficulty.HARD) {
            max = 160;
        }

        return max;
    }
}
