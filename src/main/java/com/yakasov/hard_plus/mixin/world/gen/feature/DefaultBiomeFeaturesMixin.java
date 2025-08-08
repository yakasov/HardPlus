package com.yakasov.hard_plus.mixin.world.gen.feature;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {
    @ModifyVariable(
            method = "addMonsters",
            at = @At("HEAD"),
            name = "zombieVillagerWeight",
            ordinal = 1,
            argsOnly = true
    )
    private static int setZombieVillagerWeightFloor(int value) {
        return Math.max(value, 5);
    }

    @Inject(
            method = "addMonsters",
            at = @At("TAIL")
    )
    private static void addMobsToMonsterPool(
            SpawnSettings.Builder builder,
            int zombieWeight, int zombieVillagerWeight,
            int skeletonWeight, boolean drowned,
            CallbackInfo ci
    ) {
        builder.spawn(SpawnGroup.MONSTER, 2,
                new SpawnSettings.SpawnEntry(EntityType.BOGGED, 2, 2)
        );
        builder.spawn(SpawnGroup.MONSTER, 2,
                new SpawnSettings.SpawnEntry(EntityType.HUSK, 2, 2)
        );
        builder.spawn(SpawnGroup.MONSTER, 2,
                new SpawnSettings.SpawnEntry(EntityType.STRAY, 2, 2)
        );
    }

    @Redirect(
            method = "addMonsters",
            at = @At(
                    value = "NEW",
                    target = "net/minecraft/world/biome/SpawnSettings$SpawnEntry"
            )
    )
    private static SpawnSettings.SpawnEntry increaseMaxGroupSize(EntityType<?> entityType, int minGroupSize, int maxGroupSize) {
        return new SpawnSettings.SpawnEntry(entityType, minGroupSize, maxGroupSize == 4 ? 6 : maxGroupSize);
    }
}
