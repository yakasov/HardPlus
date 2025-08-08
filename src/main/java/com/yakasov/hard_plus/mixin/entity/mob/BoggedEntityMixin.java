package com.yakasov.hard_plus.mixin.entity.mob;

import net.minecraft.entity.mob.BoggedEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoggedEntity.class)
public class BoggedEntityMixin {
    @Inject(
            method = "getRegularAttackInterval",
            at = @At("HEAD"),
            cancellable = true
    )
    protected void decreaseRegularAttackInterval(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(60);
    }

    @Inject(
            method = "getHardAttackInterval",
            at = @At("HEAD"),
            cancellable = true
    )
    protected void decreaseHardAttackInterval(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(40);
    }
}
