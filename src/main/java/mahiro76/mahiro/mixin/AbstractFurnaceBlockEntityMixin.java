package mahiro76.mahiro.mixin;

import mahiro76.mahiro.registry.MahiroItems;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceBlockEntityMixin {

    @Inject(method = "createFuelTimeMap", at = @At("TAIL"), cancellable = true)
    private static void addFuelItems(CallbackInfoReturnable<Map<Item, Integer>> callbackInfoReturnable) {
        Map<Item, Integer> map = callbackInfoReturnable.getReturnValue();
        map.put(MahiroItems.CRUTCH, 300); // 添加物品及其燃料时间（单位：tick）
        callbackInfoReturnable.setReturnValue(map);
    }
}