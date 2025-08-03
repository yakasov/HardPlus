package com.yakasov.hard_plus.mixin.structure;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.structure.DesertVillageData;
import net.minecraft.structure.pool.StructurePoolElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DesertVillageData.class)
public class DesertVillageDataMixin {
    @Redirect(
        method = "bootstrap",
        at = @At(value = "INVOKE",
                 target = "Lcom/google/common/collect/ImmutableList;of(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/common/collect/ImmutableList;",
                 ordinal = 0
        )
    )
    private static ImmutableList<Pair<StructurePoolElement, Integer>> modifyTownCenterWeights(
        Object normal1, Object normal2, Object normal3,
        Object zombie1, Object zombie2, Object zombie3
    ) {
        Pair<StructurePoolElement, Integer> normalPair1 = (Pair<StructurePoolElement, Integer>) normal1;
        Pair<StructurePoolElement, Integer> normalPair2 = (Pair<StructurePoolElement, Integer>) normal2;
        Pair<StructurePoolElement, Integer> normalPair3 = (Pair<StructurePoolElement, Integer>) normal3;
        Pair<StructurePoolElement, Integer> zombiePair1 = (Pair<StructurePoolElement, Integer>) zombie1;
        Pair<StructurePoolElement, Integer> zombiePair2 = (Pair<StructurePoolElement, Integer>) zombie2;
        Pair<StructurePoolElement, Integer> zombiePair3 = (Pair<StructurePoolElement, Integer>) zombie3;

        return ImmutableList.of(
            Pair.of(normalPair1.getFirst(), 33),
            Pair.of(normalPair2.getFirst(), 33),
            Pair.of(normalPair3.getFirst(), 16),
            Pair.of(zombiePair1.getFirst(), 16),
            Pair.of(zombiePair2.getFirst(), 16),
            Pair.of(zombiePair3.getFirst(), 8)
        );
    }
}
