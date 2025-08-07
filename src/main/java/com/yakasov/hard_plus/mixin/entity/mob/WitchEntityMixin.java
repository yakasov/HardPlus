package com.yakasov.hard_plus.mixin.entity.mob;

import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Mixin(WitchEntity.class)
public class WitchEntityMixin {
    @Final
    @Unique
    private Map<String, String> potionMap = Map.of(
            "healing", "strong_healing",
            "regeneration", "strong_regeneration",
            "slowness", "strong_slowness",
            "weakness", "long_weakness"
    );

    @ModifyArg(
            method = "shootAt",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/component/type/PotionContentsComponent;createStack(Lnet/minecraft/item/Item;Lnet/minecraft/registry/entry/RegistryEntry;)Lnet/minecraft/item/ItemStack;"
            )
    )
    private RegistryEntry<Potion> addBlindnessAndHunger(RegistryEntry<Potion> registryEntry) {
        Random r = new Random();
        int ri = r.nextInt(100);

        String currentPotionId = registryEntry.getKey()
                .map(key -> key.getValue().getPath())
                .orElse(null);

        if (currentPotionId != null && potionMap.containsKey(currentPotionId)) {
            String strongerPotionId = potionMap.get(currentPotionId);

            Optional<RegistryEntry.Reference<Potion>> strongerPotionOpt =
                    Registries.POTION.getEntry(Identifier.ofVanilla(strongerPotionId));

            if (ri <= 15 && strongerPotionOpt.isPresent()) {
                registryEntry = strongerPotionOpt.get();
            }
        }

        return registryEntry;
    }
}
