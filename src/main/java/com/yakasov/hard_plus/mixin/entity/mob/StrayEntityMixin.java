package com.yakasov.hard_plus.mixin.entity.mob;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.StrayEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(StrayEntity.class)
public class StrayEntityMixin {
    @Redirect(
            method = "canSpawn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/ServerWorldAccess;isSkyVisible(Lnet/minecraft/util/math/BlockPos;)Z"
            )
    )
    private static boolean forceIsSkyVisible(ServerWorldAccess world, BlockPos pos) {
        return true;
    }

    @ModifyArg(
            method = "createArrowProjectile",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/projectile/ArrowEntity;addEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;)V"
            )
    )
    private StatusEffectInstance setAmplifierToTwo(StatusEffectInstance effect) {
        return new StatusEffectInstance(StatusEffects.SLOWNESS, 400, 1);
    }
}
