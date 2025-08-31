package io.github.diiiaz.better_hammers.item;

import io.github.diiiaz.better_hammers.api.HammerTool;
import io.github.diiiaz.better_hammers.enchantment.ModEnchantments;
import io.github.diiiaz.better_hammers.util.ModTags;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.*;

public class HammerItem extends MiningToolItem implements HammerTool {

    public HammerItem(ToolMaterial material, float attackDamage, float attackSpeed, Item.Settings settings) { super(attackDamage, attackSpeed, material, ModTags.Blocks.HAMMER_MINEABLE, settings); }

    public int getDepth(ItemStack heldStack) { return EnchantmentHelper.getLevel(ModEnchantments.TUNNELER, heldStack); }

    public int getRadius(ItemStack heldStack) { return EnchantmentHelper.getLevel(ModEnchantments.MAGNITUDE, heldStack) + 1; }

}