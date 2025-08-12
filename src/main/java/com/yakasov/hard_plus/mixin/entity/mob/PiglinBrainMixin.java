package com.yakasov.hard_plus.mixin.entity.mob;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.registry.tag.ItemTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinBrain.class)
public class PiglinBrainMixin {
    @Inject(
            method = "isWearingPiglinSafeArmor",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void requireThreePiecesOfSafeArmour(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        int safeEquipment = 0;
        for (EquipmentSlot equipmentSlot : AttributeModifierSlot.ARMOR) {
            if (entity.getEquippedStack(equipmentSlot).isIn(ItemTags.PIGLIN_SAFE_ARMOR)) {
                safeEquipment++;
            }
        }

        cir.setReturnValue(safeEquipment >= 3);
    }
}
