package cono6783.thebuddysystem.registry;

import cono6783.thebuddysystem.menus.BuddyMenu;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static cono6783.thebuddysystem.TheBuddySystem.MODID;

public class ModMenus {

    public static final DeferredRegister<MenuType<?>> MENU_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MODID);
    public static final RegistryObject<MenuType<BuddyMenu>> BUDDY_MENU = MENU_DEFERRED_REGISTER.register("buddy_menu", () -> new MenuType(BuddyMenu::new, FeatureFlags.DEFAULT_FLAGS));
}
