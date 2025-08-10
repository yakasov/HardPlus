package com.yakasov.hard_plus.mixin.structure;

import com.google.common.collect.ImmutableList;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.SnowyVillageData;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorLists;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(SnowyVillageData.class)
public class SnowyVillageDataMixin {
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
        RegistryEntry<StructurePool> registryEntry7 = poolRegisterable.getRegistryLookup(RegistryKeys.TEMPLATE_POOL).getOrThrow(StructurePools.EMPTY);
        return new StructurePool(
                registryEntry7,
                ImmutableList.of(
                        Pair.of(StructurePoolElement.ofLegacySingle("village/snowy/town_centers/snowy_meeting_point_1"), 33),
                        Pair.of(StructurePoolElement.ofLegacySingle("village/snowy/town_centers/snowy_meeting_point_2"), 16),
                        Pair.of(StructurePoolElement.ofLegacySingle("village/snowy/town_centers/snowy_meeting_point_3"), 50),
                        Pair.of(StructurePoolElement.ofLegacySingle("village/snowy/zombie/town_centers/snowy_meeting_point_1"), 16),
                        Pair.of(StructurePoolElement.ofLegacySingle("village/snowy/zombie/town_centers/snowy_meeting_point_2"), 8),
                        Pair.of(StructurePoolElement.ofLegacySingle("village/snowy/zombie/town_centers/snowy_meeting_point_3"), 25)
                ),
                StructurePool.Projection.RIGID
        );
    }
}
