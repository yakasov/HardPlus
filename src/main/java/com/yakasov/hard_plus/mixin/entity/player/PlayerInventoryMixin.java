package com.yakasov.hard_plus.mixin.entity.player;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Random;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {
    @ModifyArg(
            method = "dropAll",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;"
            )
    )
    private ItemStack decreaseDroppedItemStack(ItemStack stack) {
        Random random = new Random();

        if (random.nextInt(3) == 0) {
            return stack;
        }

        if (stack.getMaxDamage() > 1) {
            int upperBound = (int) Math.floor((double) (stack.getMaxDamage() - stack.getDamage()) / 3);

            if (upperBound > 0) {
                int damageToDeal = random.nextInt(upperBound);
                stack.setDamage(Math.min(stack.getDamage() + damageToDeal, stack.getMaxDamage()) - 1);
            }
        } else if (stack.getCount() > 3) {
            int upperBound = (int) Math.floor((double) stack.getCount() / 3);
            stack.decrement(random.nextInt(upperBound));
        }

        return stack;
    }
}
