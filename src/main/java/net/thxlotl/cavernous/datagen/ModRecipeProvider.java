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
import net.minecraft.world.level.block.*;
import net.neoforged.fml.common.Mod;
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


    private void makeStoneFamilyRecipes(Block parent, StairBlock stairBlock, SlabBlock slabBlock, WallBlock wallBlock) {
        stairBuilder(stairBlock, Ingredient.of(parent)).unlockedBy("has_" + parent.getName(), has(parent)).save(output);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, slabBlock, Ingredient.of(parent)).unlockedBy("has_" + parent.getName(), has(parent)).save(output);
        wallBuilder(RecipeCategory.BUILDING_BLOCKS, wallBlock, Ingredient.of(parent)).unlockedBy("has_" + parent.getName(), has(parent)).save(output);
    }

    private void makeRefineRecipe(Block input, Block result) {
        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.BUILDING_BLOCKS, result, 4)
                .pattern("   ")
                .pattern(" FF")
                .pattern(" FF")
                .define('F', input)
                .unlockedBy("has_" + input.getName(), has(input)).save(output);
    }

    private void makeStoneFamilyStonecutterRecipes(Block parent, StairBlock stairBlock, SlabBlock slabBlock, WallBlock wallBlock, Block... ingredients) {

        if (ingredients.length == 0) {
            stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, stairBlock, parent, 1);
            stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, slabBlock, parent, 2);
            stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, wallBlock, parent, 1);
        }

        for (Block ingredient : ingredients) {
            stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, stairBlock, ingredient, 1);
            stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, slabBlock, ingredient, 2);
            stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, wallBlock, ingredient, 1);
            if (ingredient != parent) {
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, parent, ingredient, 1);
            }
        }

    }

    private void stonecutterResultFromIngredients(Block result, int amount, Block... ingredients) {

        for (Block ingredient : ingredients) {
            stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, result, ingredient, amount);
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
        makeRefineRecipe(ModBlocks.GROUND_FUNGATITE.get(), ModBlocks.FUNGATITE.get());
        makeStoneFamilyRecipes(
                ModBlocks.FUNGATITE.get(),
                ModBlocks.FUNGATITE_STAIRS.get(),
                ModBlocks.FUNGATITE_SLAB.get(),
                ModBlocks.FUNGATITE_WALL.get());
        makeStoneFamilyStonecutterRecipes(
                ModBlocks.FUNGATITE.get(),
                ModBlocks.FUNGATITE_STAIRS.get(),
                ModBlocks.FUNGATITE_SLAB.get(),
                ModBlocks.FUNGATITE_WALL.get());

        // Polished Fungatite
        makeRefineRecipe(ModBlocks.FUNGATITE.get(), ModBlocks.POLISHED_FUNGATITE.get());
        makeStoneFamilyRecipes(
                ModBlocks.POLISHED_FUNGATITE.get(),
                ModBlocks.POLISHED_FUNGATITE_STAIRS.get(),
                ModBlocks.POLISHED_FUNGATITE_SLAB.get(),
                ModBlocks.POLISHED_FUNGATITE_WALL.get());
        makeStoneFamilyStonecutterRecipes(
                ModBlocks.POLISHED_FUNGATITE.get(),
                ModBlocks.POLISHED_FUNGATITE_STAIRS.get(),
                ModBlocks.POLISHED_FUNGATITE_SLAB.get(),
                ModBlocks.POLISHED_FUNGATITE_WALL.get(),
                ModBlocks.FUNGATITE.get());
        chiseled(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_FUNGATITE, ModBlocks.POLISHED_FUNGATITE_SLAB);

        stonecutterResultFromIngredients(ModBlocks.CHISELED_FUNGATITE.get(), 1, ModBlocks.FUNGATITE.get(), ModBlocks.POLISHED_FUNGATITE.get());

        // Fungatite Bricks
        makeRefineRecipe(ModBlocks.POLISHED_FUNGATITE.get(), ModBlocks.FUNGATITE_BRICKS.get());
        makeStoneFamilyRecipes(
                ModBlocks.FUNGATITE_BRICKS.get(),
                ModBlocks.FUNGATITE_BRICK_STAIRS.get(),
                ModBlocks.FUNGATITE_BRICK_SLAB.get(),
                ModBlocks.FUNGATITE_BRICK_WALL.get());
        makeStoneFamilyStonecutterRecipes(
                ModBlocks.FUNGATITE_BRICKS.get(),
                ModBlocks.FUNGATITE_BRICK_STAIRS.get(),
                ModBlocks.FUNGATITE_BRICK_SLAB.get(),
                ModBlocks.FUNGATITE_BRICK_WALL.get(),
                ModBlocks.FUNGATITE.get(), ModBlocks.POLISHED_FUNGATITE.get());

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


        //region Obsidianstone

        makeStoneFamilyRecipes(
                ModBlocks.OBSIDIANSTONE.get(),
                ModBlocks.OBSIDIANSTONE_STAIRS.get(),
                ModBlocks.OBSIDIANSTONE_SLAB.get(),
                ModBlocks.OBSIDIANSTONE_WALL.get());
        makeStoneFamilyStonecutterRecipes(
                ModBlocks.OBSIDIANSTONE.get(),
                ModBlocks.OBSIDIANSTONE_STAIRS.get(),
                ModBlocks.OBSIDIANSTONE_SLAB.get(),
                ModBlocks.OBSIDIANSTONE_WALL.get());

        makeRefineRecipe(ModBlocks.OBSIDIANSTONE.get(), ModBlocks.POLISHED_OBSIDIANSTONE.get());
        makeStoneFamilyRecipes(
                ModBlocks.POLISHED_OBSIDIANSTONE.get(),
                ModBlocks.POLISHED_OBSIDIANSTONE_STAIRS.get(),
                ModBlocks.POLISHED_OBSIDIANSTONE_SLAB.get(),
                ModBlocks.POLISHED_OBSIDIANSTONE_WALL.get());
        makeStoneFamilyStonecutterRecipes(
                ModBlocks.POLISHED_OBSIDIANSTONE.get(),
                ModBlocks.POLISHED_OBSIDIANSTONE_STAIRS.get(),
                ModBlocks.POLISHED_OBSIDIANSTONE_SLAB.get(),
                ModBlocks.POLISHED_OBSIDIANSTONE_WALL.get(),
                ModBlocks.OBSIDIANSTONE.get());

        makeRefineRecipe(ModBlocks.POLISHED_OBSIDIANSTONE.get(), ModBlocks.OBSIDIANSTONE_BRICKS.get());
        makeStoneFamilyRecipes(
                ModBlocks.OBSIDIANSTONE_BRICKS.get(),
                ModBlocks.OBSIDIANSTONE_BRICK_STAIRS.get(),
                ModBlocks.OBSIDIANSTONE_BRICK_SLAB.get(),
                ModBlocks.OBSIDIANSTONE_BRICK_WALL.get());
        makeStoneFamilyStonecutterRecipes(
                ModBlocks.OBSIDIANSTONE_BRICKS.get(),
                ModBlocks.OBSIDIANSTONE_BRICK_STAIRS.get(),
                ModBlocks.OBSIDIANSTONE_BRICK_SLAB.get(),
                ModBlocks.OBSIDIANSTONE_BRICK_WALL.get(),
                ModBlocks.POLISHED_OBSIDIANSTONE.get(),
                ModBlocks.OBSIDIANSTONE.get());

        //endregion
    }

}
