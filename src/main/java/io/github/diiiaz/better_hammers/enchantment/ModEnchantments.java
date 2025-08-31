package io.github.diiiaz.better_hammers.enchantment;

import io.github.diiiaz.better_hammers.Mod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantments {

    public static Enchantment MAGNITUDE = register("magnitude", new MagnitudeEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.DIGGER, EquipmentSlot.MAINHAND));
    public static Enchantment TUNNELER = register("tunneler", new TunnelerEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.DIGGER, EquipmentSlot.MAINHAND));


    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(Mod.ID, name), enchantment);
    }

    public static void register() {}

}
