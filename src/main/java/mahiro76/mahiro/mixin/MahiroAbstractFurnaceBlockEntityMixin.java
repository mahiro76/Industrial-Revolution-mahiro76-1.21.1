package mahiro76.mahiro.mixin;

import mahiro76.mahiro.registry.MahiroItems;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.Item;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

//燃料mixin
@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class MahiroAbstractFurnaceBlockEntityMixin {

    @Shadow private static volatile @Nullable Map<Item, Integer> fuelTimes;
    //添加燃料时间
    @Inject(method = "createFuelTimeMap",at = @At("TAIL"))
    private static void addFuelItems(CallbackInfoReturnable<Map<Item, Integer>> cir){
        fuelTimes.put(MahiroItems.CRUTCH,300);

    }
}
