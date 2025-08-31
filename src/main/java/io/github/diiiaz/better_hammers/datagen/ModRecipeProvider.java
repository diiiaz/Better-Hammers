package io.github.diiiaz.better_hammers.datagen;

import io.github.diiiaz.better_hammers.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.WOODEN_HAMMER)
                .input('#', Items.STICK)
                .input('X', ItemTags.PLANKS)
                .pattern("XXX")
                .pattern("X#X")
                .pattern(" # ")
                .criterion("has_stick", conditionsFromItem(Items.STICK))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.STONE_HAMMER)
                .input('#', Items.STICK)
                .input('X', ItemTags.STONE_TOOL_MATERIALS)
                .pattern("XXX")
                .pattern("X#X")
                .pattern(" # ")
                .criterion("has_cobblestone", conditionsFromTag(ItemTags.STONE_TOOL_MATERIALS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.IRON_HAMMER)
                .input('#', Items.STICK)
                .input('X', Items.IRON_INGOT)
                .pattern("XXX")
                .pattern("X#X")
                .pattern(" # ")
                .criterion("has_iron_ingot", conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.GOLDEN_HAMMER)
                .input('#', Items.STICK)
                .input('X', Items.GOLD_INGOT)
                .pattern("XXX")
                .pattern("X#X")
                .pattern(" # ")
                .criterion("has_gold_ingot", conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.DIAMOND_HAMMER)
                .input('#', Items.STICK)
                .input('X', Items.DIAMOND)
                .pattern("XXX")
                .pattern("X#X")
                .pattern(" # ")
                .criterion("has_diamond", conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter);

        offerNetheriteUpgradeRecipe(exporter, ModItems.DIAMOND_HAMMER, RecipeCategory.TOOLS, ModItems.NETHERITE_HAMMER);

    }


}
