package com.yakasov.hard_plus.mixin.entity.player;

import net.minecraft.entity.player.HungerManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(HungerManager.class)
public class HungerManagerMixin {
    @ModifyConstant(
            method = "update",
            constant = @Constant(
                    intValue = 10
            )
    )
    private int increaseFastFoodTickTimer(int i) {
        return 20;
    }

    @ModifyConstant(
            method = "update",
            constant = @Constant(
                    intValue = 80
            )
    )
    private int increaseSlowFoodTickTimer(int i) {
        return 160;
    }
}
