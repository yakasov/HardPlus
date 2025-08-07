package com.yakasov.hard_plus.mixin.entity.passive;

import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VillagerEntity.class)
public class VillagerEntityMixin {
    @Inject(
            method = "getReputation",
            at = @At("RETURN"),
            cancellable = true
    )
    private void capReputation(PlayerEntity player, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(Math.min(35, cir.getReturnValue()));
    }
}
