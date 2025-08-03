package com.yakasov.hard_plus.mixin.entity.mob;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.StrayEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LightType;
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
        if (world.getDifficulty() == Difficulty.HARD) {
            return true;
        }

        return world.getLightLevel(LightType.SKY, pos) >= 15;
    }

    @ModifyArg(
            method = "createArrowProjectile",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/projectile/ArrowEntity;addEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;)V"
            )
    )
    private StatusEffectInstance setAmplifierToTwo(StatusEffectInstance effect) {
        StrayEntity strayEntity = (StrayEntity)(Object)this;

        if (strayEntity.getWorld().getDifficulty() == Difficulty.HARD) {
            effect = new StatusEffectInstance(StatusEffects.SLOWNESS, 400, 1);
        }

        return effect;
    }
}
