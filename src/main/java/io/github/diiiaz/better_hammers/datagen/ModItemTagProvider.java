package io.github.diiiaz.better_hammers.datagen;

import io.github.diiiaz.better_hammers.item.ModItems;
import io.github.diiiaz.better_hammers.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Items.HAMMERS)
            .add(ModItems.DIAMOND_HAMMER)
            .add(ModItems.STONE_HAMMER)
            .add(ModItems.GOLDEN_HAMMER)
            .add(ModItems.NETHERITE_HAMMER)
            .add(ModItems.WOODEN_HAMMER)
            .add(ModItems.IRON_HAMMER);

        getOrCreateTagBuilder(ItemTags.MINING_ENCHANTABLE)
                .addTag(ModTags.Items.HAMMERS);

        getOrCreateTagBuilder(ItemTags.MINING_LOOT_ENCHANTABLE)
                .addTag(ModTags.Items.HAMMERS);

        getOrCreateTagBuilder(ItemTags.VANISHING_ENCHANTABLE)
                .addTag(ModTags.Items.HAMMERS);

        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .addTag(ModTags.Items.HAMMERS);

        getOrCreateTagBuilder(ItemTags.MACE_ENCHANTABLE)
                .addTag(ModTags.Items.HAMMERS);

        getOrCreateTagBuilder(ConventionalItemTags.ENCHANTABLES)
                .addTag(ModTags.Items.HAMMERS);
    }
}


