package com.yakasov.hard_plus.mixin.items;

import net.minecraft.item.equipment.ArmorMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ArmorMaterials.class)
public class ArmorMaterialsMixin {
    /*
     *    int i,
     *    java.util.Map<net.minecraft.item.equipment.EquipmentType, Integer> map,
     *    int j,
     *    net.minecraft.registry.entry.RegistryEntry<net.minecraft.sound.SoundEvent> registryEntry,
     *    float f,
     *    float g,
     *    net.minecraft.registry.tag.TagKey<net.minecraft.item.Item> tagKey,
     *    net.minecraft.registry.RegistryKey<net.minecraft.item.equipment.EquipmentAsset> registryKey
     *
     *    Lnet/minecraft/item/equipment/ArmorMaterial;<init>(ILjava/util/Map;ILnet/minecraft/registry/entry/RegistryEntry;FFLnet/minecraft/registry/tag/TagKey;Lnet/minecraft/registry/RegistryKey;)V
     */
    @ModifyArg(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/equipment/ArmorMaterial;<init>(ILjava/util/Map;ILnet/minecraft/registry/entry/RegistryEntry;FFLnet/minecraft/registry/tag/TagKey;Lnet/minecraft/registry/RegistryKey;)V",
                    ordinal = 4
            ),
            index = 2
    )
    private static int increaseDiamondEnchantability(int j) {
        return 13;
    }
}
