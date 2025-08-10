package com.yakasov.hard_plus.mixin.block;

import net.minecraft.block.NetherPortalBlock;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(NetherPortalBlock.class)
public class NetherPortalBlockMixin {
    @Redirect(
            method = "randomTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/Difficulty;getId()I"
            )
    )
    private int doublePiglinChance(Difficulty instance) {
        return instance.getId() * 2;
    }
}
