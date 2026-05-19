# Reverse Engineering Notes

Tinkers' Construct Community Edition is an unofficial community port maintained by gfaraujosousa. Original Tinkers' Construct and Mantle are by SlimeKnights and contributors.

This file records reproducible findings from the local MIT-licensed upstream jars in `original_sources/`. Raw extracted resources and jar listings are generated under ignored `reverse_engineering/` scratch directories and must not be committed accidentally.

## Inspection Inputs

- TConstruct reference: `original_sources/TConstruct-1.20.1-3.11.2.166.jar`
- Mantle reference: `original_sources/Mantle-1.20.1-1.11.104.jar`
- Target port: Minecraft `1.21.1`, NeoForge `21.1.230`, Java `21`, mod id `tconstruct_ce`
- Jar tool used: `C:\Program Files\Java\jdk-20\bin\jar.exe`
- Bytecode inspection tool available: `C:\Program Files\Java\jdk-20\bin\javap.exe`
- Java decompiler status: no CFR/Vineflower/FernFlower/Quiltflower command was found on PATH during this pass, so the current behavioral map uses resources, class names, metadata, and selected `javap` signatures.

## Generated Scratch Outputs

- `reverse_engineering/tconstruct_jar_listing.txt`
- `reverse_engineering/mantle_jar_listing.txt`
- `reverse_engineering/tconstruct_resources/`
- `reverse_engineering/mantle_resources/`
- `reverse_engineering/decompiled/` reserved for a decompiler if one is added later

These paths are intentionally ignored by `.gitignore`.

## TConstruct Jar Inventory

- Total jar entries: `29731`
- Java classes: `2400`
- Extracted data resources: `5378`
- Extracted asset resources: `19710`
- Metadata/root resources: `META-INF/mods.toml`, `META-INF/accesstransformer.cfg`, `pack.mcmeta`, logo/manifest resources

Major class packages by count:

| Package Area | Class Count | Notes |
| --- | ---: | --- |
| `slimeknights/tconstruct/library` | 1341 | Tool, material, modifier, module, JSON, recipe, book, and client library systems |
| `slimeknights/tconstruct/tools` | 330 | Tool items, parts, stats, recipes, modifiers, client models |
| `slimeknights/tconstruct/smeltery` | 185 | Melting, casting, fluid, melter, smeltery/foundry logic |
| `slimeknights/tconstruct/tables` | 113 | Station blocks, menus, screens, crafting logic |
| `slimeknights/tconstruct/common` | 102 | Common bootstrap, tags, damage types, network |
| `slimeknights/tconstruct/shared` | 95 | Shared blocks/items/content |
| `slimeknights/tconstruct/world` | 80 | Slime content, entities, worldgen |
| `slimeknights/tconstruct/plugin` | 80 | Optional integrations |
| `slimeknights/tconstruct/gadgets` | 39 | Gadget items/content |
| `slimeknights/tconstruct/fluids` | 33 | Fluid registry and client support |

Important discovered classes/signatures:

- `slimeknights.tconstruct.TConstruct`
- `slimeknights.tconstruct.common.TinkerModule`
- `slimeknights.tconstruct.common.TinkerTags`
- `slimeknights.tconstruct.common.network.TinkerNetwork`
- `slimeknights.tconstruct.fluids.TinkerFluids`
- `slimeknights.tconstruct.library.materials.MaterialRegistry`
- `slimeknights.tconstruct.library.modifiers.Modifier`
- `slimeknights.tconstruct.library.tools.definition.ToolDefinition`
- `slimeknights.tconstruct.library.tools.nbt.ToolStack`
- `slimeknights.tconstruct.library.data.tinkering.AbstractToolDefinitionDataProvider`
- `slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider`
- `slimeknights.tconstruct.library.data.tinkering.AbstractMaterialTagProvider`

Selected `javap` evidence:

- `MaterialRegistry` exposes material reload/sync entry points for definitions, stats, and traits plus built-in stat groups such as melee/harvest, ranged, armor, and ammo.
- `ToolDefinition` is an id-aware object with datapack-loaded `ToolDefinitionData`, hooks, and material awareness.
- `Modifier` is id-aware, owns module hooks, display metadata, color/text style, and helper methods for held tool access and mining modifiers.

## TConstruct Data Resources

Top-level `data/tconstruct` resource counts:

| Path | Count | Porting Meaning |
| --- | ---: | --- |
| `recipes` | 2950 | Core survival progression, tools, tables, smeltery, casting, modifiers |
| `tinkering` | 708 | Materials, modifiers, station layouts, tool definitions, fluid effects |
| `tags` | 449 | Blocks, items, fluids, entities, menus, damage types, worldgen |
| `advancements` | 411 | Progression and guide unlock cues |
| `loot_tables` | 342 | Blocks, entities, gameplay drops |
| `worldgen` | 31 | Cobalt, geodes, slime islands, configured/placed features |
| `structures` | 25 | Slime island and related structure data |
| `loot_modifiers` | 20 | Modifier hooks and lustrous drops |
| `damage_type` | 28 | Custom damage types |
| `forge` | 8 | 1.20.1 Forge biome modifiers |
| `mantle` | 36 | Mantle data hooks used by TConstruct |

Recipe type counts from upstream JSON:

| Recipe Type | Count |
| --- | ---: |
| `tconstruct:melting` | 498 |
| `tconstruct:casting_table` | 435 |
| `minecraft:crafting_shaped` | 236 |
| `tconstruct:material` | 189 |
| `tconstruct:damagable_melting` | 187 |
| `tconstruct:casting_basin` | 158 |
| `tconstruct:modifier_salvage` | 130 |
| `tconstruct:item_part_builder` | 116 |
| `tconstruct:modifier` | 114 |
| `tconstruct:ore_melting` | 94 |
| `forge:conditional` | 66 |
| `minecraft:crafting_shapeless` | 62 |
| `tconstruct:material_fluid` | 61 |
| `tconstruct:molding_table` | 58 |
| `tconstruct:incremental_modifier` | 46 |
| `tconstruct:table_casting_material` | 42 |
| `tconstruct:material_melting` | 39 |
| `tconstruct:swappable_modifier` | 37 |
| `tconstruct:part_builder_recycling` | 37 |
| `tconstruct:entity_melting` | 32 |
| `tconstruct:severing` | 30 |
| `tconstruct:tool_building` | 25 |
| `tconstruct:table_casting_composite` | 23 |
| `tconstruct:part_builder` | 21 |
| `tconstruct:alloy` | 21 |

## Tinkering Data

`data/tconstruct/tinkering` contains:

| Path | Count |
| --- | ---: |
| `materials/definition` | 91 |
| `materials/stats` | 86 |
| `materials/traits` | 86 |
| `modifiers` | 222 |
| `tool_definitions` | 44 |
| `station_layouts` | 23 |
| `fluid_effects` | 84 |
| `tags` | 65 |
| `mob_equipment` | 6 |
| `enchantments_to_modifiers.json` | 1 |

Representative material ids:

`aluminum`, `amethyst`, `amethyst_bronze`, `ancient`, `ancient_hide`, `bamboo`, `blaze`, `blazewood`, `blazing_bone`, `blood`, `bone`, `bronze`, `cactus`, `chain`, `cinderslime`, `clay`, `cobalt`, `constantan`, `copper`, `dragon_scale`, `earthslime`, `electrum`, `enderslime`, `feather`, `fiery`, `flint`, `glass`, `gold`, `hepatizon`, `ichor`, `iron`, `knightmetal`, `manyullyn`, `nahuatl`, `obsidian`, `paper`, `pig_iron`, `queens_slime`, `rose_gold`, `seared_stone`, `slimesteel`, `steel`, `wood`, `wool`.

Tool definition ids:

`arrow`, `battlesign`, `broad_axe`, `cleaver`, `crossbow`, `dagger`, `earth_staff`, `ender_staff`, `excavator`, `fishing_rod`, `flint_and_brick`, `hand_axe`, `ichor_staff`, `javelin`, `kama`, `longbow`, `mattock`, `melting_pan`, `minotaur_axe`, `pickadze`, `pickaxe`, `plate_boots`, `plate_chestplate`, `plate_helmet`, `plate_leggings`, `plate_shield`, `scythe`, `shuriken`, `sky_staff`, `sledge_hammer`, `slime_boots`, `slime_chestplate`, `slime_helmet`, `slime_leggings`, `swasher`, `sword`, `throwing_axe`, `travelers_boots`, `travelers_chestplate`, `travelers_helmet`, `travelers_leggings`, `travelers_shield`, `vein_hammer`, `war_pick`.

Station layout ids:

`arrow`, `broad_axe`, `cleaver`, `crossbow`, `dagger`, `excavator`, `fishing_rod`, `hand_axe`, `javelin`, `kama`, `longbow`, `mattock`, `pickadze`, `pickaxe`, `plate_armor`, `scorched_anvil`, `scythe`, `sledge_hammer`, `sword`, `thrown_ammo`, `tinker_station`, `tinkers_anvil`, `vein_hammer`.

Representative modifier ids:

`haste`, `diamond`, `emerald`, `reinforced`, `sharpness`, `fortune`, `silky`, `luck`, `netherite`, `writable`, `recapitated`, `expanded`, `overslime_friend`, `overforced`, `draconic`, `gilded`, `harmonious`, `reach`, `knockback`, `unbreakable`, `protection`, `fire_protection`, `projectile_protection`, `feather_falling`, `swift_sneak`, `double_jump`, `step_up`, `pockets`, `tool_belt`, `tank`, `worldbound`, `soulbound`.

## TConstruct Assets

Top-level `assets/tconstruct` resource counts:

| Path | Count |
| --- | ---: |
| `textures` | 12730 |
| `book` | 4680 |
| `models` | 1538 |
| `blockstates` | 435 |
| `mantle` | 142 |
| `lang` | 19 |
| `sounds` | 16 |
| `tinkering` | 138 |
| `particles` | 7 |
| `sounds.json` | 1 |
| `Credits.txt` | 1 |

Language key counts from `assets/tconstruct/lang/en_us.json`:

| Prefix | Count |
| --- | ---: |
| `block.tconstruct.` | 553 |
| `item.tconstruct.` | 421 |
| `entity.tconstruct.` | 15 |
| `fluid.tconstruct.` | 205 |
| `modifier.tconstruct.` | 1013 |
| `material.tconstruct.` | 407 |

Representative block ids:

`part_builder`, `tinker_station`, `modifier_worktable`, `tinkers_anvil`, `scorched_anvil`, `crafting_station`, `cast_chest`, `seared_bricks`, `scorched_bricks`, `melter`, `heater`, `seared_fuel_tank`, `smeltery_controller`, `foundry_controller`, `seared_drain`, `seared_duct`, `seared_chute`, `faucet`, `casting_table`, `casting_basin`, `channel`, `cobalt_ore`, `cobalt_block`, `sky_slime`, `earth_congealed_slime`, `ender_congealed_slime`, `ichor_congealed_slime`, `slime_*_grass`, `slime_*_sapling`, `clear_glass`, `gold_platform`, `copper_platform`.

Representative item ids:

`blank_pattern`, `pick_head`, `tool_handle`, `tool_binding`, `broad_blade`, `broad_axe_head`, `hammer_head`, `large_plate`, `tough_handle`, `tough_binding`, `bow_limb`, `bow_grip`, `bowstring`, `helmet_plating`, `chestplate_plating`, `leggings_plating`, `boots_plating`, `maille`, `pickaxe`, `sledge_hammer`, `mattock`, `excavator`, `hand_axe`, `broad_axe`, `dagger`, `sword`, `cleaver`, `longbow`, `crossbow`, `javelin`, `shuriken`, `ingot_cast`, `nugget_cast`, `gem_cast`, `gear_cast`, `rod_cast`, `blank_cast`, `gold_reinforcement`, `cobalt_reinforcement`, `materials_and_you`, `puny_smelting`, `mighty_smelting`, `fantastic_foundry`, `encyclopedia`.

Books under `assets/tconstruct/book`:

| Book | File Count |
| --- | ---: |
| `materials_and_you` | 320 |
| `puny_smelting` | 1135 |
| `mighty_smelting` | 905 |
| `fantastic_foundry` | 315 |
| `tinkers_gadgetry` | 269 |
| `encyclopedia` | 1665 |
| shared `images` | 69 |
| shared `structures` | 2 |

## Fluids

Evidence:

- `data/tconstruct/tags/fluids` contains 84 fluid tag files.
- `assets/tconstruct/lang/en_us.json` contains 205 `fluid.tconstruct.*` language keys.
- `slimeknights/tconstruct/fluids/TinkerFluids.class` exists.
- Recipe data references molten fluids heavily through melting, casting, material fluid, alloy, and basin/table filling recipes.

Port implication:

- Current CE recipe serializers that output representative items are temporary. Milestone 10 must add real NeoForge fluid registrations, bucket items, tank storage, transfer, and rendering before upstream casting/melting recipes can be imported safely.

## Smeltery, Melter, And Casting

Evidence:

- `slimeknights/tconstruct/smeltery` has 185 classes.
- Recipe counts include 498 melting, 435 casting table, 158 casting basin, 94 ore melting, 39 material melting, 32 entity melting, and 21 alloy recipes.
- Upstream assets/data include seared/scorched blocks, controllers, drains, ducts, channels, faucets, tanks, casting tables, casting basins, melter/heater/fuel tank content, and book pages for smelting.

Port implication:

- Smeltery/foundry cannot be represented by registration-only blocks. A real port needs block entities, fluid inventories, recipe matching, cached multiblock validation, faucet/channel transfer, alloy ticks, persistence, and station/client UI.

## Worldgen And Slime Content

Evidence:

- `data/tconstruct/worldgen` contains 15 configured features, 6 placed features, 6 structures, and 4 structure sets.
- `data/tconstruct/forge/biome_modifier` contains cobalt ore, earth/sky/ichor/ender geode, and slime spawn modifiers.
- World classes include slime entities, slime tree features, island structures, island placement, and slime grass seed behavior.

Port implication:

- Forge 1.20.1 biome modifiers need a NeoForge 1.21.1-compatible worldgen pass before direct resource import. Structure and feature codecs must be ported or mapped to vanilla/NeoForge datapack equivalents.

## Mantle Jar Inventory

- Total jar entries: `1004`
- Java classes: `783`
- Extracted data resources: `25`
- Extracted asset resources: `74`
- Metadata/root resources: `META-INF/mods.toml`, `META-INF/accesstransformer.cfg`, `pack.mcmeta`, `Mantle.png`

Major class packages by count:

| Package Area | Class Count | Notes |
| --- | ---: | --- |
| `slimeknights/mantle/data` | 226 | Data providers, loadables, generic resource helpers |
| `slimeknights/mantle/client` | 179 | Book UI, screens, models, render helpers |
| `slimeknights/mantle/recipe` | 92 | Recipe serializers, conditions, helper recipes |
| `slimeknights/mantle/registration` | 45 | Deferred registration object wrappers |
| `slimeknights/mantle/util` | 42 | JSON, tags, translation, sync, typed map helpers |
| `slimeknights/mantle/fluid` | 39 | Fluid transfer, tooltips, model support |
| `slimeknights/mantle/command` | 38 | Debug/data commands |
| `slimeknights/mantle/loot` | 32 | Loot conditions and modifiers |
| `slimeknights/mantle/block` | 22 | Inventory and entity block patterns |
| `slimeknights/mantle/item` | 15 | Book and item helpers |
| `slimeknights/mantle/network` | 15 | Forge-era packet wrappers |
| `slimeknights/mantle/inventory` | 11 | Container/inventory abstractions |

Important Mantle systems TConstruct depends on:

- Registration object wrappers: `SynchronizedDeferredRegister`, `ItemObject`, `FluidObject`, `FlowingFluidObject`, `MetalItemObject`, wood/building block objects.
- Data/load helpers: JSON helpers, loadables, recipe conditions, retextured providers.
- Inventory/menu sync: item stack lists, data slots, block entity helpers.
- Fluid helpers: tooltips, model data, transfer helpers.
- Book system: Mantle book item, book screens, book asset layout, structure/image/crafting/smelting pages.
- Networking helpers: Forge packet infrastructure that must be replaced by NeoForge 1.21.1 payload handlers if reused.

## Namespace Migration Findings

- Upstream resources are under `tconstruct:` and `mantle:`.
- CE runtime identity must remain `tconstruct_ce:`.
- Direct resource import must rewrite references carefully:
  - Internal upstream ids like `tconstruct:pickaxe`, `tconstruct:haste`, and `tconstruct:molten_gold` should map to `tconstruct_ce:*` for CE-owned registries.
  - Vanilla ids and common tag ids must remain unchanged.
  - Forge-specific data paths such as `data/tconstruct/forge/biome_modifier` require API migration, not a blind namespace rewrite.
  - Mantle book/model resource references must either be ported into a Mantle CE layer or rewritten to CE-owned equivalents.

## Current CE Gap Summary

- Current CE has functional foundation only: a few blocks/items, one material/tool path, one modifier path, early recipe serializers, and three Game Tests.
- Upstream evidence shows the real port needs hundreds of blocks/items, 84+ fluids/tags, 44 tool definitions, 91 material definitions, 222 modifiers, 23 station layouts, 2,950 recipes, and Mantle-like book/inventory/registration/data helpers.
- The next safe implementation milestone is resource import strategy plus a real data model upgrade for materials/tool definitions before attempting GUI or smeltery behavior.
