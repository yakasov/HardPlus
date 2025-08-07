package com.yakasov.hard_plus.mixin.entity.mob;

import net.minecraft.entity.mob.HuskEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HuskEntity.class)
public class HuskEntityMixin {
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
}
