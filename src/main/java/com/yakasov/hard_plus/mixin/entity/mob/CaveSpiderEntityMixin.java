package com.yakasov.hard_plus.mixin.entity.mob;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.mob.CaveSpiderEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(CaveSpiderEntity.class)
public class CaveSpiderEntityMixin {
    @ModifyVariable(
            method = "tryAttack(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/Entity;)Z",
            at = @At("STORE"),
            name = "i",
            ordinal = 0
    )
    private int setDurationToTen(int i, @Local(argsOnly = true) ServerWorld world) {
        return 10;
    }

    @ModifyArg(
            method = "tryAttack(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/Entity;)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/effect/StatusEffectInstance;<init>(Lnet/minecraft/registry/entry/RegistryEntry;II)V"
            ),
            index = 2
    )
    private int setAmplifierToTwo(int amplifier, @Local(argsOnly = true) ServerWorld world) {
        // amplifier starts at 0 so 1 is actually 'adding' an extra tier of the status effect
        return 1;
    }
}
