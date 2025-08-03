package com.yakasov.hard_plus.mixin.mob;

import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ZombieEntity.class)
public class ZombieEntityMixin {
    @ModifyConstant(
            method = "initEquipment",
            constant = @Constant(
                    floatValue = 0.05F
            )
    )
    private float increaseEquipmentChance(float f) {
        ZombieEntity zombieEntity = (ZombieEntity)(Object)this;

        if (zombieEntity.getWorld().getDifficulty() == Difficulty.HARD) {
            f = 0.15F;
        }

        return f;
    }

    @ModifyConstant(
            method = "tryAttack",
            constant = @Constant(
                    floatValue = 0.3F
            )
    )
    private float ensureFireLightChance(float f) {
        ZombieEntity zombieEntity = (ZombieEntity)(Object)this;

        if (zombieEntity.getWorld().getDifficulty() == Difficulty.HARD) {
            f = 100.00F;
        }

        return f;
    }

    @ModifyConstant(
            method = "applyAttributeModifiers",
            constant = @Constant(
                    doubleValue = 1.0F
            )
    )
    private double ensureFollowRangeBonus(double d) {
        ZombieEntity zombieEntity = (ZombieEntity)(Object)this;

        if (zombieEntity.getWorld().getDifficulty() == Difficulty.HARD) {
            d = 0.0;
        }

        return d;
    }

    @ModifyVariable(
            method = "applyAttributeModifiers",
            at = @At("STORE"),
            ordinal = 0
    )
    private double increaseFollowRangeBonus(double d) {
        ZombieEntity zombieEntity = (ZombieEntity)(Object)this;

        if (zombieEntity.getWorld().getDifficulty() == Difficulty.HARD) {
            d *= 1.25;
        }

        return d;
    }

    @ModifyConstant(
            method = "applyAttributeModifiers",
            constant = @Constant(
                    floatValue = 0.05F
            )
    )
    private float increaseLeaderChance(float f) {
        ZombieEntity zombieEntity = (ZombieEntity)(Object)this;

        if (zombieEntity.getWorld().getDifficulty() == Difficulty.HARD) {
            f = 0.1F;
        }

        return f;
    }
}
