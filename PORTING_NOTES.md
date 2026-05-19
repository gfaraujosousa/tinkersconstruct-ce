# Porting Notes

## Source And Target

- Source reference detected: compiled `original_sources/TConstruct-1.20.1-3.11.2.166.jar` and `original_sources/Mantle-1.20.1-1.11.104.jar`.
- Java source trees for upstream TConstruct/Mantle were not present in this workspace, so this pass uses the compiled jars as behavioral/content references and implements a focused NeoForge foundation in source.
- Target: Minecraft `1.21.1`, NeoForge `21.1.230`, Java `21` target from the template.
- Community mod id: `tconstruct_ce`.

## API Replacements Started

- ForgeGradle-era setup: kept the supplied NeoForge userdev template rather than importing old build scripts.
- Old registry events: replaced with NeoForge `DeferredRegister`.
- Old ItemStack NBT/share tag tool data: started as a typed `DataComponentType<ToolStackData>` with a codec-backed immutable record.
- Old recipe serializers: early custom recipe serializers use 1.21.1 `MapCodec` and `StreamCodec`.

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
- Datapack reload listeners for material/modifier JSON are not implemented yet; the records/codecs are in place with built-in bootstrap data.

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
  - Current tests: tool receives haste modifier, broken tool keeps its stack.

## Known Blockers

- Upstream Java source code was not supplied, only compiled jars. A full architecture-preserving port needs the source tree or a deliberate clean-room reimplementation plan.
- The local PATH exposes `java.exe` but not `jar.exe`; jar inspection used `C:\Program Files\Java\jdk-20\bin\jar.exe`.
- The shell `java` on PATH is Java 20, but Gradle resolved and used an Eclipse Adoptium Java 21 toolchain for NeoForge run tasks.
