package com.gfaraujosousa.tconstruct;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ENABLE_EXPERIMENTAL_TABLES = BUILDER
            .comment("Enables early community-port table blocks. Their current behavior is recipe/data driven, not GUI driven yet.")
            .define("enableExperimentalTables", true);

    public static final ModConfigSpec.BooleanValue ENABLE_EXPERIMENTAL_CASTING = BUILDER
            .comment("Enables early community-port casting and melting recipe serializers.")
            .define("enableExperimentalCasting", true);

    public static final ModConfigSpec SPEC = BUILDER.build();

    private Config() {
    }
}
