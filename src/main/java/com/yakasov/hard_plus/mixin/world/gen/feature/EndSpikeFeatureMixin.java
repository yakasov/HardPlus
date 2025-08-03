package com.yakasov.hard_plus.mixin.world.gen.feature;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.gen.feature.EndSpikeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EndSpikeFeature.SpikeCache.class)
public class EndSpikeFeatureMixin {
    @ModifyVariable(
            method = "load(Ljava/lang/Long;)Ljava/util/List;",
            at = @At("STORE"),
            name = "bl"
    )
    private boolean increaseGuardedCrystalCount(boolean bl, @Local(ordinal = 0) int l) {
        return l <= 5;
    }
}
