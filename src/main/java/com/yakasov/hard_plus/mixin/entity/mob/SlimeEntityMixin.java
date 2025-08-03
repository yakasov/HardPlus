package com.yakasov.hard_plus.mixin.entity.mob;

import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SlimeEntity.class)
public class SlimeEntityMixin {
    @Inject(
            method = "canAttack",
            at = @At("HEAD"),
            cancellable = true
    )
    private void allowSmallSlimesToAttack(CallbackInfoReturnable<Boolean> cir) {
        SlimeEntity slimeEntity = (SlimeEntity)(Object)this;

        if (slimeEntity.getWorld().getDifficulty() == Difficulty.HARD) {
            cir.setReturnValue(slimeEntity.canActVoluntarily());
        }
    }
}
