package io.github.diiiaz.better_hammers.enchantment;

import io.github.diiiaz.better_hammers.Mod;
import io.github.diiiaz.better_hammers.util.ModTags;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

public class ModEnchantments {

    public static final RegistryKey<Enchantment> MAGNITUDE = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(Mod.ID, "magnitude"));
    public static final RegistryKey<Enchantment> TUNNELER = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(Mod.ID, "tunneler"));


    public static void bootstrap(Registerable<Enchantment> registerable) {
        var items = registerable.getRegistryLookup(RegistryKeys.ITEM);

        register(registerable, MAGNITUDE, Enchantment.builder(
                Enchantment.definition(
                        items.getOrThrow(ModTags.Items.HAMMERS),
                        10,
                        2,
                        Enchantment.leveledCost(1, 10),
                        Enchantment.leveledCost(51, 10),
                        1,
                        AttributeModifierSlot.MAINHAND
                )));

        register(registerable, TUNNELER, Enchantment.builder(
                Enchantment.definition(
                        items.getOrThrow(ModTags.Items.HAMMERS),
                        10,
                        3,
                        Enchantment.leveledCost(1, 10),
                        Enchantment.leveledCost(51, 10),
                        1,
                        AttributeModifierSlot.MAINHAND
                )));
    }


    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }

    public static void register() {}

}
