package io.github.happyhippo77.witchery2.render.armor.renderers;

import io.github.happyhippo77.witchery2.item.items.Earmuffs;
import io.github.happyhippo77.witchery2.render.armor.models.EarmuffsModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class EarmuffsRenderer extends GeoArmorRenderer<Earmuffs> {
    public EarmuffsRenderer() {
        super(new EarmuffsModel());
    }
}
