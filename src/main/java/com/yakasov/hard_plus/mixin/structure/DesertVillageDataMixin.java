package com.yakasov.hard_plus.mixin.structure;

import com.google.common.collect.ImmutableList;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.DesertVillageData;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorLists;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(DesertVillageData.class)
public class DesertVillageDataMixin {
    @Redirect(
        method = "bootstrap",
        at = @At(
                value = "NEW",
                target = "(Lnet/minecraft/registry/entry/RegistryEntry;Ljava/util/List;Lnet/minecraft/structure/pool/StructurePool$Projection;)Lnet/minecraft/structure/pool/StructurePool;"
        )
    )
    private static StructurePool modifyTownCenterWeights(
            RegistryEntry fallback, List elementWeightsByGetters, StructurePool.Projection projection,
            @Local(argsOnly = true) Registerable<StructurePool> poolRegisterable
            ) {
        RegistryEntry<StructureProcessorList> registryEntry3 = poolRegisterable.getRegistryLookup(RegistryKeys.PROCESSOR_LIST).getOrThrow(StructureProcessorLists.ZOMBIE_DESERT);
        RegistryEntry<StructurePool> registryEntry5 = poolRegisterable.getRegistryLookup(RegistryKeys.TEMPLATE_POOL).getOrThrow(StructurePools.EMPTY);
        return new StructurePool(
                registryEntry5,
                ImmutableList.of(
                        Pair.of(StructurePoolElement.ofLegacySingle("village/desert/town_centers/desert_meeting_point_1"), 33),
                        Pair.of(StructurePoolElement.ofLegacySingle("village/desert/town_centers/desert_meeting_point_2"), 33),
                        Pair.of(StructurePoolElement.ofLegacySingle("village/desert/town_centers/desert_meeting_point_3"), 16),
                        Pair.of(StructurePoolElement.ofProcessedLegacySingle("village/desert/zombie/town_centers/desert_meeting_point_1", registryEntry3), 16),
                        Pair.of(StructurePoolElement.ofProcessedLegacySingle("village/desert/zombie/town_centers/desert_meeting_point_2", registryEntry3), 16),
                        Pair.of(StructurePoolElement.ofProcessedLegacySingle("village/desert/zombie/town_centers/desert_meeting_point_3", registryEntry3), 8)
                ),
                StructurePool.Projection.RIGID
        );
    }
}
