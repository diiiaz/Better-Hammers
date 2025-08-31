package io.github.diiiaz.better_hammers.enchantment;

import io.github.diiiaz.better_hammers.item.HammerItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class TunnelerEnchantment extends Enchantment {

    protected TunnelerEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot... slotTypes) { super(weight, target, slotTypes); }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof HammerItem;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
