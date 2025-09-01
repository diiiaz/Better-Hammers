package io.github.diiiaz.better_hammers.util;

import io.github.diiiaz.better_hammers.item.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.function.EnchantWithLevelsLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.function.SetDamageLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class ModLootTableModifier {

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {

            if (LootTables.ABANDONED_MINESHAFT_CHEST.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(EmptyEntry.builder().weight(70))
                        .with(ItemEntry.builder(ModItems.IRON_HAMMER).weight(5));
                tableBuilder.pool(poolBuilder.build());
            }

            if (LootTables.BASTION_TREASURE_CHEST.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(EmptyEntry.builder().weight(12))
                        .with(
                                ItemEntry.builder(ModItems.GOLDEN_HAMMER)
                                        .weight(1)
                                        .apply(EnchantRandomlyLootFunction.builder())
                        );
                tableBuilder.pool(poolBuilder.build());
            }

            if (LootTables.BASTION_HOGLIN_STABLE_CHEST.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(EmptyEntry.builder().weight(100))
                        .with(
                                ItemEntry.builder(ModItems.DIAMOND_HAMMER)
                                        .weight(12)
                                        .apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.15F, 0.95F)))
                                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                        .apply(EnchantRandomlyLootFunction.builder())
                        );
                tableBuilder.pool(poolBuilder.build());
            }

            if (LootTables.BASTION_OTHER_CHEST.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(EmptyEntry.builder().weight(80))
                        .with(
                                ItemEntry.builder(ModItems.DIAMOND_HAMMER)
                                        .weight(6)
                                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                        .apply(EnchantRandomlyLootFunction.builder())
                        );
                tableBuilder.pool(poolBuilder.build());
            }

            if (LootTables.RUINED_PORTAL_CHEST.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(4.0F, 8.0F))
                        .with(EmptyEntry.builder().weight(400))
                        .with(
                                ItemEntry.builder(ModItems.GOLDEN_HAMMER)
                                        .weight(15)
                                        .apply(EnchantRandomlyLootFunction.builder())
                        );
                tableBuilder.pool(poolBuilder.build());
            }

            if (LootTables.VILLAGE_TOOLSMITH_CHEST.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(3.0F, 8.0F))
                        .with(EmptyEntry.builder().weight(50))
                        .with(ItemEntry.builder(ModItems.IRON_HAMMER).weight(5));
                tableBuilder.pool(poolBuilder.build());
            }

            if (LootTables.VILLAGE_WEAPONSMITH_CHEST.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(3.0F, 8.0F))
                        .with(EmptyEntry.builder().weight(90))
                        .with(ItemEntry.builder(ModItems.IRON_HAMMER).weight(5));
                tableBuilder.pool(poolBuilder.build());
            }

            if (LootTables.STRONGHOLD_CORRIDOR_CHEST.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(1.0F, 4.0F))
                        .with(EmptyEntry.builder().weight(60))
                        .with(ItemEntry.builder(ModItems.IRON_HAMMER));
                tableBuilder.pool(poolBuilder.build());
            }

            if (LootTables.STRONGHOLD_CROSSING_CHEST.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(1.0F, 4.0F))
                        .with(EmptyEntry.builder().weight(60))
                        .with(ItemEntry.builder(ModItems.IRON_HAMMER));
                tableBuilder.pool(poolBuilder.build());
            }

            if (LootTables.END_CITY_TREASURE_CHEST.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(2.0F, 6.0F))
                        .with(EmptyEntry.builder().weight(80))
                        .with(
                                ItemEntry.builder(ModItems.DIAMOND_HAMMER)
                                        .weight(3)
                                        .apply(EnchantWithLevelsLootFunction.builder(UniformLootNumberProvider.create(20.0F, 39.0F)).allowTreasureEnchantments())
                        )
                        .with(
                                ItemEntry.builder(ModItems.IRON_HAMMER)
                                        .weight(3)
                                        .apply(EnchantWithLevelsLootFunction.builder(UniformLootNumberProvider.create(20.0F, 39.0F)).allowTreasureEnchantments())
                        );
                tableBuilder.pool(poolBuilder.build());
            }
        });
    }

    public static void register() { modifyLootTables(); }

}
