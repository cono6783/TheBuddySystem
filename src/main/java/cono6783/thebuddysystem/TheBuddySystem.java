package cono6783.thebuddysystem;


import com.mojang.logging.LogUtils;
import cono6783.thebuddysystem.entity.Buddy;
import cono6783.thebuddysystem.entity.BuddyRenderer;
import cono6783.thebuddysystem.registry.ModEntities;
import cono6783.thebuddysystem.registry.ModItems;
import cono6783.thebuddysystem.util.EntityFinder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import org.slf4j.Logger;


@Mod(TheBuddySystem.MODID)
public class TheBuddySystem {

    public static final String MODID = "thebuddysystem";
    public static final Logger LOGGER = LogUtils.getLogger();






    public TheBuddySystem() {


        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModEntities.ENTITY_DEFERRED_REGISTER.register(modEventBus);
        ModItems.ITEM_DEFERRED_REGISTER.register(modEventBus);


        LOGGER.info("Does this WORKY WORKY TIME");

        modEventBus.addListener(this::commonSetup);



        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(RegisterEvent event) {
        LOGGER.info("Hello from commonSetup");
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Hello from Server Starting");


    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        ClientLevel clientLevel = Minecraft.getInstance().level;
        LOGGER.info("Chat message sent");
        LOGGER.info("The message was " + event.getMessage());
        Buddy[] buddysAroundPlayer = EntityFinder.findBuddysAroundPlayer(clientLevel.getPlayerByUUID(event.getSender()), clientLevel);
        for (Buddy buddy : buddysAroundPlayer) {
            buddy.buddyChattedWith(event.getMessage().getString().split("> ", 2)[1]);
        }

    }





    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.Buddy.get(), BuddyRenderer::new);
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventSubscriber {
        @SubscribeEvent
        public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
            event.put(ModEntities.Buddy.get(), Buddy.createAttributes().build());
        }
    }










}
