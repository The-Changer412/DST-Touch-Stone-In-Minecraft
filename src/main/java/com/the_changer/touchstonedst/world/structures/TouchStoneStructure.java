package com.the_changer.touchstonedst.world.structures;

import com.mojang.serialization.Codec;
import net.minecraft.structure.StructureGeneratorFactory;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.JigsawFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.Optional;
import java.util.function.Predicate;

public class TouchStoneStructure extends JigsawFeature {

    public TouchStoneStructure(Codec<StructurePoolFeatureConfig> codec, int structureStartY, boolean modifyBoundingBox, boolean surface, Predicate<StructureGeneratorFactory.Context<StructurePoolFeatureConfig>> contextPredicate) {
        super(codec, structureStartY, modifyBoundingBox, surface, contextPredicate);
    }

    public static boolean canGenerate(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context)
    {
        context.chunkGenerator().getMultiNoiseSampler();
        RegistryEntry<Biome> biome = context.biomeSource().getBiome(context.chunkPos().x, 64, context.chunkPos().z,
                context.chunkGenerator().getMultiNoiseSampler());

        return true;
    }

    @Override
    public GenerationStep.Feature getGenerationStep() {
        return GenerationStep.Feature.SURFACE_STRUCTURES;
    }
}
