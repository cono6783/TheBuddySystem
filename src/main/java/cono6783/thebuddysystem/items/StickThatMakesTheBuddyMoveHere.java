package cono6783.thebuddysystem.items;

import com.mojang.logging.LogUtils;
import cono6783.thebuddysystem.entity.Buddy;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class StickThatMakesTheBuddyMoveHere extends Item {

    public StickThatMakesTheBuddyMoveHere(Properties properties) {
        super(properties);
    }

    /* This is no work
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) { // not sure why this isnt working
        LogUtils.getLogger().info("Outside");
        if (!level.isClientSide) {
            LogUtils.getLogger().info("Does is work");
            Buddy.setPosGoal(player.blockPosition());
        }


        return InteractionResultHolder.success(player.getItemInHand(interactionHand));
    } */



}
