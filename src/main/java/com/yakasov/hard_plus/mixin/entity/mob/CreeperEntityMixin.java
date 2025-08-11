package com.yakasov.hard_plus.mixin.entity.mob;

import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.projectile.thrown.LingeringPotionEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public class CreeperEntityMixin extends HostileEntity {
    protected CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        CreeperEntity creeperEntity = (CreeperEntity)(Object)this;

        Random random = world.getRandom();
        if (random.nextInt(40) == 0) {
            creeperEntity.dataTracker.set(
                    CreeperEntity.CHARGED,
                    true
            );
        }
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Unique
    private ItemStack getRandomPotion(java.util.Random random) {
        int randomInt = random.nextInt(4);
        RegistryEntry<Potion> registryEntry = Potions.SLOWNESS;

        switch (randomInt) {
            case 0:
                registryEntry = Potions.POISON;
                break;
            case 1:
                registryEntry = Potions.WEAKNESS;
                break;
            case 2:
                registryEntry = Potions.HARMING;
                break;
            default:
                break;
        }

        return PotionContentsComponent.createStack(Items.SPLASH_POTION, registryEntry);
    }

    @Inject(
            method = "explode",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/mob/CreeperEntity;spawnEffectsCloud()V"
            )
    )
    private void spawnPotionEffectChance(CallbackInfo ci) {
        CreeperEntity creeperEntity = (CreeperEntity)(Object)this;
        java.util.Random random = new java.util.Random();

        World world = creeperEntity.getWorld();
        float localDifficulty = world.getLocalDifficulty(creeperEntity.getBlockPos()).getLocalDifficulty();

        if (random.nextInt(100) < 10 + localDifficulty * 2) {
            ItemStack potion = getRandomPotion(random);
            LingeringPotionEntity lingeringPotionEntity = new LingeringPotionEntity(
                    world,
                    creeperEntity.getX(),
                    creeperEntity.getY(),
                    creeperEntity.getZ(),
                    potion
            );

            lingeringPotionEntity.spawnAreaEffectCloud(
                    (ServerWorld) creeperEntity.getWorld(),
                    potion,
                    creeperEntity.raycast(0, 0, false)
            );
        }
    }
}
