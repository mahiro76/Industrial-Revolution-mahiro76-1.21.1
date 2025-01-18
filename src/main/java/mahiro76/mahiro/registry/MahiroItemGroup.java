package mahiro76.mahiro.registry;

import mahiro76.mahiro.Mahiro;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class MahiroItemGroup {
    // 武器物品类
    public static ItemGroup FIGHT = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Mahiro.MOD_ID, "fight"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.mahiro.fight"))
                    .icon(() -> new ItemStack(MahiroItems.CRUTCH))
                    .entries((displayContext, entries) -> {
                        entries.add(MahiroItems.CRUTCH);
                    }).build());

    // 材料物品类
    public static ItemGroup MATERIAL = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Mahiro.MOD_ID, "material"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.mahiro.material"))
                    .icon(() -> new ItemStack(MahiroItems.HARDENER))
                    .entries((displayContext, entries) -> {
                        entries.add(MahiroItems.HARDENER);
                    }).build());

    // 启动初始化方法
    public static void registerModItemGroup() {
        Mahiro.LOGGER.debug("registerModItemGroup " + Mahiro.MOD_ID);
    }
}