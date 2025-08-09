package com.yakasov.hard_plus.mixin.entity;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.EntityEquipment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Random;

@Mixin(EntityEquipment.class)
public class EntityEquipmentMixin {
    @Unique
    private void damageStack(ItemStack stack, Random random) {
        int damageToDeal = random.nextInt((int) Math.floor((double) stack.getMaxDamage() / 5));

        if ((stack.getMaxDamage() - stack.getDamage()) - damageToDeal < 1) {
            stack.setDamage(stack.getMaxDamage() - 1);
        } else {
            stack.setDamage(stack.getDamage() + damageToDeal);
        }
    }

    @ModifyArg(
            method = "dropAll",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;"
            )
    )
    private ItemStack decreaseEquipmentHealth(ItemStack stack, @Local(argsOnly = true) LivingEntity entity) {
        Random random = new Random();

        if (!(entity instanceof PlayerEntity) || random.nextInt(3) == 0) {
            return stack;
        }

        damageStack(stack, random);

        return stack;
    }
}
