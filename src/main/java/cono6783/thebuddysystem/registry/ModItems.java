package cono6783.thebuddysystem.registry;

import cono6783.thebuddysystem.items.BuddySpawnerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static cono6783.thebuddysystem.TheBuddySystem.MODID;

public class ModItems {

    public static final DeferredRegister<Item> ITEM_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> BUDDY_MOVE_STICK = ITEM_DEFERRED_REGISTER.register("buddymovestick", () -> new Item(new Item.Properties()));
}
