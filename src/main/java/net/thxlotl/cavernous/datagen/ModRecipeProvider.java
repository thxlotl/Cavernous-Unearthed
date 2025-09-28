package net.thxlotl.cavernous.datagen;

import net.minecraft.client.data.models.blockstates.ConditionBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.item.ModItems;
import net.thxlotl.cavernous.util.ModTags;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
            super(packOutput, provider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new ModRecipeProvider(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "My Recipes";
        }
    }


    @Override
    protected void buildRecipes() {

        // Feather Moss
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.BUILDING_BLOCKS, ModBlocks.FEATHER_MOSS_CARPET, 3)
                .pattern("   ")
                .pattern("   ")
                .pattern(" FF")
                .define('F', ModBlocks.FEATHER_MOSS_BLOCK.get())
                .unlockedBy("has_feather_moss", has(ModBlocks.FEATHER_MOSS_BLOCK)).save(output);
        ShapelessRecipeBuilder.shapeless(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.BUILDING_BLOCKS, Blocks.MOSSY_COBBLESTONE, 1)
                .requires(ModBlocks.FEATHER_MOSS_BLOCK)
                .requires(Blocks.COBBLESTONE)
                .unlockedBy("has_feather_moss", has(ModBlocks.FEATHER_MOSS_BLOCK)).save(output);
        ShapelessRecipeBuilder.shapeless(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.BUILDING_BLOCKS, Blocks.MOSSY_STONE_BRICKS, 1)
                .requires(ModBlocks.FEATHER_MOSS_BLOCK)
                .requires(Blocks.STONE_BRICKS)
                .unlockedBy("has_feather_moss", has(ModBlocks.FEATHER_MOSS_BLOCK)).save(output);
        ShapelessRecipeBuilder.shapeless(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.BUILDING_BLOCKS, ModBlocks.FEATHER_MOSS_TUFTS, 4)
                .requires(ModBlocks.FEATHER_MOSS_BLOCK)
                .unlockedBy("has_feather_moss", has(ModBlocks.FEATHER_MOSS_BLOCK)).save(output);
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.BUILDING_BLOCKS, ModBlocks.FEATHER_MOSS_BLOCK, 1)
                .pattern("   ")
                .pattern(" FF")
                .pattern(" FF")
                .define('F', ModBlocks.FEATHER_MOSS_TUFTS.get())
                .unlockedBy("has_feather_moss_tufts", has(ModBlocks.FEATHER_MOSS_TUFTS)).save(output);


        // Fungatite
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.BUILDING_BLOCKS, ModBlocks.FUNGATITE, 4)
                .pattern("   ")
                .pattern(" FF")
                .pattern(" FF")
                .define('F', ModBlocks.GROUND_FUNGATITE.get())
                .unlockedBy("has_ground_fungatite", has(ModBlocks.GROUND_FUNGATITE)).save(output);
        stairBuilder(ModBlocks.FUNGATITE_STAIRS.get(), Ingredient.of(ModBlocks.FUNGATITE)).unlockedBy("has_fungatite", has(ModBlocks.FUNGATITE)).save(output);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FUNGATITE_SLAB.get(), Ingredient.of(ModBlocks.FUNGATITE)).unlockedBy("has_fungatite", has(ModBlocks.FUNGATITE)).save(output);
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FUNGATITE_WALL.get(), Ingredient.of(ModBlocks.FUNGATITE)).unlockedBy("has_fungatite", has(ModBlocks.FUNGATITE)).save(output);
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_FUNGATITE, 4)
                .pattern("   ")
                .pattern(" FF")
                .pattern(" FF")
                .define('F', ModBlocks.FUNGATITE.get())
                .unlockedBy("has_fungatite", has(ModBlocks.FUNGATITE)).save(output);

        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FUNGATITE_STAIRS, ModBlocks.FUNGATITE, 1);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FUNGATITE_SLAB, ModBlocks.FUNGATITE, 2);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FUNGATITE_WALL, ModBlocks.FUNGATITE, 1);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_FUNGATITE, ModBlocks.FUNGATITE, 1);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_FUNGATITE_STAIRS, ModBlocks.FUNGATITE, 1);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_FUNGATITE_SLAB, ModBlocks.FUNGATITE, 2);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_FUNGATITE_WALL, ModBlocks.FUNGATITE, 1);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_FUNGATITE, ModBlocks.FUNGATITE, 1);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FUNGATITE_BRICKS, ModBlocks.FUNGATITE, 1);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FUNGATITE_BRICK_STAIRS, ModBlocks.FUNGATITE, 1);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FUNGATITE_BRICK_SLAB, ModBlocks.FUNGATITE, 2);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FUNGATITE_BRICK_WALL, ModBlocks.FUNGATITE, 1);

        // Polished Fungatite
        stairBuilder(ModBlocks.POLISHED_FUNGATITE_STAIRS.get(), Ingredient.of(ModBlocks.POLISHED_FUNGATITE)).unlockedBy("has_polished_fungatite", has(ModBlocks.POLISHED_FUNGATITE)).save(output);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_FUNGATITE_SLAB.get(), Ingredient.of(ModBlocks.POLISHED_FUNGATITE)).unlockedBy("has_polished_fungatite", has(ModBlocks.POLISHED_FUNGATITE)).save(output);
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_FUNGATITE_WALL.get(), Ingredient.of(ModBlocks.POLISHED_FUNGATITE)).unlockedBy("has_polished_fungatite", has(ModBlocks.POLISHED_FUNGATITE)).save(output);
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.BUILDING_BLOCKS, ModBlocks.FUNGATITE_BRICKS, 4)
                .pattern("   ")
                .pattern(" FF")
                .pattern(" FF")
                .define('F', ModBlocks.POLISHED_FUNGATITE.get())
                .unlockedBy("has_polished_fungatite", has(ModBlocks.POLISHED_FUNGATITE)).save(output);
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_FUNGATITE, 1)
                .pattern("   ")
                .pattern("  F")
                .pattern("  F")
                .define('F', ModBlocks.POLISHED_FUNGATITE_SLAB.get())
                .unlockedBy("has_polished_fungatite_slab", has(ModBlocks.POLISHED_FUNGATITE_SLAB)).save(output);

        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_FUNGATITE_STAIRS, ModBlocks.POLISHED_FUNGATITE, 1);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_FUNGATITE_SLAB, ModBlocks.POLISHED_FUNGATITE, 2);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_FUNGATITE_WALL, ModBlocks.POLISHED_FUNGATITE, 1);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_FUNGATITE, ModBlocks.POLISHED_FUNGATITE, 1);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FUNGATITE_BRICKS, ModBlocks.POLISHED_FUNGATITE, 1);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FUNGATITE_BRICK_STAIRS, ModBlocks.POLISHED_FUNGATITE, 1);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FUNGATITE_BRICK_SLAB, ModBlocks.POLISHED_FUNGATITE, 2);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FUNGATITE_BRICK_WALL, ModBlocks.POLISHED_FUNGATITE, 1);

        // Fungatite Bricks
        stairBuilder(ModBlocks.FUNGATITE_BRICK_STAIRS.get(), Ingredient.of(ModBlocks.FUNGATITE_BRICKS)).unlockedBy("has_fungatite_bricks", has(ModBlocks.FUNGATITE_BRICKS)).save(output);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FUNGATITE_BRICK_SLAB.get(), Ingredient.of(ModBlocks.FUNGATITE_BRICKS)).unlockedBy("has_fungatite_bricks", has(ModBlocks.FUNGATITE_BRICKS)).save(output);
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FUNGATITE_BRICK_WALL.get(), Ingredient.of(ModBlocks.FUNGATITE_BRICKS)).unlockedBy("has_fungatite_bricks", has(ModBlocks.FUNGATITE_BRICKS)).save(output);

        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FUNGATITE_BRICK_STAIRS, ModBlocks.FUNGATITE_BRICKS, 1);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FUNGATITE_BRICK_SLAB, ModBlocks.FUNGATITE_BRICKS, 2);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FUNGATITE_BRICK_WALL, ModBlocks.FUNGATITE_BRICKS, 1);

        // Shroomwood
        woodFromLogs(ModBlocks.SHROOMWOOD.get(), ModBlocks.SHROOMWOOD_LOG.get());
        woodFromLogs(ModBlocks.STRIPPED_SHROOMWOOD.get(), ModBlocks.STRIPPED_SHROOMWOOD_LOG.get());
        ShapelessRecipeBuilder.shapeless(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHROOMWOOD_PLANKS, 4)
                .requires(ModTags.Items.SHROOMWOOD_LOGS)
                .unlockedBy("has_shroomwood_logs", has(ModTags.Items.SHROOMWOOD_LOGS)).save(output);

        stairBuilder(ModBlocks.SHROOMWOOD_STAIRS.get(), Ingredient.of(ModBlocks.SHROOMWOOD_PLANKS)).unlockedBy("has_shroomwood_planks", has(ModBlocks.SHROOMWOOD_PLANKS)).save(output);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHROOMWOOD_SLAB.get(), Ingredient.of(ModBlocks.SHROOMWOOD_PLANKS)).unlockedBy("has_shroomwood_planks", has(ModBlocks.SHROOMWOOD_PLANKS)).save(output);
        fenceBuilder(ModBlocks.SHROOMWOOD_FENCE.get(), Ingredient.of(ModBlocks.SHROOMWOOD_PLANKS)).unlockedBy("has_shroomwood_planks", has(ModBlocks.SHROOMWOOD_PLANKS)).save(output);
        fenceGateBuilder(ModBlocks.SHROOMWOOD_FENCE_GATE.get(), Ingredient.of(ModBlocks.SHROOMWOOD_PLANKS)).unlockedBy("has_shroomwood_planks", has(ModBlocks.SHROOMWOOD_PLANKS)).save(output);
        doorBuilder(ModBlocks.SHROOMWOOD_DOOR.get(), Ingredient.of(ModBlocks.SHROOMWOOD_PLANKS)).unlockedBy("has_shroomwood_planks", has(ModBlocks.SHROOMWOOD_PLANKS)).save(output);
        trapdoorBuilder(ModBlocks.SHROOMWOOD_TRAPDOOR.get(), Ingredient.of(ModBlocks.SHROOMWOOD_PLANKS)).unlockedBy("has_shroomwood_planks", has(ModBlocks.SHROOMWOOD_PLANKS)).save(output);
        pressurePlateBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHROOMWOOD_PRESSURE_PLATE.get(), Ingredient.of(ModBlocks.SHROOMWOOD_PLANKS)).unlockedBy("has_shroomwood_planks", has(ModBlocks.SHROOMWOOD_PLANKS)).save(output);
        buttonBuilder(ModBlocks.SHROOMWOOD_BUTTON.get(), Ingredient.of(ModBlocks.SHROOMWOOD_PLANKS)).unlockedBy("has_shroomwood_planks", has(ModBlocks.SHROOMWOOD_PLANKS)).save(output);
        signBuilder(ModBlocks.SHROOMWOOD_SIGN, Ingredient.of(ModBlocks.SHROOMWOOD_PLANKS)).unlockedBy("has_shroomwood_planks", has(ModBlocks.SHROOMWOOD_PLANKS)).save(output);
        hangingSign(ModBlocks.SHROOMWOOD_HANGING_SIGN, ModBlocks.STRIPPED_SHROOMWOOD_LOG);

        oreSmelting(List.of(ModBlocks.FUNGATITE_COAL_ORE.asItem()),RecipeCategory.MISC,Items.COAL,0.1f,200,"coal");
        oreSmelting(List.of(ModBlocks.FUNGATITE_IRON_ORE.asItem()),RecipeCategory.MISC,Items.IRON_INGOT,0.7f,200,"iron");
        oreSmelting(List.of(ModBlocks.FUNGATITE_COPPER_ORE.asItem()),RecipeCategory.MISC,Items.COPPER_INGOT,0.7f,200,"copper");
        oreSmelting(List.of(ModBlocks.FUNGATITE_GOLD_ORE.asItem()),RecipeCategory.MISC,Items.GOLD_INGOT,1.0f,200,"gold");
        oreSmelting(List.of(ModBlocks.FUNGATITE_REDSTONE_ORE.asItem()),RecipeCategory.MISC,Items.REDSTONE,0.7f,200,"redstone");
        oreSmelting(List.of(ModBlocks.FUNGATITE_EMERALD_ORE.asItem()),RecipeCategory.MISC,Items.EMERALD,1.0f,200,"emerald");
        oreSmelting(List.of(ModBlocks.FUNGATITE_LAPIS_ORE.asItem()),RecipeCategory.MISC,Items.LAPIS_LAZULI,0.2f,200,"lapis");
        oreSmelting(List.of(ModBlocks.FUNGATITE_DIAMOND_ORE.asItem()),RecipeCategory.MISC,Items.DIAMOND,1.0f,200,"diamond");

        oreBlasting(List.of(ModBlocks.FUNGATITE_COAL_ORE.asItem()),RecipeCategory.MISC,Items.COAL,0.1f,100,"coal");
        oreBlasting(List.of(ModBlocks.FUNGATITE_IRON_ORE.asItem()),RecipeCategory.MISC,Items.IRON_INGOT,0.7f,100,"iron");
        oreBlasting(List.of(ModBlocks.FUNGATITE_COPPER_ORE.asItem()),RecipeCategory.MISC,Items.COPPER_INGOT,0.7f,100,"copper");
        oreBlasting(List.of(ModBlocks.FUNGATITE_GOLD_ORE.asItem()),RecipeCategory.MISC,Items.GOLD_INGOT,1.0f,100,"gold");
        oreBlasting(List.of(ModBlocks.FUNGATITE_REDSTONE_ORE.asItem()),RecipeCategory.MISC,Items.REDSTONE,0.7f,100,"redstone");
        oreBlasting(List.of(ModBlocks.FUNGATITE_EMERALD_ORE.asItem()),RecipeCategory.MISC,Items.EMERALD,1.0f,100,"emerald");
        oreBlasting(List.of(ModBlocks.FUNGATITE_LAPIS_ORE.asItem()),RecipeCategory.MISC,Items.LAPIS_LAZULI,0.2f,100,"lapis");
        oreBlasting(List.of(ModBlocks.FUNGATITE_DIAMOND_ORE.asItem()),RecipeCategory.MISC,Items.DIAMOND,1.0f,100,"diamond");


        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAMPSHROOM_TERRARIUM)
                .pattern("GPG")
                .pattern("GLG")
                .pattern("GUG")
                .define('G', Blocks.GLASS)
                .define('P', ItemTags.PLANKS)
                .define('L', ModBlocks.LAMPSHROOM)
                .define('U', ModBlocks.UNDERGROUND_MYCELIUM)
                .unlockedBy("has_lampshroom", has(ModBlocks.LAMPSHROOM)).save(output);
    }

}
