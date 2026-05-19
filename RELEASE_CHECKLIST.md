# Release Checklist

Tinkers' Construct Community Edition is an unofficial community port maintained by gfaraujosousa. Original Tinkers' Construct and Mantle are by SlimeKnights and contributors.

## Before Tagging

- [ ] Update `mod_version` in `gradle.properties`.
- [ ] Confirm artifact naming is `tconstruct-ce-neoforge-1.21.1-<version>.jar`.
- [ ] Update `CHANGELOG.md` with the release date and completed work.
- [ ] Update `PORTING_NOTES.md` with commands, test status, and known limitations.
- [ ] Confirm README attribution says:
  - Tinkers' Construct Community Edition is an unofficial community port maintained by gfaraujosousa.
  - Original Tinkers' Construct and Mantle are by SlimeKnights and contributors.
- [ ] Confirm no README, metadata, changelog, or release notes claim this is an official SlimeKnights release.
- [ ] Confirm imported upstream resources retain license/credit attribution.
- [ ] Confirm no `.env`, tokens, local caches, crash reports, or run logs are staged.
- [ ] Review Minecraft/NeoForge dependency changes manually. Do not accept version bumps blindly.

## Validation Commands

- [ ] `.\gradlew.bat clean build`
- [ ] `.\gradlew.bat runData`
- [ ] `.\gradlew.bat runGameTestServer`
- [ ] `.\gradlew.bat runServer` dedicated server startup smoke test
- [ ] Client dev run smoke test when client systems changed

## Release Prep Commit

- [ ] Review status: `git status`
- [ ] Review diff: `git diff`
- [ ] Stage only release-prep files.
- [ ] Commit: `chore(release): prepare vX.Y.Z`

## Tag And Push

- [ ] Create annotated tag: `git tag -a vX.Y.Z -m "Release vX.Y.Z"`
- [ ] Push branch and tags: `git push origin main --tags`
- [ ] Confirm GitHub Actions release workflow starts.
- [ ] Confirm GitHub Release is created.
- [ ] Confirm jars, changelog, README, porting notes, and license files are attached.

## Publishing

- [ ] Do not publish to CurseForge, Modrinth, or Maven from this workflow.
- [ ] If publishing is added later, use repository secrets only:
  - `MODRINTH_TOKEN`
  - `CURSEFORGE_TOKEN`
  - `GITHUB_TOKEN`
- [ ] Never hardcode tokens.
