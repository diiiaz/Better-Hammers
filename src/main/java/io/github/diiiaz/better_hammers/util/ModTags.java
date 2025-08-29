package io.github.diiiaz.better_hammers.util;

import io.github.diiiaz.better_hammers.Mod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {

    public static class Blocks {

        public static final TagKey<Block> HAMMER_MINEABLE = createTag("mineable/hammer");

        private static TagKey<Block> createTag(String name) { return TagKey.of(RegistryKeys.BLOCK, new Identifier(Mod.ID, name)); }
    }

    public static class Items {
        public static final TagKey<Item> HAMMERS = createTag("hammers");

        private static TagKey<Item> createTag(String name) { return TagKey.of(RegistryKeys.ITEM, new Identifier(Mod.ID, name)); }
    }

}
