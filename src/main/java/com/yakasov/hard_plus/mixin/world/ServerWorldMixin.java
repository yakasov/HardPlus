package com.yakasov.hard_plus.mixin.world;

import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
    @ModifyConstant(
            method = "tickThunder",
            constant = @Constant(
                    intValue = 100000
            )
    )
    private int increaseLightningChance(int i) {
        return 40000;
    }
}
