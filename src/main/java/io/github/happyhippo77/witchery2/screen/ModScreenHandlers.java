package io.github.happyhippo77.witchery2.screen;

import io.github.happyhippo77.witchery2.Witchery2;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static ScreenHandlerType<WitchsOvenScreenHandler> WITCHS_OVEN_SCREEN_HANDLER;

    public static void registerAllScreenHandlers() {
        WITCHS_OVEN_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(Witchery2.MOD_ID, "witchs_oven"), WitchsOvenScreenHandler::new);
    }
}
