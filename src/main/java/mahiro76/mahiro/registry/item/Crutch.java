package mahiro76.mahiro.registry.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;


public class Crutch extends Item {

    // 创建UUID
    private static final UUID KNOCKBACK_MODIFIER_ID = UUID.randomUUID();
    private static final UUID DAMAGE_MODIFIER_ID = UUID.randomUUID();

    public Crutch(Settings settings) {
        super(settings);
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(ItemStack stack,EquipmentSlot slot ) {
        Multimap<EntityAttribute, EntityAttributeModifier> modifiers = HashMultimap.create(super.getAttributeModifiers(stack, slot));
        // 如果是主手
        if (slot == EquipmentSlot.MAINHAND) {
            // 添加击退属性
            modifiers.put(EntityAttributes.GENERIC_ATTACK_KNOCKBACK,
                    new EntityAttributeModifier(KNOCKBACK_MODIFIER_ID, "Weapon modifier", 5.0, EntityAttributeModifier.Operation.ADDITION));
            // 添加攻击伤害属性
            modifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                    new EntityAttributeModifier(DAMAGE_MODIFIER_ID, "Weapon modifier", 5.0, EntityAttributeModifier.Operation.ADDITION));
        }
        // 返回属性
        return modifiers;
    }

    // 覆写方法，添加物品提示文本
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.mahiro.crutch.tips"));
    }

}