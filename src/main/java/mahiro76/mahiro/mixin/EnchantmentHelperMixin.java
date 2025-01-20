package mahiro76.mahiro.mixin;

import mahiro76.mahiro.registry.MahiroItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
    @Inject(method = "getKnockback", at = @At("RETURN"), cancellable = true)
    private static void getKnockback(LivingEntity entity, CallbackInfoReturnable<Integer> cir) {
        int value = cir.getReturnValue();
        if (entity.getMainHandStack().isOf(MahiroItems.CRUTCH)) {
            cir.setReturnValue(value + 5);
        }
    }
}
