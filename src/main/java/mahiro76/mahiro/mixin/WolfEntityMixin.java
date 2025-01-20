package mahiro76.mahiro.mixin;

import mahiro76.mahiro.registry.MahiroItems;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;


@Mixin(WolfEntity.class)
public abstract class WolfEntityMixin extends TameableEntity implements Angerable {

    @Shadow
    public abstract DyeColor getCollarColor();
    @Shadow
    public abstract void setCollarColor(DyeColor color);


    public WolfEntityMixin(EntityType<? extends WolfEntity> entityType, World world) {
        super(entityType, world);
        this.setTamed(false);
        this.setPathfindingPenalty(PathNodeType.POWDER_SNOW, -1.0F);
        this.setPathfindingPenalty(PathNodeType.DANGER_POWDER_SNOW, -1.0F);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (this.getWorld().isClient) {
            boolean bl = this.isOwner(player) || this.isTamed() || itemStack.isOf(Items.BONE) && !this.isTamed() && !this.hasAngerTime();
            return bl ? ActionResult.CONSUME : ActionResult.PASS;
        }
        else
            if (this.isTamed()) {
                if (this.isBreedingItem(itemStack) && this.getHealth() < this.getMaxHealth()) {
                    if (!player.getAbilities().creativeMode) {
                        itemStack.decrement(1);
                    }

                    this.heal((float)item.getFoodComponent().getHunger());
                    return ActionResult.SUCCESS;
                }
                else {
                    if (itemStack.isOf(MahiroItems.CHOCOLATE)){
                        if (!player.getAbilities().creativeMode) {
                            itemStack.decrement(1);
                        }

                        this.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 1200));
                    }
                    else {
                        if (item instanceof DyeItem dyeItem && this.isOwner(player)) {
                            DyeColor dyeColor = dyeItem.getColor();
                            if (dyeColor != this.getCollarColor()) {
                                this.setCollarColor(dyeColor);
                                if (!player.getAbilities().creativeMode) {
                                    itemStack.decrement(1);
                                }

                                return ActionResult.SUCCESS;
                            }

                            return super.interactMob(player, hand);
                        }
                    }

                        ActionResult actionResult = super.interactMob(player, hand);
                        if ((!actionResult.isAccepted() || this.isBaby()) && this.isOwner(player)) {
                            this.setSitting(!this.isSitting());
                            this.jumping = false;
                            this.navigation.stop();
                            this.setTarget(null);
                            return ActionResult.SUCCESS;
                        }
                        else {
                            return actionResult;
                        }

                }
            }
            else if (itemStack.isOf(Items.BONE) && !this.hasAngerTime()) {
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }

            if (this.random.nextInt(3) == 0) {
                this.setOwner(player);
                this.navigation.stop();
                this.setTarget(null);
                this.setSitting(true);
                this.getWorld().sendEntityStatus(this, EntityStatuses.ADD_POSITIVE_PLAYER_REACTION_PARTICLES);
            } else {
                this.getWorld().sendEntityStatus(this, EntityStatuses.ADD_NEGATIVE_PLAYER_REACTION_PARTICLES);
            }

            return ActionResult.SUCCESS;
        } else {
            return super.interactMob(player, hand);
        }
    }
}

