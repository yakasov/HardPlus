package com.yakasov.hard_plus.mixin.entity.mob;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.world.ServerWorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(DrownedEntity.class)
public class DrownedEntityMixin {
    @ModifyConstant(
            method = "canSpawn(Lnet/minecraft/entity/EntityType;Lnet/minecraft/world/ServerWorldAccess;Lnet/minecraft/entity/SpawnReason;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/random/Random;)Z",
            constant = @Constant(
                    intValue = 15
            )
    )
    private static int increaseLikelyDrownedSpawns(int i, @Local(argsOnly = true)ServerWorldAccess world) {
        return 10;
    }

    @ModifyConstant(
            method = "canSpawn(Lnet/minecraft/entity/EntityType;Lnet/minecraft/world/ServerWorldAccess;Lnet/minecraft/entity/SpawnReason;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/random/Random;)Z",
            constant = @Constant(
                    intValue = 40
            )
    )
    private static int increaseUnlikelyDrownedSpawns(int i, @Local(argsOnly = true)ServerWorldAccess world) {
        return 20;
    }
}
