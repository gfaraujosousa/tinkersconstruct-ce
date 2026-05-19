# Feature Parity Matrix

Tinkers' Construct Community Edition is an unofficial community port maintained by gfaraujosousa. Original Tinkers' Construct and Mantle are by SlimeKnights and contributors.

Statuses: `Not Started`, `Stub`, `Partial`, `Functional`, `Parity Candidate`, `Blocked`.

| Category | Upstream Feature | Upstream Source Evidence | Current CE Status | Implementation Files | Tests | Notes | Priority |
| --- | --- | --- | --- | --- | --- | --- | --- |
| Tool Data | Component-backed tool stack | `ToolStack.class`, `ToolDefinition.class`, `data/tconstruct/tinkering/tool_definitions/*.json` | Partial | `src/main/java/com/gfaraujosousa/tconstruct/tools/ToolStackData.java` | `tool_receives_modifier`, `broken_tool_keeps_stack` | Current data is simplified and only supports early materials/modifiers. | P0 Core Identity |
| Materials | Real material data loading | 91 definitions, 86 stats, 86 traits under `tinkering/materials` | Partial | `MaterialDefinition`, `ToolDataReloadListener` | `datapack_tool_data_loaded` | Current codec is CE-specific and does not parse upstream format yet. | P0 Core Identity |
| Modifiers | Real modifier data loading | 222 JSONs under `tinkering/modifiers`, `Modifier.class` | Partial | `ModifierDefinition`, `ToolDataReloadListener` | `tool_receives_modifier` | Current system models basic stat bonuses only. | P0 Core Identity |
| Tool Definitions | Datapack-loaded tool definitions | 44 JSONs under `tinkering/tool_definitions` | Stub | `ToolData` built-ins | None | Pickaxe is hardcoded. Must load module-style upstream definitions. | P0 Core Identity |
| Part System | Tool parts with material identity | Upstream part item ids, `item_part_builder` recipes, station layouts | Stub | `TCItems`, crafting JSONs | None | Current flint parts are fixed items, not materialized parts. | P0 Core Identity |
| Tinker Station | Tool assembly flow | Station layout `tinker_station`, `tool_building` recipes, table classes | Stub | `TCBlocks` only | None | Block exists without menu/screen/server logic. | P0 Core Identity |
| Repair | Material repair flow | Material repair modules, repair recipes, modifier hooks | Not Started | None | None | Needs part/material repair values and tool stack mutation. | P0 Core Identity |
| Modifier Application | Station modifier application | `modifier`, `incremental_modifier`, `swappable_modifier`, `overslime_modifier` recipes | Partial | special crafting recipe | `tool_receives_modifier` | Current redstone shortcut must move into station/worktable flow. | P0 Core Identity |
| Broken Tools | Broken but not destroyed behavior | TConstruct tool identity and CE requirement | Functional | `ModifiableToolItem`, `ToolStackData` | `broken_tool_keeps_stack` | Needs integration with full stat engine and repair. | P0 Core Identity |
| Melting/Casting Model | Real recipe model | 498 melting, 435 table casting, 158 basin casting recipes | Partial | `ItemFluidResultRecipe` | None | Serializers exist but do not use real fluids/tanks. | P0 Core Identity |
| Molten Fluids | Real fluids and buckets | 84 fluid tags, `TinkerFluids.class`, 205 fluid lang keys | Not Started | None | None | Required before importing upstream casting recipes. | P0 Core Identity |
| Casting Table/Basin | Casting behavior | casting table/basin recipes, blocks/assets/classes | Stub | `TCBlocks` only | None | Needs block entities, tanks, cooling, cast consumption. | P0 Core Identity |
| Melter/Smeltery MVP | Early molten workflow | melter/smeltery classes, book data, recipes | Stub | `TCBlocks` only | None | Melter block exists without storage or recipe ticks. | P0 Core Identity |
| Part Builder | Pattern-to-part workflow | 116 `item_part_builder`, 21 `part_builder` recipes | Stub | `TCBlocks`, recipe JSONs | None | Current crafting recipes bypass station workflow. | P1 Required Gameplay |
| Tinkers' Anvil | Advanced tool assembly | `tinkers_anvil` station layout, block/assets/classes | Not Started | None | None | Needed for advanced tools and part replacement. | P1 Required Gameplay |
| Modifier Worktable | Modifier/slot workflow | Upstream worktable block/classes/recipes | Stub | `TCBlocks` only | None | Block exists without menu logic. | P1 Required Gameplay |
| Crafting Station | TConstruct crafting station | block/lang/model/classes | Not Started | None | None | Needs Mantle-like inventory/menu support. | P1 Required Gameplay |
| Pattern System | Blank pattern and variants | pattern items, recipes, books | Stub | `TCItems` | None | Blank pattern exists, but no station pattern state. | P1 Required Gameplay |
| Cast System | Gold/sand/red-sand casts | cast item ids, casting/molding recipes | Stub | `gold_pick_head_cast` only | None | Need all tool part casts and consume rules. | P1 Required Gameplay |
| Tool Parts | Full part set | part item ids and cast ids in lang/models | Stub | `TCItems` | None | Need pick head, blades, heads, plates, bindings, handles, bow/armor parts. | P1 Required Gameplay |
| Basic Tools | Pickaxe/sword/axe/etc. | 44 tool definitions | Stub | `flint_pickaxe` | Basic break test | Only one simple pickaxe exists. | P1 Required Gameplay |
| Harvest Tools | Pickaxe, hammer, excavator, mattock, axe variants | tool definitions and station layouts | Stub | `flint_pickaxe` | Basic break test | AoE and harvest tiers not ported. | P1 Required Gameplay |
| Weapon Tools | Dagger, sword, cleaver, kama/scythe | tool definitions | Not Started | None | None | Needs attack hooks/stat engine. | P1 Required Gameplay |
| Ranged Tools | Bows, crossbow, javelin, shuriken, arrow | tool definitions | Not Started | None | None | Needs projectile stats and ammo handling. | P1 Required Gameplay |
| Armor | Plate/slime/travelers armor | armor tool definitions and part ids | Not Started | None | None | Needs armor components/stats/modifier hooks. | P1 Required Gameplay |
| Material Traits | Traits from materials | 86 `materials/traits` JSONs | Stub | `MaterialDefinition` | None | Current traits are strings only. | P1 Required Gameplay |
| Slot Logic | Upgrade/ability/defense/trait slots | modifier tags, tool modules, modifier slots modules | Stub | `ToolStackData` | None | Needs slot accounting and validation. | P1 Required Gameplay |
| Modifier Requirements | Requirements and validation | `ModifierRequirementsModule`, modifier recipes | Not Started | None | None | Needed before real recipes are imported. | P1 Required Gameplay |
| Tooltips | Material/modifier/stat display | lang keys, modifier display methods, client classes | Partial | `ModifiableToolItem` | None | Current tooltip is minimal. | P1 Required Gameplay |
| Tool Stats | Stat calculation engine | material stats, tool definition modules, stat modules | Partial | `ToolStackData` | Basic GameTests | Needs module/hook model and cache invalidation. | P1 Required Gameplay |
| Repair Kits | Repair kits/material repair | recipe/classes/lang evidence | Not Started | None | None | Needs material values and station support. | P1 Required Gameplay |
| Heater/Fuel Tank | Early heat/fuel blocks | smeltery classes/assets/recipes | Not Started | None | None | Needed for melter progression. | P1 Required Gameplay |
| Faucets/Channels/Drains | Fluid transfer network | block ids/classes/models | Not Started | None | None | Needed for casting pipeline. | P1 Required Gameplay |
| Smeltery Controller | Multiblock controller | controller ids/classes/book pages | Not Started | None | None | Needs cached validation and persistence. | P1 Required Gameplay |
| Seared/Scorched Blocks | Smeltery structure blocks | blockstates/models/loot/lang | Stub | `seared_bricks` | None | Only one block is registered. | P1 Required Gameplay |
| Full Materials | 91 material definitions | `materials/definition/*.json` | Partial | `ToolDataReloadListener` | Basic load test | Need upstream codec compatibility. | P2 Major Content |
| Full Modifiers | 222 modifier definitions | `tinkering/modifiers/*.json` | Partial | `ModifierDefinition` | Redstone test | Need hooks/modules for actual effects. | P2 Major Content |
| Slime Content | slime blocks, fluids, trees, geodes | block/lang/worldgen/entities/classes | Not Started | None | None | Major content and world identity. | P2 Major Content |
| Cobalt | ore, cluster, block, materials | block/lang/worldgen/material JSONs | Not Started | None | None | Requires worldgen and materials. | P2 Major Content |
| Alloy Materials | manyullyn/hepatizon/queens slime/etc. | material JSONs and alloy recipes | Not Started | None | None | Requires real fluids/alloying. | P2 Major Content |
| Worldgen | ores, slime islands, geodes | `worldgen`, `structures`, Forge biome modifiers | Not Started | None | None | Requires NeoForge 1.21.1 worldgen migration. | P2 Major Content |
| Entities | slime mobs/projectiles | entity lang/loot/classes | Not Started | None | None | Port after core blocks/tools. | P2 Major Content |
| Guidebooks | Mantle/TConstruct books | 4680 book assets, Mantle book classes | Not Started | None | None | Requires Mantle CE book layer. | P2 Major Content |
| Advancements | progression triggers | 411 advancement JSONs | Not Started | None | None | Import after registry parity. | P2 Major Content |
| Loot Tables | block/entity drops | 342 loot tables | Not Started | None | None | Needs namespace migration and registry parity. | P2 Major Content |
| Creative Tabs | Sorted content groups | lang/assets/classes | Stub | `TCCreativeTabs` | None | Current tab has only foundation items. | P2 Major Content |
| Sounds/Particles | sounds and particles | `sounds.json`, particle assets/classes | Not Started | None | None | Client/server registry pass needed. | P2 Major Content |
| Models/Textures | core visuals | 435 blockstates, 1538 models, 12730 textures | Stub | current generated/simple assets | None | Must import/rewrite namespace carefully. | P2 Major Content |
| Datagen | Providers and generated data | upstream data provider classes | Partial | `src/main/java/.../data` | `runData` | Current providers are minimal. | P2 Major Content |
| JEI/EMI/REI | Recipe viewer integration | plugin package and mods.toml optional JEI dep | Not Started | None | None | Optional only, guard behind mod checks. | P3 Polish/Compat |
| Config Parity | Gameplay/client/server config | config classes | Partial | `TConstructCEConfig` | None | Current config is minimal. | P3 Polish/Compat |
| Pack Maker Docs | Datapack docs | upstream data-driven formats | Not Started | None | None | Add after codecs stabilize. | P3 Polish/Compat |
| Addon API Docs | CE extension points | upstream library architecture | Not Started | None | None | Needs stable internal API. | P3 Polish/Compat |
| Placeholder Migration | Early CE item/component migration | current CE save compatibility | Not Started | None | None | Needed before replacing current component format. | P3 Polish/Compat |
| Translation Completeness | 19 lang files upstream | `assets/tconstruct/lang` | Not Started | None | None | Preserve attribution when importing. | P3 Polish/Compat |
| CI Validation | Build/datagen/game/server workflows | project requirement | Functional | `.github/workflows/ci.yml` | GitHub Actions only | Added in CE repo, pending remote run. | P4 Optional |
| Release Automation | Tagged GitHub releases | project requirement | Functional | `.github/workflows/release.yml` | GitHub Actions only | No Modrinth/CurseForge publishing. | P4 Optional |
