package com.yakasov.hard_plus.mixin.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity {
    protected MobEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method = "createMobAttributes",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void setDefaultFollowRange(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.setReturnValue(LivingEntity.createLivingAttributes().add(EntityAttributes.FOLLOW_RANGE, 24.0));
    }

    @ModifyArg(
            method = "getSafeFallDistance()I",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/mob/MobEntity;getSafeFallDistance(F)I"
            ),
            index = 0
    )
    private float increaseSafeFallDistance(float par1) {
        MobEntity mobEntity = (MobEntity)(Object)this;

        if (mobEntity.getWorld().getDifficulty() == Difficulty.HARD) {
            par1 *= 1.25F;
        }

        return par1;
    }

    @ModifyVariable(
            method = "initEquipment",
            at = @At("STORE"),
            name = "f",
            ordinal = 0
    )
    private float increaseEquipmentInitChance(float f) {
        MobEntity mobEntity = (MobEntity)(Object)this;

        if (mobEntity.getWorld().getDifficulty() == Difficulty.HARD) {
            f = 0.05F;
        }

        return f;
    }

    @ModifyConstant(
            method = "initEquipment",
            constant = @Constant(
                    floatValue = 0.095F
            )
    )
    private float increaseEquipmentGradeChance(float f) {
        MobEntity mobEntity = (MobEntity)(Object)this;

        if (mobEntity.getWorld().getDifficulty() == Difficulty.HARD) {
            f = 0.19f;
        }

        return f;
    }

    @ModifyVariable(
            method = "enchantEquipment(Lnet/minecraft/world/ServerWorldAccess;Lnet/minecraft/entity/EquipmentSlot;Lnet/minecraft/util/math/random/Random;FLnet/minecraft/world/LocalDifficulty;)V",
            at = @At("HEAD"),
            ordinal = 0,
            argsOnly = true
    )
    private float increaseEnchantmentPower(float power) {
        MobEntity mobEntity = (MobEntity)(Object)this;

        if (mobEntity.getWorld().getDifficulty() == Difficulty.HARD) {
            power *= 1.5F;
        }

        return power;
    }
}
