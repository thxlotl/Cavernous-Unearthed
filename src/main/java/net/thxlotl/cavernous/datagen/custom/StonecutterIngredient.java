package net.thxlotl.cavernous.datagen.custom;

import net.minecraft.world.level.block.Block;

public class StonecutterIngredient {

    public int multiplier;
    public Block block;

    public StonecutterIngredient(int multiplier, Block ingredient) {
        this.multiplier = multiplier;
        this.block = ingredient;
    }

    public static StonecutterIngredient of(int multiplier, Block ingredient) {
        return new StonecutterIngredient(multiplier, ingredient);
    }

}
