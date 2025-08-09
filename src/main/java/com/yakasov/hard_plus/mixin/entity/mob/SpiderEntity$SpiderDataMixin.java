package com.yakasov.hard_plus.mixin.entity.mob;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SpiderEntity.SpiderData.class)
public class SpiderEntity$SpiderDataMixin {
    @ModifyVariable(
            method = "setEffect",
            at = @At("STORE")
    )
    private int increasePotionEffectCount(int i, @Local(argsOnly = true) Random random) {
        return random.nextInt(8);
    }

    @Inject(
            method = "setEffect",
            at = @At("TAIL")
    )
    private void addEffects(Random random, CallbackInfo ci, @Local int i) {
        SpiderEntity.SpiderData spiderData = (SpiderEntity.SpiderData)(Object)this;

        if (i == 5) {
            spiderData.effect = StatusEffects.RESISTANCE;
        } else if (i == 6) {
            spiderData.effect = StatusEffects.FIRE_RESISTANCE;
        } else if (i == 7) {
            spiderData.effect = StatusEffects.INFESTED;
        } else if (i == 8) {
            spiderData.effect = StatusEffects.OOZING;
        } else if (i == 9) {
            spiderData.effect = StatusEffects.WEAVING;
        }
    }
}
