package cono6783.thebuddysystem.registry;

import cono6783.thebuddysystem.entity.Buddy;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static cono6783.thebuddysystem.TheBuddySystem.MODID;

public class ModEntities {


    public static final DeferredRegister<EntityType<?>> ENTITY_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    public static final RegistryObject<EntityType<cono6783.thebuddysystem.entity.Buddy>> Buddy = ENTITY_DEFERRED_REGISTER.register("buddy", () -> EntityType.Builder.of(Buddy::new, MobCategory.CREATURE).build("buddy"));
}
