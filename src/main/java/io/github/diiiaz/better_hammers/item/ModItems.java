package io.github.diiiaz.better_hammers.item;

import io.github.diiiaz.better_hammers.Mod;
import io.github.diiiaz.better_hammers.util.ModTags;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item WOODEN_HAMMER = registerItem("wooden_hammer",
            new HammerItem(ToolMaterials.WOOD, ModTags.Blocks.HAMMER_MINEABLE, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(ToolMaterials.WOOD, 9.0F, -3.4F))));
    public static final Item STONE_HAMMER = registerItem("stone_hammer",
            new HammerItem(ToolMaterials.STONE, ModTags.Blocks.HAMMER_MINEABLE, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(ToolMaterials.STONE, 10.0F, -3.4F))));
    public static final Item IRON_HAMMER = registerItem("iron_hammer",
            new HammerItem(ToolMaterials.IRON, ModTags.Blocks.HAMMER_MINEABLE, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(ToolMaterials.IRON, 9.0F, -3.2F))));
    public static final Item GOLDEN_HAMMER = registerItem("golden_hammer",
            new HammerItem(ToolMaterials.GOLD, ModTags.Blocks.HAMMER_MINEABLE, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(ToolMaterials.GOLD, 9.0F, -3.3F))));
    public static final Item DIAMOND_HAMMER = registerItem("diamond_hammer",
            new HammerItem(ToolMaterials.DIAMOND, ModTags.Blocks.HAMMER_MINEABLE, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(ToolMaterials.DIAMOND, 8.0F, -3.2F))));
    public static final Item NETHERITE_HAMMER = registerItem("netherite_hammer",
            new HammerItem(ToolMaterials.NETHERITE, ModTags.Blocks.HAMMER_MINEABLE, new Item.Settings()
                    .attributeModifiers(HammerItem.createAttributeModifiers(ToolMaterials.NETHERITE, 8.0F, -3.2F))
                    .fireproof()));

    private static void addItemsToToolsItemGroup(FabricItemGroupEntries entries) {
        entries.addAfter(Items.WOODEN_AXE, WOODEN_HAMMER);
        entries.addAfter(Items.STONE_AXE, STONE_HAMMER);
        entries.addAfter(Items.IRON_AXE, IRON_HAMMER);
        entries.addAfter(Items.GOLDEN_AXE, GOLDEN_HAMMER);
        entries.addAfter(Items.DIAMOND_AXE, DIAMOND_HAMMER);
        entries.addAfter(Items.NETHERITE_AXE, NETHERITE_HAMMER);
    }

    private static void addItemsToCombatItemGroup(FabricItemGroupEntries entries) {
        entries.addAfter(Items.NETHERITE_AXE, WOODEN_HAMMER);
        entries.addAfter(WOODEN_HAMMER, STONE_HAMMER);
        entries.addAfter(STONE_HAMMER, IRON_HAMMER);
        entries.addAfter(IRON_HAMMER, GOLDEN_HAMMER);
        entries.addAfter(GOLDEN_HAMMER, DIAMOND_HAMMER);
        entries.addAfter(DIAMOND_HAMMER, NETHERITE_HAMMER);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Mod.ID, name), item);
    }

    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToToolsItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatItemGroup);
    }

}
