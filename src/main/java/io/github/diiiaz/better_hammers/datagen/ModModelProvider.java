package io.github.diiiaz.better_hammers.datagen;

import io.github.diiiaz.better_hammers.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.WOODEN_HAMMER, Models.GENERATED);
        itemModelGenerator.register(ModItems.STONE_HAMMER, Models.GENERATED);
        itemModelGenerator.register(ModItems.IRON_HAMMER, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_HAMMER, Models.GENERATED);
        itemModelGenerator.register(ModItems.DIAMOND_HAMMER, Models.GENERATED);
        itemModelGenerator.register(ModItems.NETHERITE_HAMMER, Models.GENERATED);
    }
}
