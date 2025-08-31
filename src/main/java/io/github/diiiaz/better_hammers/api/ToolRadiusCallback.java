package io.github.diiiaz.better_hammers.api;

// Copied and/or modified from: "https://github.com/Draylar/magna/blob/1.20.1/src/main/java/dev/draylar/magna/api/event/ToolRadiusCallback.java"

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.item.ItemStack;

public interface ToolRadiusCallback {

    Event<ToolRadiusCallback> EVENT = EventFactory.createArrayBacked(ToolRadiusCallback.class,
            listeners -> (tool, currentRadius) -> {
                for (ToolRadiusCallback callback : listeners) {
                    currentRadius = callback.getRadius(tool, currentRadius);
                }

                return currentRadius;
            });

    int getRadius(ItemStack tool, int currentRadius);
}