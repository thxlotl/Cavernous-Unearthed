package net.thxlotl.cavernous.datagen.custom;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.*;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.client.renderer.block.model.VariantMutator;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ModBlockModelGenerators extends BlockModelGenerators {
    public ModBlockModelGenerators(Consumer<BlockModelDefinitionGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
        super(blockStateOutput, itemModelOutput, modelOutput);
    }

    public void createVine(VineBlock vineBlock, Boolean tinted) {
        this.createMultiface(vineBlock);
        Identifier Identifier = this.createFlatItemModelWithBlockTexture(vineBlock.asItem(), vineBlock);
        if (tinted)
        {
            this.registerSimpleTintedItemModel(vineBlock, Identifier, ItemModelUtils.constantTint(-12012264));
        }
        else
        {
            this.registerSimpleItemModel(vineBlock, Identifier);
        }
    }

    public void createMultiface(Block multifaceBlock) {
        this.createMultifaceBlockStates(multifaceBlock);
    }

    public void createMultifaceBlockStates(Block block) {
        Map<Property<Boolean>, VariantMutator> map = selectMultifaceProperties(block.defaultBlockState(), MultifaceBlock::getFaceProperty);
        ConditionBuilder conditionbuilder = condition();
        map.forEach((p_403915_, p_403916_) -> conditionbuilder.term(p_403915_, false));
        MultiVariant multivariant = plainVariant(ModelTemplates.MOSSY_CARPET_SIDE.create(block, TextureMapping.side(block), modelOutput));
        MultiPartGenerator multipartgenerator = MultiPartGenerator.multiPart(block);
        map.forEach((p_408981_, p_408982_) -> {
            multipartgenerator.with(condition().term(p_408981_, true), multivariant.with(p_408982_));
            multipartgenerator.with(conditionbuilder, multivariant.with(p_408982_));
        });
        this.blockStateOutput.accept(multipartgenerator);
    }

    public void createBlockWithRandomRotations(TexturedModel.Provider modelProvider, Block block) {
        Variant variant = plainModel(modelProvider.create(block, this.modelOutput));
        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, createRotatedVariants(variant)));
    }

}
