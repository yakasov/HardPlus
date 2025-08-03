package com.yakasov.hard_plus.mixin.item;

import net.minecraft.item.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ToolMaterial.class)
public class ToolMaterialMixin {
    @ModifyArg(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ToolMaterial;<init>(Lnet/minecraft/registry/tag/TagKey;IFFILnet/minecraft/registry/tag/TagKey;)V",
                    ordinal = 3
            ),
            index = 4
    )
    private static int increaseDiamondEnchantability(int enchantmentValue) {
        return 13;
    }
}
