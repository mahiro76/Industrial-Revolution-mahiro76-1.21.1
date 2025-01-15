package mahiro76.mahiro.datageneration;

package mahiro76.mahiro.datageneration;

import mahiro76.mahiro.registry.MahiroBlocks;
import mahiro76.mahiro.registry.MahiroItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * 配方生成器
 *
 * @see <a href="https://fabricmc.net/wiki/zh_cn:tutorial:datagen_recipe">配方生成</a>
 */
public class MahiroRecipeGenerator extends FabricRecipeProvider {
    private MahiroRecipeGenerator(FabricDataOutput generator) {
        super(generator);
    }

    /**
     * 生成合成配方
     *
     * @see VanillaRecipeProvider
     * @see RecipeProvider
     */
    @Override
    public void generate(Consumer<RecipeJsonProvider> consumer) {
        //生成无序合成配方

        //生成有序合成配方
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, MahiroItems.CRUTCH)
                .pattern("S  ")
                .pattern("S  ")
                .pattern("   ")
                .input('S', Items.STICK)
                .criterion(hasItem(Items.STICK), RecipeProvider.conditionsFromItem(Items.STICK))
                .offerTo(consumer);
        //添加熔炼配方
        CookingRecipeJsonBuilder.createSmelting(
                        Ingredient.ofItems(MahiroBlocks.Limestone),
                        RecipeCategory.MISC,
                        Items.WHITE_CONCRETE_POWDER,
                        0.7f,
                        100)
                .criterion(hasItem(MahiroBlocks.Limestone), RecipeProvider.conditionsFromItem(MahiroBlocks.Limestone))
                .offerTo(consumer);
        //混凝土配方
        offerConcretePowderDyeingMoreRecipe((FabricRecipeProvider) consumer, Blocks.BLACK_CONCRETE_POWDER, Items.BLACK_DYE);
        offerConcretePowderDyeingMoreRecipe((FabricRecipeProvider) consumer, Blocks.BLUE_CONCRETE_POWDER, Items.BLUE_DYE);
        offerConcretePowderDyeingMoreRecipe((FabricRecipeProvider) consumer, Blocks.BROWN_CONCRETE_POWDER, Items.BROWN_DYE);
        offerConcretePowderDyeingMoreRecipe((FabricRecipeProvider) consumer, Blocks.CYAN_CONCRETE_POWDER, Items.CYAN_DYE);
        offerConcretePowderDyeingMoreRecipe((FabricRecipeProvider) consumer, Blocks.GRAY_CONCRETE_POWDER, Items.GRAY_DYE);
        offerConcretePowderDyeingMoreRecipe((FabricRecipeProvider) consumer, Blocks.GREEN_CONCRETE_POWDER, Items.GREEN_DYE);
        offerConcretePowderDyeingMoreRecipe((FabricRecipeProvider) consumer, Blocks.LIGHT_BLUE_CONCRETE_POWDER, Items.LIGHT_BLUE_DYE);
        offerConcretePowderDyeingMoreRecipe((FabricRecipeProvider) consumer, Blocks.LIGHT_GRAY_CONCRETE_POWDER, Items.LIGHT_GRAY_DYE);
        offerConcretePowderDyeingMoreRecipe((FabricRecipeProvider) consumer, Blocks.LIME_CONCRETE_POWDER, Items.LIME_DYE);
        offerConcretePowderDyeingMoreRecipe((FabricRecipeProvider) consumer, Blocks.MAGENTA_CONCRETE_POWDER, Items.MAGENTA_DYE);
        offerConcretePowderDyeingMoreRecipe((FabricRecipeProvider) consumer, Blocks.ORANGE_CONCRETE_POWDER, Items.ORANGE_DYE);
        offerConcretePowderDyeingMoreRecipe((FabricRecipeProvider) consumer, Blocks.PINK_CONCRETE_POWDER, Items.PINK_DYE);
        offerConcretePowderDyeingMoreRecipe((FabricRecipeProvider) consumer, Blocks.PURPLE_CONCRETE_POWDER, Items.PURPLE_DYE);
        offerConcretePowderDyeingMoreRecipe((FabricRecipeProvider) consumer, Blocks.RED_CONCRETE_POWDER, Items.RED_DYE);
        offerConcretePowderDyeingMoreRecipe((FabricRecipeProvider) consumer, Blocks.YELLOW_CONCRETE_POWDER, Items.YELLOW_DYE);
    }

    //混凝土粉末染色更多配方辅助方法
    public static void offerConcretePowderDyeingMoreRecipe(FabricRecipeProvider exporter, ItemConvertible output, ItemConvertible input) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output)
                .input(input)
                .input(Items.WHITE_CONCRETE_POWDER)
                .criterion(hasItem(Items.WHITE_CONCRETE_POWDER), RecipeProvider.conditionsFromItem(Items.WHITE_CONCRETE_POWDER))
                .offerTo(exporter);
    }

    private static String hasItem(Item item) {
        return "has_" + Registries.ITEM.getId(item);
    }

}
