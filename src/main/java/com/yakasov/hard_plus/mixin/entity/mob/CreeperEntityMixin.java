package com.yakasov.hard_plus.mixin.entity.mob;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

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
}
