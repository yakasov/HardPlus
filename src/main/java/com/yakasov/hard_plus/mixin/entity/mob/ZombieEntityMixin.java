package com.yakasov.hard_plus.mixin.entity.mob;

import net.minecraft.entity.mob.ZombieEntity;
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
        return 0.15F;
    }

    @ModifyConstant(
            method = "tryAttack",
            constant = @Constant(
                    floatValue = 0.3F
            )
    )
    private float ensureFireLightChance(float f) {
        return 100.0F;
    }

    @ModifyConstant(
            method = "applyAttributeModifiers",
            constant = @Constant(
                    doubleValue = 1.0F
            )
    )
    private double ensureFollowRangeBonus(double d) {
        return 0.0;
    }

    @ModifyVariable(
            method = "applyAttributeModifiers",
            at = @At("STORE"),
            ordinal = 0
    )
    private double increaseFollowRangeBonus(double d) {
        return d * 1.25;
    }

    @ModifyConstant(
            method = "applyAttributeModifiers",
            constant = @Constant(
                    floatValue = 0.05F
            )
    )
    private float increaseLeaderChance(float f) {
        return 0.1F;
    }

    @ModifyConstant(
            method = "initialize",
            constant = @Constant(
                    floatValue = 0.55F
            )
    )
    private float ensureZombieCanPickUpLoot(float f) {
        return 100.0F;
    }
}
