# Tinkers' Construct Community Edition

[![CI](https://github.com/gfaraujosousa/tinkersconstruct-ce/actions/workflows/ci.yml/badge.svg)](https://github.com/gfaraujosousa/tinkersconstruct-ce/actions/workflows/ci.yml)
[![Latest Release](https://img.shields.io/github/v/release/gfaraujosousa/tinkersconstruct-ce?include_prereleases&label=release)](https://github.com/gfaraujosousa/tinkersconstruct-ce/releases)
[![License: MIT](https://img.shields.io/badge/license-MIT-blue.svg)](TEMPLATE_LICENSE.txt)
[![Minecraft 1.21.1](https://img.shields.io/badge/Minecraft-1.21.1-62b47a)](https://www.minecraft.net/)
[![NeoForge 21.1.230](https://img.shields.io/badge/NeoForge-21.1.230-f16436)](https://neoforged.net/)

Tinkers' Construct Community Edition is an unofficial community port/fork maintained by gfaraujosousa. Original Tinkers' Construct and Mantle are by SlimeKnights and contributors.

This repository targets Minecraft 1.21.1 on NeoForge using the existing NeoForge Gradle template. It is not an official SlimeKnights release and should not be represented as one.

## Current Port Scope

- Mod id: `tconstruct_ce`
- Loader: NeoForge 1.21.1
- Java: 21 target via the NeoForge template toolchain
- Current artifact naming: `tconstruct-ce-neoforge-1.21.1-<version>.jar`
- Source reference supplied in this workspace: compiled Mantle `1.20.1-1.11.104` and TConstruct `1.20.1-3.11.2.166` jars under `original_sources/`

The current implementation ports a small functional foundation: core registries, a typed data-component tool stack, built-in flint material data, a haste modifier, basic part/tool items, early station blocks, a modifier crafting recipe, and early melting/casting recipe serializers.

## Build And Test

Use Java 21 through the Gradle/NeoForge toolchain.

```powershell
.\gradlew.bat clean build
.\gradlew.bat runData
.\gradlew.bat runGameTestServer
.\gradlew.bat runServer
```

`runServer` is a smoke test for dedicated server startup and remains running after the server reaches the prompt.

## Releases

Release tags use semantic versioning:

- Alpha: `v0.1.0-alpha.1`
- Beta: `v0.1.0-beta.1`
- Stable: `v1.0.0`

GitHub Actions validate builds, datagen, Game Tests, and dedicated server startup. Tagged releases matching `v*.*.*` or `v*.*.*-*` create GitHub Releases and attach the built jars plus project notes. The workflows do not publish to CurseForge, Modrinth, or Maven.

Minecraft and NeoForge version bumps require manual port validation. Do not accept automated dependency updates for those versions without a dedicated migration pass.

## License And Attribution

Port maintainer: gfaraujosousa.

Original authors: SlimeKnights and contributors.

Original Tinkers' Construct and Mantle code, assets, names, credits, and design belong to SlimeKnights and contributors under their published license terms. Preserve all attribution, credits, license headers, and notices when importing additional original source or assets.

This community port is MIT-licensed where applicable, but bundled or imported upstream material keeps its original notices and attribution.
