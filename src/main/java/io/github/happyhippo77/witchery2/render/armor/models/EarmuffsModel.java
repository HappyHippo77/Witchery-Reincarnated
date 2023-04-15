package io.github.happyhippo77.witchery2.render.armor.models;

import io.github.happyhippo77.witchery2.Witchery2;
import io.github.happyhippo77.witchery2.item.items.Earmuffs;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class EarmuffsModel extends DefaultedItemGeoModel<Earmuffs> {
    public EarmuffsModel() {
        super(new Identifier(Witchery2.MOD_ID, "earmuffs"));
    }

    @Override
    protected String subtype() {
        return "armor";
    }
}
