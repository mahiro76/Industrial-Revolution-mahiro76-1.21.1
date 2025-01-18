package mahiro76.mahiro.mixin;

import mahiro76.mahiro.registry.MahiroItems;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

//燃料mixin
@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class MahiroAbstractFurnaceBlockEntityMixin {

    //添加燃料时间
    @Inject(method = "createFuelTimeMap", at = @At("RETURN"), cancellable = true)
    private static void addFuelItems(CallbackInfoReturnable<Map<Item, Integer>> cir) {
        Map<Item, Integer> fuelTimes = cir.getReturnValue();
        //在这里添加物品及其燃料时间（单位：tick）
        fuelTimes.put(MahiroItems.CRUTCH, 300);
        cir.setReturnValue(fuelTimes);
    }
}
