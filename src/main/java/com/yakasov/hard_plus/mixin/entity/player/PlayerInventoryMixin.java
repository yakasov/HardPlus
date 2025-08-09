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

        if (stack.getCount() < 3 || random.nextInt(2) == 0) {
            return stack;
        }

        int upperBound = (int) Math.floor((double) stack.getCount() / 3);
        stack.decrement(random.nextInt(upperBound));

        return stack;
    }
}
