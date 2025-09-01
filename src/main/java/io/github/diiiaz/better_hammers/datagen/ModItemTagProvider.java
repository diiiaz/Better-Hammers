package io.github.diiiaz.better_hammers.datagen;

import io.github.diiiaz.better_hammers.Mod;
import io.github.diiiaz.better_hammers.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, Identifier.of(Mod.ID, "tools/hammers")))
            .add(ModItems.DIAMOND_HAMMER)
            .add(ModItems.STONE_HAMMER)
            .add(ModItems.GOLDEN_HAMMER)
            .add(ModItems.NETHERITE_HAMMER)
            .add(ModItems.WOODEN_HAMMER)
            .add(ModItems.IRON_HAMMER);
    }
}


