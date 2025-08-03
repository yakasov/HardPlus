package com.yakasov.hard_plus.mixin.structure;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.world.Difficulty;
import net.minecraft.world.StructureWorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(StrongholdGenerator.PortalRoom.class)
public class StrongholdGenerator$PortalRoomMixin {
    @ModifyConstant(
            method = "generate",
            constant = @Constant(
                    floatValue = 0.9F
            )
    )
    private float ensureNoEyesSpawn(float f, @Local(argsOnly = true) StructureWorldAccess world) {
        if (world.getDifficulty() == Difficulty.HARD) {
            f = 0.0F;
        }

        return f;
    }
}
