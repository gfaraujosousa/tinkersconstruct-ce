package com.gfaraujosousa.tconstruct.registry;

import com.gfaraujosousa.tconstruct.TinkersConstructCommunityEdition;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class TCBlocks {
    public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(TinkersConstructCommunityEdition.MODID);

    public static final DeferredBlock<Block> PART_BUILDER = REGISTRY.registerSimpleBlock("part_builder", tableProperties(MapColor.WOOD));
    public static final DeferredBlock<Block> TINKER_STATION = REGISTRY.registerSimpleBlock("tinker_station", tableProperties(MapColor.WOOD));
    public static final DeferredBlock<Block> MODIFIER_WORKTABLE = REGISTRY.registerSimpleBlock("modifier_worktable", tableProperties(MapColor.WOOD));
    public static final DeferredBlock<Block> MELTER = REGISTRY.registerSimpleBlock("melter", stoneProperties(MapColor.STONE));
    public static final DeferredBlock<Block> CASTING_TABLE = REGISTRY.registerSimpleBlock("casting_table", stoneProperties(MapColor.TERRACOTTA_BROWN));
    public static final DeferredBlock<Block> SEARED_BRICKS = REGISTRY.registerSimpleBlock("seared_bricks", stoneProperties(MapColor.COLOR_GRAY));

    private static BlockBehaviour.Properties tableProperties(MapColor color) {
        return BlockBehaviour.Properties.of().mapColor(color).strength(2.0F, 3.0F).sound(SoundType.WOOD);
    }

    private static BlockBehaviour.Properties stoneProperties(MapColor color) {
        return BlockBehaviour.Properties.of().mapColor(color).strength(3.0F, 6.0F).sound(SoundType.STONE);
    }

    private TCBlocks() {
    }
}
