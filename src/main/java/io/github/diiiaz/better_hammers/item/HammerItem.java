package io.github.diiiaz.better_hammers.item;

import io.github.diiiaz.better_hammers.api.HammerTool;
import io.github.diiiaz.better_hammers.enchantment.ModEnchantments;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.World;

public class HammerItem extends MiningToolItem implements HammerTool {

    public HammerItem(ToolMaterial material, TagKey<Block> effectiveBlocks, Settings settings) {
        super(material, effectiveBlocks, settings);
    }

    public int getDepth(World world, ItemStack heldStack) {
        RegistryEntry<Enchantment> registryEntry = world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(ModEnchantments.TUNNELER).orElse(null);
        if (registryEntry == null) return 0;
        return EnchantmentHelper.getLevel(registryEntry, heldStack);
    }

    public int getRadius(World world, ItemStack heldStack) {
        RegistryEntry<Enchantment> registryEntry = world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(ModEnchantments.MAGNITUDE).orElse(null);
        if (registryEntry == null) return 1;
        return EnchantmentHelper.getLevel(registryEntry, heldStack) + 1;
    }

}