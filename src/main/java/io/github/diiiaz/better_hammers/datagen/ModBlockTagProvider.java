package io.github.diiiaz.better_hammers.datagen;

import io.github.diiiaz.better_hammers.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Blocks.HAMMER_MINEABLE)
            .forceAddTag(BlockTags.PICKAXE_MINEABLE)
            .forceAddTag(BlockTags.SHOVEL_MINEABLE)
            .forceAddTag(BlockTags.AXE_MINEABLE)
        ;
    }
}
