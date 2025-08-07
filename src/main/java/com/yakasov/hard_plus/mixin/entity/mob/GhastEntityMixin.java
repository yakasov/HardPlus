package com.yakasov.hard_plus.mixin.entity.mob;

import net.minecraft.entity.mob.GhastEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GhastEntity.class)
public class GhastEntityMixin {
    @Inject(
            method = "getLimitPerChunk",
            at = @At("HEAD"),
            cancellable = true
    )
    private void increaseLimitPerChunk(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(2);
    }
}
