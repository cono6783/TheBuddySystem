package cono6783.thebuddysystem.menus;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

import static cono6783.thebuddysystem.registry.ModMenus.BUDDY_MENU;

public class BuddyMenu extends AbstractContainerMenu {

    private final Container container;
    private final Player player;



    public BuddyMenu(int containerID, Inventory playerInv) {
        this(containerID, playerInv, new SimpleContainer());
    }

    public BuddyMenu(int containerID, Inventory playerInv, Container container) { // May need to make this use an IContinerFactory if i cannot have any other way of linkning the buddy to the menu; Will probably not have to b/c the menu should have a link to the buddy; https://docs.minecraftforge.net/en/1.20.1/gui/menus/
        super(BUDDY_MENU.get(), containerID);
        //Initialize the slots here see crafting inventory for example net.minecraft.world.inventory
        this.container = container;
        this.player = playerInv.player;

    }


    @Override
    public boolean stillValid(Player player) {
        return true; // need to make this check if the player is within a certain distance of the buddy and if the buddy is still alive
    }


    @Override
    public ItemStack quickMoveStack(Player p_219987_, int p_219988_) {
        return ItemStack.EMPTY;
    }
}
