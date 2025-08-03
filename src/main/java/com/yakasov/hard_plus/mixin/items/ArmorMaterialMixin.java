package com.yakasov.hard_plus.mixin.items;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ArmorMaterial.class)
public class ArmorMaterialMixin {
    @ModifyVariable(
            method = "<init>",
            at = @At("HEAD"),
            ordinal = 1,
            argsOnly = true
    )
    private static int increaseDiamondEnchantability(int enchantmentValue, @Local(argsOnly = true) RegistryKey<EquipmentAsset> registryKey) {
        if (registryKey.equals(EquipmentAssetKeys.DIAMOND)) {
            enchantmentValue = 13;
        }
        return enchantmentValue;
    }
}
