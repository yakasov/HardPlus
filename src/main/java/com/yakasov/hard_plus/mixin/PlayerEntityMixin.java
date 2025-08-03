package com.yakasov.hard_plus.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @ModifyVariable(
            method = "damage(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/damage/DamageSource;F)Z",
            at = @At("STORE"),
            ordinal = 0,
            argsOnly = true
    )
    private float setDamageMultiplier(float amount, @Local(argsOnly = true) ServerWorld world) {
        if (world.getDifficulty() == Difficulty.HARD) {
            amount *= (1.0F + (1.0F / 3.0F));
        }
        return amount;
    }

    @ModifyVariable(
            method = "takeShieldHit",
            at = @At("STORE"),
            ordinal = 0,
            name = "f"
    )
    private float setDisableBlocking(float f) {
        return Math.max(f, 0.5F);
    }

    @Inject(
            method = "getExperienceToDrop",
            at = @At("HEAD"),
            cancellable = true)
    private void uncapExperienceDrop(ServerWorld world, CallbackInfoReturnable<Integer> cir) {
        PlayerEntity playerEntity = (PlayerEntity)(Object)this;
        cir.setReturnValue(
                !world.getGameRules().getBoolean(GameRules.KEEP_INVENTORY) &&  !playerEntity.isSpectator()
                        ? Math.min(playerEntity.totalExperience / 2, 1000)
                        : 0
        );
    }
}
