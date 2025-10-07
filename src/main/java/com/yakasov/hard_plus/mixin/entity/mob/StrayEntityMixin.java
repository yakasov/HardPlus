package com.yakasov.hard_plus.mixin.entity.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.StrayEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.entity.mob.HostileEntity.canSpawnInDark;

@Mixin(StrayEntity.class)
public class StrayEntityMixin {
    @Inject(
            method = "canSpawn",
            at = @At("HEAD"),
            cancellable = true)
    private static void forceIsSkyVisible(EntityType<StrayEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(canSpawnInDark(type, world, spawnReason, pos, random) && (SpawnReason.isAnySpawner(spawnReason)));
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
