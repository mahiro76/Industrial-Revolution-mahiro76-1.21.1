package mahiro76.mahiro.registry.item;

import mahiro76.mahiro.Mahiro;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;

public class Crutch {
    private static final Identifier ID = Identifier.of(Mahiro.MOD_ID, "crutch_knockback");

    public Crutch(Item.Settings settings) {
        super(settings.component(DataComponentTypes.ATTRIBUTE_MODIFIERS, modifyAttribute()));
    }

    // 添加属性，这里必须为静态方法，因为它在完成父类构造前被调用
    private static EntityAttributeModifier modifyAttribute() {
        // 物品的所有添加的属性
        List<EntityAttributeModifier.Entry> entries = List.of(
                // 击退属性
                createModifiers(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 5),
                // 攻击伤害属性
                createModifiers(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4));
        // 这里布尔值参数决定了是否显示属性修改的工具提示
        return new EntityAttributeModifier(entries, true);
    }

    /**
     * 创建属性修改组件，主手持有拐杖物品时修改器生效
     *
     * @param attribute 要修改的属性
     * @param value     要给属性增加的值
     * @return 属性修改组件
     */

    private static EntityAttributeModifier createModifier(EntityAttribute attribute, double value) {
        return new EntityAttributeModifier(ID, "modifier", value, EntityAttributeModifier.Operation.ADDITION);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.mahiro.crutch.tips"));
    }

}
