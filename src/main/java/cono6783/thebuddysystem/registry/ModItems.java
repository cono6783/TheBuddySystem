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

    public static final RegistryObject<Item> BUDDY_SPAWNER = ITEM_DEFERRED_REGISTER.register("Buddy Spawner", () -> new Item(new Item.Properties()));
}
