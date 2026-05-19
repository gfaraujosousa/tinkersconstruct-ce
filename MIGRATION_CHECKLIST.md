# Migration Checklist

Tinkers' Construct Community Edition is an unofficial community port maintained by gfaraujosousa. Original Tinkers' Construct and Mantle are by SlimeKnights and contributors.

Status keys: `[ ]` not started, `[~]` partial, `[x]` completed for the current milestone scope, `[!]` blocked or needs explicit decision.

## Repository And Identity

- [x] Keep CE mod id as `tconstruct_ce`.
- [x] Preserve unofficial community fork disclaimer in README and mod metadata.
- [x] Preserve original attribution to SlimeKnights and contributors.
- [~] Add port maintainer metadata for `gfaraujosousa` consistently across README, mod metadata, changelog, and release notes.
- [x] Ignore reverse-engineering scratch outputs with `.gitignore`.
- [ ] Preserve upstream MIT license/credits when importing concrete resources/assets/data.

## Milestone 1: Upstream Inventory And Parity Map

- [x] Generate TConstruct jar listing.
- [x] Generate Mantle jar listing.
- [x] Extract selected upstream resources into ignored scratch folders.
- [x] Catalog data/assets/classes by category.
- [x] Identify tool definitions, materials, modifiers, station layouts, recipes, tags, worldgen, books, lang keys, models, textures, and Mantle dependencies.
- [x] Create `REVERSE_ENGINEERING_NOTES.md`.
- [x] Create `FEATURE_PARITY_MATRIX.md`.

## Milestone 2: Resource/Data Import Strategy

- [ ] Decide per resource type whether to import directly, transform, or regenerate.
- [ ] Implement namespace rewrite strategy from `tconstruct:` to `tconstruct_ce:` for CE-owned registries.
- [ ] Preserve vanilla ids and common tags during rewrites.
- [ ] Handle Forge 1.20.1 data such as biome modifiers with NeoForge 1.21.1 equivalents.
- [ ] Import or generate a first safe slice of upstream lang/models/material data.
- [ ] Run `.\gradlew.bat runData`.
- [ ] Run `.\gradlew.bat build`.

## Milestone 3: Mantle CE Required Systems

- [ ] Define internal Mantle CE package/module boundary.
- [ ] Port reusable registry helpers where they reduce CE duplication.
- [ ] Port inventory/menu sync helpers needed by stations and smeltery.
- [ ] Port JSON/resource reload helpers required by materials/modifiers/tool definitions.
- [ ] Port fluid helpers needed by tanks and tooltips.
- [ ] Decide book system strategy and document unported pieces.
- [ ] Ensure no client classes load on dedicated server.

## Milestone 4: Registry Parity

- [ ] Blocks.
- [ ] Items.
- [ ] Fluids and buckets.
- [ ] Block entities.
- [ ] Menu types.
- [ ] Entity types.
- [ ] Recipe serializers/types.
- [ ] Data components.
- [ ] Particles/sounds.
- [ ] Creative tabs and ordering.
- [ ] Server startup smoke test.

## Milestone 5: Real Material System

- [ ] Parse upstream material definition JSONs.
- [ ] Parse upstream material stat JSONs.
- [ ] Parse upstream material trait JSONs.
- [ ] Implement stat types and validation.
- [ ] Implement material traits as functional modifier entries.
- [ ] Add material reload tests.
- [ ] Load at least 10 upstream materials in CE.

## Milestone 6: Real Modifier System

- [ ] Parse upstream modifier JSONs.
- [ ] Implement slot categories and accounting.
- [ ] Implement requirements.
- [ ] Implement core hooks for stats, mining, combat, durability, tick, tooltips.
- [ ] Port `haste`, `diamond`, `emerald`, `reinforced`, `sharpness`, `fortune`, `silky`, and `netherite` first.
- [ ] Add Game Tests for several modifiers.

## Milestone 7: Tool Stack And Stat Engine

- [ ] Replace simplified `ToolStackData` with full component-backed data.
- [ ] Store tool definition id, part materials, modifiers, persistent data, damage, broken state, repair data, and slot counts.
- [ ] Implement lazy stat cache invalidation.
- [ ] Implement serialization and migration from current CE placeholders.
- [ ] Add serialization/stat/broken behavior tests.

## Milestone 8: Tool Definitions, Parts, And Tool Items

- [ ] Load 44 upstream tool definitions.
- [ ] Register real part item set.
- [ ] Register harvest tools.
- [ ] Register melee tools.
- [ ] Register ranged tools.
- [ ] Register armor/shields if feasible.
- [ ] Implement repair and tooltips.

## Milestone 9: Stations, Menus, And Screens

- [ ] Part Builder block entity/menu/screen/logic.
- [ ] Tinker Station block entity/menu/screen/logic.
- [ ] Tinkers' Anvil block entity/menu/screen/logic.
- [ ] Modifier Worktable block entity/menu/screen/logic.
- [ ] Crafting Station if retained.
- [ ] Server-authoritative previews and mutation.
- [ ] Client sync and validation messages.

## Milestone 10: Melting, Fluids, And Casting

- [ ] Register real molten fluids and buckets.
- [ ] Implement fluid storage helpers.
- [ ] Parse upstream melting/casting/alloying recipes.
- [ ] Implement casts and consumption rules.
- [ ] Implement casting table/basin behavior and persistence.
- [ ] Add Game Tests for melting, table casting, basin casting, and cast consumption.

## Milestone 11: Melter And Fuel Systems

- [ ] Melter block entity.
- [ ] Heater/fuel tank/seared tank behavior.
- [ ] Temperature and fuel handling.
- [ ] GUI/menu.
- [ ] Automation sides and persistence.

## Milestone 12: Smeltery And Foundry

- [ ] Cached multiblock validation.
- [ ] Controller block entities.
- [ ] Tanks, drains, ducts, channels, faucets.
- [ ] Item melting.
- [ ] Alloy ticks.
- [ ] Persistence and chunk reload.
- [ ] Valid/invalid structure Game Tests.

## Milestone 13: Slime Content And World

- [ ] Slime blocks/fluids/crystals/trees.
- [ ] Cobalt ore/content.
- [ ] NeoForge 1.21.1 worldgen data.
- [ ] Entities after blocks/tools stabilize.

## Milestone 14: Books And Progression

- [ ] Mantle CE book rendering/logic.
- [ ] Book items and recipes.
- [ ] Import book assets/data with attribution.
- [ ] Advancements and progression unlocks.

## Milestone 15: Client Polish

- [ ] Item/block models and blockstates.
- [ ] Textures.
- [ ] Fluid rendering.
- [ ] Station screens.
- [ ] Dynamic tool part models.
- [ ] Tooltips and expanded info.
- [ ] Dedicated server classloading check.

## Milestone 16: Recipes, Tags, Loot, Datagen

- [ ] Import/port recipes.
- [ ] Import/port tags.
- [ ] Import/port loot tables.
- [ ] Datagen providers for CE-owned generated data.
- [ ] Survival progression verification.

## Milestone 17: Optional Integrations

- [ ] JEI/EMI/REI strategy.
- [ ] Optional dependency guards.
- [ ] Recipe categories after core recipes stabilize.

## Milestone 18: Testing And Hardening

- [ ] `.\gradlew.bat clean build`
- [ ] `.\gradlew.bat compileJava`
- [ ] `.\gradlew.bat runData`
- [ ] `.\gradlew.bat runServer`
- [ ] `.\gradlew.bat runGameTestServer`
- [ ] Dedicated server client-classloading smoke check.
- [ ] Client smoke check.

## CI/CD Checklist

- [x] Add CI workflow for build, datagen, game tests, and server smoke.
- [x] Add release workflow for semantic version tags and manual dispatch.
- [x] Add nightly workflow for unstable artifacts.
- [x] Add Dependabot configuration for GitHub Actions and Gradle.
- [x] Add `CHANGELOG.md`.
- [x] Add `RELEASE_CHECKLIST.md`.
- [ ] Validate workflows on GitHub after repository remote is known.
- [ ] Add README badges after repository URL is known.
