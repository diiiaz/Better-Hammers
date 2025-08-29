package io.github.diiiaz.better_hammers.item;

import io.github.diiiaz.better_hammers.util.ModTags;
import net.minecraft.item.Item;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;

public class HammerItem extends MiningToolItem {
    public HammerItem(ToolMaterial material, float attackDamage, float attackSpeed, Item.Settings settings) {
        super(attackDamage, attackSpeed, material, ModTags.Blocks.HAMMER_MINEABLE, settings);
    }
}