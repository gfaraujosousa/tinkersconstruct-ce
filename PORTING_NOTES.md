# Porting Notes

## Source And Target

- Source reference detected: compiled `original_sources/TConstruct-1.20.1-3.11.2.166.jar` and `original_sources/Mantle-1.20.1-1.11.104.jar`.
- Java source trees for upstream TConstruct/Mantle were not present in this workspace, so this pass uses the compiled jars as behavioral/content references and implements a focused NeoForge foundation in source.
- Target: Minecraft `1.21.1`, NeoForge `21.1.230`, Java `21` target from the template.
- Community mod id: `tconstruct_ce`.
- Community port maintainer: `gfaraujosousa`.
- Original authors: SlimeKnights and contributors.
- This is not an official SlimeKnights build.

## API Replacements Started

- ForgeGradle-era setup: kept the supplied NeoForge userdev template rather than importing old build scripts.
- Old registry events: replaced with NeoForge `DeferredRegister`.
- Old ItemStack NBT/share tag tool data: started as a typed `DataComponentType<ToolStackData>` with a codec-backed immutable record.
- Old recipe serializers: early custom recipe serializers use 1.21.1 `MapCodec` and `StreamCodec`.

## Reverse Engineering Inventory

- Generated ignored jar listings:
  - `reverse_engineering/tconstruct_jar_listing.txt`
  - `reverse_engineering/mantle_jar_listing.txt`
- Extracted selected upstream resources into ignored folders:
  - `reverse_engineering/tconstruct_resources/`
  - `reverse_engineering/mantle_resources/`
- TConstruct jar findings:
  - `29731` jar entries.
  - `2400` Java classes.
  - `5378` data resources.
  - `19710` asset resources.
  - `44` tool definition JSONs.
  - `91` material definition JSONs, `86` material stat JSONs, and `86` material trait JSONs.
  - `222` modifier JSONs.
  - `23` station layout JSONs.
  - `2950` recipe JSONs.
- Mantle jar findings:
  - `1004` jar entries.
  - `783` Java classes.
  - Main dependency areas: data/load helpers, recipe helpers, registration wrappers, inventory/menu sync, fluid helpers, networking wrappers, and book/client UI.
- Detailed inventory is tracked in `REVERSE_ENGINEERING_NOTES.md`.
- Feature status and priorities are tracked in `FEATURE_PARITY_MATRIX.md`.
- Migration execution checklist is tracked in `MIGRATION_CHECKLIST.md`.

## CI/CD And Release Automation

- Added GitHub Actions CI in `.github/workflows/ci.yml`.
  - Validates wrapper files.
  - Uses Temurin Java 21.
  - Runs `./gradlew --version`, `./gradlew clean build`, `./gradlew runData`, `./gradlew runGameTestServer`, and a timeout-bounded `./gradlew runServer` startup smoke test.
  - Uploads jars, generated resources, reports, and logs as workflow artifacts.
- Added `.github/workflows/release.yml`.
  - Publishes GitHub Releases from tags matching `v*.*.*` and `v*.*.*-*`, or manual dispatch.
  - Fails if the release tag/input version does not match `mod_version` in `gradle.properties`.
  - Attaches built jars, changelog, README, porting notes, and license template.
  - Does not publish to CurseForge, Modrinth, or Maven.
- Added `.github/workflows/nightly.yml` for unstable workflow artifacts only.
- Added `.github/dependabot.yml` for GitHub Actions and Gradle update review.
- Added `CHANGELOG.md` and `RELEASE_CHECKLIST.md`.
- Updated artifact base name to `tconstruct-ce-neoforge-1.21.1`.
- Changed current mod version to `0.1.0-alpha.1` to match the documented alpha porting phase.

## Implemented In This Pass

- Main mod bootstrap, config, mod metadata, and creative tab.
- Core blocks: part builder, tinker station, modifier worktable, melter, casting table, seared bricks.
- Core items: blank pattern, flint parts, flint pickaxe, redstone upgrade, gold pick head cast, molten gold ingot.
- Tool data model: material ids, modifier entries, tool stats, tool stack data component, broken-state durability behavior.
- Basic gameplay loop:
  - Craft flint parts from blank patterns and flint.
  - Craft a flint pickaxe from flint parts.
  - Apply redstone to a flint pickaxe through a special crafting recipe to add/increase the haste modifier.
  - Tools accumulate component damage and become broken instead of disappearing.
- Early casting/melting data model:
  - Custom melting/casting recipe serializers and example JSON recipes.

## Missing Or Disabled Features

- Full upstream Mantle API is not ported yet. No separate Mantle CE module exists because the workspace supplied compiled jars, not source.
- GUI/menu implementations for Tinker Station, Part Builder, Tinkers' Anvil, Modifier Worktable, Casting Table, and Melter are not ported yet.
- Real fluid registration and tank/block-entity storage are not ported yet; early melting/casting recipes reference fluid ids and output representative items.
- Smeltery/foundry multiblock validation is not ported yet.
- Worldgen, slime content, guidebooks, JEI integration, entities, client renderers, and books are not ported yet.
- Material and modifier JSON reload listeners are implemented for `data/<namespace>/tinkering/materials` and `data/<namespace>/tinkering/modifiers`.
- Tool definitions and station layouts are not datapack-loaded yet.

## Tests And Commands

- `.\gradlew.bat build`
  - Initially failed because `gradlew.bat` passed an empty `-classpath` to Java before `-jar`.
  - Passed after fixing the wrapper invocation.
- `.\gradlew.bat compileJava`
  - Passed after the first CE implementation batch.
- `.\gradlew.bat runData`
  - Passed. The mod boots in the data generator; no providers are registered yet, so no generated files were emitted beyond the cache.
- `.\gradlew.bat runServer`
  - Reached dedicated server startup. Log line: `Done (23.433s)! For help, type "help"`.
  - The command was stopped by the smoke-test timeout because a dedicated server remains running by design.
- `.\gradlew.bat runGameTestServer`
  - Passed after adding `TConstructCEGameTests` and an empty structure template under `data/tconstruct_ce/structure/empty.nbt`.
  - Current tests: datapack tool data loads, tool receives haste modifier, broken tool keeps its stack.

## Known Blockers

- Upstream Java source code was not supplied, only compiled jars. A full architecture-preserving port needs the source tree or a deliberate clean-room reimplementation plan.
- The local PATH exposes `java.exe` but not `jar.exe`; jar inspection used `C:\Program Files\Java\jdk-20\bin\jar.exe`.
- The shell `java` on PATH is Java 20, but Gradle resolved and used an Eclipse Adoptium Java 21 toolchain for NeoForge run tasks.
- No Java decompiler command was found on PATH during the reverse-engineering pass. Current notes use resources, class listings, metadata, and selected `javap` signatures.
- GitHub Actions workflows have not yet run remotely in this workspace; validate them after pushing to the repository.
