package com.yakasov.hard_plus.mixin.world.spawner;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.PatrolEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.spawner.PatrolSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Random;

@Mixin(PatrolSpawner.class)
public class PatrolSpawnerMixin {
    @ModifyVariable(
            method = "spawnPillager",
            at = @At(
                    value = "STORE",
                    ordinal = 0
            ),
            name = "patrolEntity"
    )
    private PatrolEntity randomSetPillagerToVindicator(PatrolEntity value, @Local(argsOnly = true) ServerWorld world) {
        Random random = new Random();

        if (random.nextInt(4) == 0) {
            value = EntityType.VINDICATOR.create(world, SpawnReason.PATROL);
        }

        return value;
    }

    @ModifyVariable(
            method = "spawn",
            at = @At(
                    value = "STORE",
                    ordinal = 0
            ),
            name = "n"
    )
    private int increaseRaidSize(int n) {
        return n + 2;
    }
}
