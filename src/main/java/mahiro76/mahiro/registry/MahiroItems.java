package mahiro76.mahiro.registry;

import mahiro76.mahiro.Mahiro;
import mahiro76.mahiro.registry.item.Crutch;
import mahiro76.mahiro.registry.item.Hardener;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class MahiroItems {

    //添加拐杖
    public static final Crutch CRUTCH = registerItem("crutch", new Crutch(new Item.Settings()));

    //添加固化剂
    public static final Hardener HARDENER = registerItem("hardener", new Hardener(new Item.Settings()));
    //添加物品辅助方法
    public static <T extends Item> T registerItem(String name, T item) {
        return Registry.register(Registries.ITEM, Identifier.of(Mahiro.MOD_ID, name), item);
    }

    //启动初始化方法
    public static void registerMahiroItems() {
        Mahiro.LOGGER.debug("Registering mod item for" + Mahiro.MOD_ID);
    }
}
