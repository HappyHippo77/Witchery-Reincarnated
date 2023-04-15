package io.github.happyhippo77.witchery2.render.entity.renderers;

import io.github.happyhippo77.witchery2.Client;
import io.github.happyhippo77.witchery2.Witchery2;
import io.github.happyhippo77.witchery2.entity.entities.MandrakeEntity;
import io.github.happyhippo77.witchery2.render.entity.models.MandrakeModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class MandrakeRenderer extends MobEntityRenderer<MandrakeEntity, MandrakeModel> {
    public MandrakeRenderer(EntityRendererFactory.Context context) {
        super(context, new MandrakeModel(context.getPart(Client.MANDRAKE_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(MandrakeEntity entity) {
        return new Identifier(Witchery2.MOD_ID, "textures/entity/mandrake.png");
    }
}
