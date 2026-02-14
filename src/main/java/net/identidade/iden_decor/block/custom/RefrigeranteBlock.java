package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.templates.SimpleHorizontalBlock;
import net.identidade.iden_decor.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RefrigeranteBlock extends SimpleHorizontalBlock {

    public RefrigeranteBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Block.box(6, 0, 6, 10, 13, 10);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
       if (!level.isClientSide) {
           if (!stack.is(ModItems.PLASTIC_CUP)) {
               return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
           }

           if (stack.getCount() == 1) {
               player.setItemInHand(InteractionHand.MAIN_HAND, ModItems.GUARANA_CUP.toStack());
               return ItemInteractionResult.SUCCESS;
           }

           stack.shrink(1);
           player.addItem(ModItems.GUARANA_CUP.toStack());
           return ItemInteractionResult.SUCCESS;
       }

       return (stack.is(ModItems.PLASTIC_CUP) ? ItemInteractionResult.SUCCESS : ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION);
    }
}
