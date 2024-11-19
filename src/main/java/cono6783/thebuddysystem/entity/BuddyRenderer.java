package cono6783.thebuddysystem.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import cono6783.thebuddysystem.TheBuddySystem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class BuddyRenderer extends MobRenderer<Buddy, HumanoidModel<Buddy>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(TheBuddySystem.MODID, "textures/entity/buddy.png");

    public  BuddyRenderer(final EntityRendererProvider.Context context) {
        super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
        super.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
        super.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
    }

    @Override
    public ResourceLocation getTextureLocation(Buddy entity) {
        return TEXTURE;
    }


    @Override
    public void render(
            @NotNull Buddy buddy,
            final float limbSwing,
            final float partialTicks,
            @NotNull final PoseStack matrixStack,
            @NotNull final MultiBufferSource renderTypeBuffer,
            final int light) {

        final HumanoidModel<Buddy> buddyModel = model;

        if (!buddy.getMainHandItem().isEmpty()) {
            buddyModel.rightArmPose = HumanoidModel.ArmPose.ITEM;
        }









        super.render(buddy, limbSwing, partialTicks, matrixStack, renderTypeBuffer, light);
    }
}
