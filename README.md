# Tinkers' Construct Community Edition

Tinkers' Construct Community Edition is an unofficial community port/fork. Original Tinkers' Construct and Mantle are by SlimeKnights and contributors.

This repository targets Minecraft 1.21.1 on NeoForge using the existing NeoForge Gradle template. It is not an official SlimeKnights release and should not be represented as one.

## Current Port Scope

- Mod id: `tconstruct_ce`
- Loader: NeoForge 1.21.1
- Java: 21 target via the NeoForge template toolchain
- Source reference supplied in this workspace: compiled Mantle `1.20.1-1.11.104` and TConstruct `1.20.1-3.11.2.166` jars under `original_sources/`

The current implementation ports a small functional foundation: core registries, a typed data-component tool stack, built-in flint material data, a haste modifier, basic part/tool items, early station blocks, a modifier crafting recipe, and early melting/casting recipe serializers.

## License And Attribution

Original Tinkers' Construct and Mantle code, assets, names, credits, and design belong to SlimeKnights and contributors under their published license terms. Preserve all attribution, credits, license headers, and notices when importing additional original source or assets.

This community port is MIT-licensed where applicable, but bundled or imported upstream material keeps its original notices and attribution.
