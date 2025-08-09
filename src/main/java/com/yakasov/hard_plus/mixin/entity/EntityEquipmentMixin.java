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
    private boolean isEquipped(ItemStack item, PlayerEntity entity) {
        for (EquipmentSlot equipmentSlot : EquipmentSlot.VALUES) {
            ItemStack itemStack = entity.getEquippedStack(equipmentSlot);
            EquippableComponent equippableComponent = itemStack.get(DataComponentTypes.EQUIPPABLE);
            if (itemStack.isOf(item.getItem()) && equippableComponent != null && equippableComponent.slot() == equipmentSlot) {
                return true;
            }
        }

        return false;
    }

    @Unique
    private void damageStack(ItemStack stack) {
        Random random = new Random();
        int damageToDeal = random.nextInt((int) Math.floor((double) stack.getMaxDamage() / 5));

        if (stack.getDamage() - damageToDeal < 1) {
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
        if (!(entity instanceof PlayerEntity)) {
            return stack;
        }

        if (
                (
                    (
                        (
                                stack.isIn(ItemTags.HEAD_ARMOR) || stack.isIn(ItemTags.CHEST_ARMOR) ||
                                        stack.isIn(ItemTags.LEG_ARMOR) || stack.isIn(ItemTags.FOOT_ARMOR)
                        ) &&
                                isEquipped(stack, (PlayerEntity) entity)
                    ) || (
                            stack.isIn(ItemTags.PICKAXES) || stack.isIn(ItemTags.AXES) ||
                                    stack.isIn(ItemTags.SWORDS) || stack.isIn(ItemTags.HOES) ||
                                    stack.isIn(ItemTags.SHOVELS)
                    )
                ) && stack.getDamage() > 1
        ) {
            damageStack(stack);
        }

        return stack;
    }
}
