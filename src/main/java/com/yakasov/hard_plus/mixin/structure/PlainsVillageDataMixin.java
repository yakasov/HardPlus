package com.yakasov.hard_plus.mixin.structure;

import com.google.common.collect.ImmutableList;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.PlainsVillageData;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorLists;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(PlainsVillageData.class)
public class PlainsVillageDataMixin {
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
        RegistryEntry<StructureProcessorList> registryEntry5 = poolRegisterable.getRegistryLookup(RegistryKeys.PROCESSOR_LIST).getOrThrow(StructureProcessorLists.MOSSIFY_20_PERCENT);
        RegistryEntry<StructureProcessorList> registryEntry6 = poolRegisterable.getRegistryLookup(RegistryKeys.PROCESSOR_LIST).getOrThrow(StructureProcessorLists.MOSSIFY_70_PERCENT);
        RegistryEntry<StructureProcessorList> registryEntry7 = poolRegisterable.getRegistryLookup(RegistryKeys.PROCESSOR_LIST).getOrThrow(StructureProcessorLists.ZOMBIE_PLAINS);
        RegistryEntry<StructurePool> registryEntry10 = poolRegisterable.getRegistryLookup(RegistryKeys.TEMPLATE_POOL).getOrThrow(StructurePools.EMPTY);
        return new StructurePool(
                registryEntry10,
                ImmutableList.of(
                        Pair.of(StructurePoolElement.ofProcessedLegacySingle("village/plains/town_centers/plains_fountain_01", registryEntry5), 33),
                        Pair.of(StructurePoolElement.ofProcessedLegacySingle("village/plains/town_centers/plains_meeting_point_1", registryEntry5), 33),
                        Pair.of(StructurePoolElement.ofLegacySingle("village/plains/town_centers/plains_meeting_point_2"), 33),
                        Pair.of(StructurePoolElement.ofProcessedLegacySingle("village/plains/town_centers/plains_meeting_point_3", registryEntry6), 33),
                        Pair.of(StructurePoolElement.ofProcessedLegacySingle("village/plains/zombie/town_centers/plains_fountain_01", registryEntry7), 16),
                        Pair.of(StructurePoolElement.ofProcessedLegacySingle("village/plains/zombie/town_centers/plains_meeting_point_1", registryEntry7), 16),
                        Pair.of(StructurePoolElement.ofProcessedLegacySingle("village/plains/zombie/town_centers/plains_meeting_point_2", registryEntry7), 16),
                        Pair.of(StructurePoolElement.ofProcessedLegacySingle("village/plains/zombie/town_centers/plains_meeting_point_3", registryEntry7), 16)
                ),
                StructurePool.Projection.RIGID
        );
    }
}
