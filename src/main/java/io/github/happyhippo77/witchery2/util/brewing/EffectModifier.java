package io.github.happyhippo77.witchery2.util.brewing;

public abstract class EffectModifier extends Modifier {

    public EffectModifier(String text) {
        super(text);
    }

    public abstract void apply(Effect effect);
}
