package com.yakasov.hard_plus.mixin.entity.projectile;

import net.minecraft.entity.projectile.DragonFireballEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(DragonFireballEntity.class)
public class DragonFireballEntityMixin {
    @ModifyConstant(
            method = "onCollision",
            constant = @Constant(
                    floatValue = 3.0F
            )
    )
    private float increaseFireballEffectRadius(float f) {
        return 0.5F;
    }
}
