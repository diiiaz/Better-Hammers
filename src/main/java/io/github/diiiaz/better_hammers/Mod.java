package io.github.diiiaz.better_hammers;

import io.github.diiiaz.better_hammers.enchantment.ModEnchantments;
import io.github.diiiaz.better_hammers.item.ModItems;
import io.github.diiiaz.better_hammers.util.ModLootTableModifier;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mod implements ModInitializer {
	public static final String ID = "better-hammers";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);

    @Override
	public void onInitialize() {

        ModItems.register();
        ModEnchantments.register();
        ModLootTableModifier.register();

	}
}