package com.yakasov.hard_plus;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

import java.util.Optional;

public class HardPlus implements ModInitializer {

    @Override
    public void onInitialize() {
        modifyLootTables();

    }

    public static void modifyLootTables() {
        LootTableEvents.REPLACE.register(((registryKey, original, lootTableSource, wrapperLookup) -> {
            if (lootTableSource.isBuiltin()) {
                // Set Enderman Ender Pearl drop chance to 66%
                if (EntityType.ENDERMAN.getLootTableKey().equals(Optional.of(registryKey))) {
                    return LootTable.builder()
                            .pool(LootPool.builder()
                                    .rolls(ConstantLootNumberProvider.create(1.0F))
                                    .with(ItemEntry.builder(Items.ENDER_PEARL)
                                            .apply(SetCountLootFunction.builder(
                                                    BinomialLootNumberProvider.create(1, 0.66F)
                                            ))
                                    )
                            ).build();
                }
            }

            return original;
        }));

        LootTableEvents.MODIFY.register(((registryKey, builder, lootTableSource, wrapperLookup) -> {
            if (lootTableSource.isBuiltin()) {
                // Reduce Piglin Bartering Ender Pearl chance to 50% and drops to 1-2
                if (LootTables.PIGLIN_BARTERING_GAMEPLAY.equals(registryKey)) {
                    builder.modifyPools(poolBuilder -> {
                        poolBuilder.with(ItemEntry.builder(Items.ENDER_PEARL)
                                .weight(5)
                                .apply(SetCountLootFunction.builder(
                                        UniformLootNumberProvider.create(1.0F, 2.0F)
                                ))
                        );
                    });
                }
            }
        }));
    }
}
