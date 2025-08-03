package com.yakasov.hard_plus.mixin.entity.projectile;

import net.minecraft.entity.projectile.DragonFireballEntity;
import net.minecraft.world.Difficulty;
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
        DragonFireballEntity dragonFireballEntity = (DragonFireballEntity)(Object)this;

        if (dragonFireballEntity.getWorld().getDifficulty() == Difficulty.HARD) {
            f = 5.0F;
        }

        return f;
    }
}
