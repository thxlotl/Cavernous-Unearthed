package net.thxlotl.cavernous.datagen.custom;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.*;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.BiConsumer;

public class ModItemModelGenerators extends ItemModelGenerators {
    public ModItemModelGenerators(ItemModelOutput itemModelOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
        super(itemModelOutput, modelOutput);
    }


    public void generateFlatItemWithBlockTexture(Block block, ModelTemplate modelTemplate) {
        this.itemModelOutput.accept(block.asItem(), ItemModelUtils.plainModel(this.createFlatBlockItemModel(block, modelTemplate)));
    }
    public Identifier createFlatBlockItemModel(Block block, ModelTemplate modelTemplate) {
        return modelTemplate.create(ModelLocationUtils.getModelLocation(block.asItem()), TextureMapping.layer0(block), this.modelOutput);
    }
}
