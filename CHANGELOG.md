# Changelog

All notable changes to Tinkers' Construct Community Edition will be documented in this file.

The format follows Keep a Changelog, and this project uses semantic versioning while the port is in active alpha development.

## [Unreleased]

### Added

- Reverse-engineering inventory for the local TConstruct `1.20.1-3.11.2.166` and Mantle `1.20.1-1.11.104` jars.
- Feature parity matrix covering core identity, gameplay, major content, polish, and CI/CD status.
- GitHub Actions CI for wrapper validation, build, datagen, GameTest server, and dedicated server startup smoke testing.
- GitHub Actions release workflow for semantic version tags and manual release dispatch.
- Nightly workflow for unstable jar artifacts.
- Dependabot configuration for GitHub Actions and Gradle dependency review.

### Known Issues

- The port is not feature complete. Current gameplay remains a focused foundation, not full upstream parity.
- Full Mantle CE systems, station GUIs, real fluids, melter internals, casting internals, and smeltery/foundry multiblocks are not yet ported.
- Minecraft/NeoForge version updates require manual port validation and should not be accepted blindly.

### Attribution

- Tinkers' Construct Community Edition is an unofficial NeoForge 1.21.1 community port maintained by gfaraujosousa.
- Original Tinkers' Construct and Mantle are by SlimeKnights and contributors.
- This project is not an official SlimeKnights build.

## [0.1.0-alpha.1] - 2026-05-19

### Added

- First-pass NeoForge 1.21.1 foundation for TConstruct CE.
- Main bootstrap, config, mod metadata, creative tab, core placeholder blocks/items, early component-backed tool data, early flint pickaxe loop, early redstone modifier loop, early melting/casting serializers, material/modifier JSON reload listeners, and minimal Game Tests.

### Known Issues

- This alpha represents foundation work only, not a stable parity release.

### Attribution

- Tinkers' Construct Community Edition is an unofficial community port maintained by gfaraujosousa.
- Original Tinkers' Construct and Mantle are by SlimeKnights and contributors.
