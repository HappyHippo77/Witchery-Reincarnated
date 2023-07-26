package io.github.happyhippo77.witchery2.util.brewing;

import io.github.happyhippo77.witchery2.block.entity.entities.WitchsCauldronEntity;
import net.minecraft.item.ItemStack;

import java.awt.*;
import java.util.Map;

public class Modifiers {
    public static final EffectModifier NO_PARTICLES = new EffectModifier("no_particles") {
        @Override
        public void apply(Effect effect) {
            effect.setHasParticles(false);
        }
    };

    public static final EffectModifier INVERT = new EffectModifier("invert") {
        @Override
        public void apply(Effect effect) {
            effect.setInverted(true);
        }
    };

    public static final EffectModifier SKIP_BLOCK = new EffectModifier("skip_block") {
        @Override
        public void apply(Effect effect) {
            effect.setApplyBlock(false);
        }
    };

    public static final EffectModifier SKIP_ENTITY = new EffectModifier("skip_entity") {
        @Override
        public void apply(Effect effect) {
            effect.setApplyEntity(false);
        }
    };


    public static final BrewModifier QUAFFING_MINUS_8 = new BrewModifier("quaffing_minus_8") {
        @Override
        public void applyBrew(ItemStack brew) {
            if (brew.getNbt() != null) {
                brew.getNbt().putInt("drinkSpeed", brew.getNbt().getInt("drinkSpeed") - 8);
            }
        }
        @Override
        public void applyCauldron(WitchsCauldronEntity cauldron) {
        }
    };
    public static final BrewModifier QUAFFING_MINUS_4 = new BrewModifier("quaffing_minus_4") {
        @Override
        public void applyBrew(ItemStack brew) {
            if (brew.getNbt() != null) {
                brew.getNbt().putInt("drinkSpeed", brew.getNbt().getInt("drinkSpeed") - 4);
            }
        }
        @Override
        public void applyCauldron(WitchsCauldronEntity cauldron) {
        }
    };

    public static final BrewModifier WHITE = new BrewModifier("white") {
        @Override
        public void applyBrew(ItemStack brew) {
            if (brew.getNbt() != null) {
                brew.getNbt().putInt("color", 0xF9FFFE);
            }
        }
        @Override
        public void applyCauldron(WitchsCauldronEntity cauldron) {
            cauldron.setColorOverride(true);
            cauldron.setColor(new Color(0xF9FFFE));
        }
    };
    public static final BrewModifier LIGHT_GRAY = new BrewModifier("light_gray") {
        @Override
        public void applyBrew(ItemStack brew) {
            if (brew.getNbt() != null) {
                brew.getNbt().putInt("color", 0x9D9D97);
            }
        }
        @Override
        public void applyCauldron(WitchsCauldronEntity cauldron) {
            cauldron.setColorOverride(true);
            cauldron.setColor(new Color(0x9D9D97));
        }
    };
    public static final BrewModifier GRAY = new BrewModifier("gray") {
        @Override
        public void applyBrew(ItemStack brew) {
            if (brew.getNbt() != null) {
                brew.getNbt().putInt("color", 4673362);
            }
        }
        @Override
        public void applyCauldron(WitchsCauldronEntity cauldron) {
            cauldron.setColorOverride(true);
            cauldron.setColor(new Color(4673362));
        }
    };
    public static final BrewModifier BLACK = new BrewModifier("black") {
        @Override
        public void applyBrew(ItemStack brew) {
            if (brew.getNbt() != null) {
                brew.getNbt().putInt("color", 0x1D1D21);
            }
        }
        @Override
        public void applyCauldron(WitchsCauldronEntity cauldron) {
            cauldron.setColorOverride(true);
            cauldron.setColor(new Color(0x1D1D21));
        }
    };
    public static final BrewModifier BROWN = new BrewModifier("brown") {
        @Override
        public void applyBrew(ItemStack brew) {
            if (brew.getNbt() != null) {
                brew.getNbt().putInt("color", 8606770);
            }
        }
        @Override
        public void applyCauldron(WitchsCauldronEntity cauldron) {
            cauldron.setColorOverride(true);
            cauldron.setColor(new Color(8606770));
        }
    };
    public static final BrewModifier RED = new BrewModifier("red") {
        @Override
        public void applyBrew(ItemStack brew) {
            if (brew.getNbt() != null) {
                brew.getNbt().putInt("color", 11546150);
            }
        }
        @Override
        public void applyCauldron(WitchsCauldronEntity cauldron) {
            cauldron.setColorOverride(true);
            cauldron.setColor(new Color(11546150));
        }
    };
    public static final BrewModifier ORANGE = new BrewModifier("orange") {
        @Override
        public void applyBrew(ItemStack brew) {
            if (brew.getNbt() != null) {
                brew.getNbt().putInt("color", 16351261);
            }
        }
        @Override
        public void applyCauldron(WitchsCauldronEntity cauldron) {
            cauldron.setColorOverride(true);
            cauldron.setColor(new Color(16351261));
        }
    };
    public static final BrewModifier YELLOW = new BrewModifier("yellow") {
        @Override
        public void applyBrew(ItemStack brew) {
            if (brew.getNbt() != null) {
                brew.getNbt().putInt("color", 16701501);
            }
        }
        @Override
        public void applyCauldron(WitchsCauldronEntity cauldron) {
            cauldron.setColorOverride(true);
            cauldron.setColor(new Color(16701501));
        }
    };
    public static final BrewModifier LIME = new BrewModifier("lime") {
        @Override
        public void applyBrew(ItemStack brew) {
            if (brew.getNbt() != null) {
                brew.getNbt().putInt("color", 8439583);
            }
        }
        @Override
        public void applyCauldron(WitchsCauldronEntity cauldron) {
            cauldron.setColorOverride(true);
            cauldron.setColor(new Color(8439583));
        }
    };
    public static final BrewModifier GREEN = new BrewModifier("green") {
        @Override
        public void applyBrew(ItemStack brew) {
            if (brew.getNbt() != null) {
                brew.getNbt().putInt("color", 6192150);
            }
        }
        @Override
        public void applyCauldron(WitchsCauldronEntity cauldron) {
            cauldron.setColorOverride(true);
            cauldron.setColor(new Color(6192150));
        }
    };
    public static final BrewModifier CYAN = new BrewModifier("cyan") {
        @Override
        public void applyBrew(ItemStack brew) {
            if (brew.getNbt() != null) {
                brew.getNbt().putInt("color", 1481884);
            }
        }
        @Override
        public void applyCauldron(WitchsCauldronEntity cauldron) {
            cauldron.setColorOverride(true);
            cauldron.setColor(new Color(1481884));
        }
    };
    public static final BrewModifier LIGHT_BLUE = new BrewModifier("light_blue") {
        @Override
        public void applyBrew(ItemStack brew) {
            if (brew.getNbt() != null) {
                brew.getNbt().putInt("color", 3847130);
            }
        }
        @Override
        public void applyCauldron(WitchsCauldronEntity cauldron) {
            cauldron.setColorOverride(true);
            cauldron.setColor(new Color(3847130));
        }
    };
    public static final BrewModifier BLUE = new BrewModifier("blue") {
        @Override
        public void applyBrew(ItemStack brew) {
            if (brew.getNbt() != null) {
                brew.getNbt().putInt("color", 3949738);
            }
        }
        @Override
        public void applyCauldron(WitchsCauldronEntity cauldron) {
            cauldron.setColorOverride(true);
            cauldron.setColor(new Color(3949738));
        }
    };
    public static final BrewModifier PURPLE = new BrewModifier("purple") {
        @Override
        public void applyBrew(ItemStack brew) {
            if (brew.getNbt() != null) {
                brew.getNbt().putInt("color", 8991416);
            }
        }
        @Override
        public void applyCauldron(WitchsCauldronEntity cauldron) {
            cauldron.setColorOverride(true);
            cauldron.setColor(new Color(8991416));
        }
    };
    public static final BrewModifier MAGENTA = new BrewModifier("magenta") {
        @Override
        public void applyBrew(ItemStack brew) {
            if (brew.getNbt() != null) {
                brew.getNbt().putInt("color", 13061821);
            }
        }
        @Override
        public void applyCauldron(WitchsCauldronEntity cauldron) {
            cauldron.setColorOverride(true);
            cauldron.setColor(new Color(13061821));
        }
    };
    public static final BrewModifier PINK = new BrewModifier("pink") {
        @Override
        public void applyBrew(ItemStack brew) {
            if (brew.getNbt() != null) {
                brew.getNbt().putInt("color", 15961002);
            }
        }
        @Override
        public void applyCauldron(WitchsCauldronEntity cauldron) {
            cauldron.setColorOverride(true);
            cauldron.setColor(new Color(15961002));
        }
    };

    public static final Map<String, Modifier> modifiers = Map.of(
            NO_PARTICLES.getId(), NO_PARTICLES,
            INVERT.getId(), INVERT,
            SKIP_BLOCK.getId(), SKIP_BLOCK,
            SKIP_ENTITY.getId(), SKIP_ENTITY,
            WHITE.getId(), WHITE
    );

    public static Modifier getFromId(String id) {
        return modifiers.get(id);
    }
}
